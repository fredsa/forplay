When you implement the `Game` interface and call `ForPlay.run(game)`, you
relinquish control to the game loop. Conceptually, this is quite simple -- it
runs a loop "forever", calling two `Game` methods, like so:

```
while (true) {
  game.update(...);
  game.paint(...);
}
```

Of course it doesn't run "as fast as possible", but is limited to how
frequently the platform can actually display a frame (there's no point in
painting frames that will never be displayed). This is limited either by the
refresh rate of the display, or by a platform-specific maximum display rate.

There are, however, two frequencies we must concern ourselves with -- the
display frequency and the update frequency. For games with relatively simple
update logic, these can be made one and the same, as in the following example:

```
public class MyGame implements Game {
  public void update(float delta) {
    // Update the game's state.
    // 'delta' is the time in milliseconds since the last update call.
  }

  public void paint(float alpha) {
    // Paint using the game's current state.
    // 'alpha' will always be zero. Ignore it.
  }

  public int updateRate() {
    // Returning zero here explicitly requests an update() call for each frame.
    return 0;
  }
}
```

Games with more complex logic (especially those using physics engines such as
Box2D) need more control over the frequency of updates. This is often necessary
because the numerical integration techniques used by physics engines can become
unstable outside of certain frequency ranges. The most common solution to this
problem is to define a fixed frequency for updates, while still painting as
often as possible.

This brings up another issue, though. `paint()` will usually be called at some
random point in time **between** game updates. What should I paint in these
cases? The answer is hinted at in the `alpha` parameter to `paint()`.  This
parameter will be a number between 0 and 1, representing how far along in time
we are between the last two updates. This can be used to interpolate the world
state, so that animation remains as smooth as possible. The following example
should serve as an illustration:

```
public class MyGame implements Game {
  static final float GRAVITY = 64;

  ImageLayer layer;

  float px, py;
  float x, y;
  float vx, vy;
  float ax, ay;

  public void init() {
    Image img = assetManager().getImage("ball.png");
    layer = graphics().createImageLayer(img);
    graphics().rootLayer().add(layer);

    x = graphics().width() / 2;
    y = graphics().height() / 2;
    ay = GRAVITY;
  }

  public void update(float delta) {
    // Save previous position for interpolation.
    px = x;
    py = y;

    // Update physics.
    delta /= 1000;
    vx += ax * delta;
    vy += ay * delta;
    x += vx * delta;
    y += vy * delta;
  }

  public void paint(float alpha) {
    // Interpolate current position.
    float x = (this.x * alpha) + (px * (1f - alpha));
    float y = (this.y * alpha) + (py * (1f - alpha));

    // Update the layer.
    layer.resetTransform();
    layer.translate(
      x - layer.image().width() / 2,
      y - layer.image().height() / 2
    );
  }

  public int updateRate() {
    // Request that update() run at 20 fps.
    return 50;
  }
}
```

This example creates a single ball that starts in the middle of the screen and
drops off, accelerating under gravity. This demonstrates a very simple case of
decoupling physics from rendering. Note that because `udpateRate()` returns 50
(milliseconds), `update()` will be called at a fixed rate of 20 frames/s. But
`paint()` will still be called as frequently as possible.

In `update()`, we update only the physics variables (simple as they are), but
don't do anything that would affect rendering. In `paint()`, we update the
graphics to reflect the current state of the world. But there's a catch: if we
just rendered every frame from the same physics variables, each frame would
look the same until the next `update()`. This would lead to "stuttering" of the
animation (temporal aliasing artifacts), which kind of defeats the purpose of
rendering these extra frames at all. Instead, we keep track of both the
previous and current positions of the ball, linearly interpolating between them
when rendering. This keeps the animation running as smoothly as possible, while
the physics simulation gets the fixed rate that it needs.