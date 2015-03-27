There are three main concepts in ForPlay's graphics architecture: `Layer`,
`Surface`, and `Canvas`. All drawing is performed in one of these three
objects.


## Layers ##

The outermost part of the system is the `Layer`. The graphics system contains a
hierarchy of layers that is rendered automatically by the system. Take the
following example:

```
public void init() {
  Image bg = assetManager().getImage("images/bg.png");
  ImageLayer bgLayer = graphics().createImageLayer(bg);
  graphics().rootLayer().add(bgLayer);

  Image pea = assetManager().getImage("images/pea.png");
  ImageLayer peaLayer = graphics().createImageLayer(pea);
  peaLayer.rotate((float) (Math.PI / 4));
  graphics().rootLayer().add(peaLayer);
}
```

This creates a simple two-layer structure, with a background image, and a
smaller image hovering above it (and rotated 45 degrees). The resulting layer
hierarchy is as follows:

  * RootLayer
    * ImageLayer (bg)
    * ImageLayer (pea)

Each layer has an affine transform and several other associated properties,
which can be manipulated directly (changes take effect automatically on the
next rendered frame). In addition to `ImageLayer`, there is also a `GroupLayer`
for binding other layers together into a group (this is particularly useful
when you want a set of layers to move together, share the same alpha value, and
so forth), along with `CanvasLayer`, and `SurfaceLayer`.


## Surfaces ##

While layers can be convenient, it is often preferable to be able to render
part of a scene directly, using an imperative API. A `SurfaceLayer` allows
you to do this easily. Consider the following example:

```
SurfaceLayer surf;
Image pea;

public void init() {
  pea = assetManager().getImage("images/pea.png");
  surf = graphics().createSurfaceLayer(WIDTH, HEIGHT);
  graphics().rootLayer().add(surf);
}

public void paint() {
  // Draw 100 random peas imperatively.
  surf.clear(0);
  for (int i = 0; i < 100; ++i) {
    int x = (int)(random() * WIDTH);
    int y = (int)(random() * HEIGHT);
    surf.drawImage(pea, x, y);
  }
}
```

This creates a single surface layer that has 100 peas drawn on it in random
locations each time it's painted. Note that this layer can coexist peacefully
with other layers, and has the same properties as other layers (transform,
alpha, and so forth).


## Images and Canvases ##

The above examples use `Image` in a fairly straightforward way. In general, an
`Image` can be used as a source of bitmap data to be drawn into a `Surface` or
`Canvas`.

A `Canvas` is an interface that allows you to draw directly into an `Image`,
which itself will be used as a source for drawing calls. Here's a simple
example:

```
Image pea;
CanvasImage borderedPea;
SurfaceLayer surf;

public void init() {
  pea = assetManager().getImage("images/pea.png");
  borderedPea = graphics().createImage(48, 48);

  Canvas canvas = borderedPea.canvas();
  canvas.setStrokeWidth(2);
  canvas.setStrokeColor(0xffff0000);
  canvas.strokeRect(1, 1, 46, 46);
  canvas.drawImage(pea, 12, 12);

  surf = graphics().createSurfaceLayer(WIDTH, HEIGHT);
  graphics().rootLayer().add(surf);
}

public void paint() {
  surf.clear(0);
  surf.drawImage(borderedPea, 0, 0);
}
```

You can also create a `CanvasLayer`, which creates a layer that can be drawn
into directly using the `Canvas` API:

```
public void init() {
  CanvasLayer layer = graphics().createCanvasLayer();
  graphics().rootLayer().add(layer);

  Canvas canvas = layer.canvas();
  canvas.setStrokeWidth(2);
  canvas.setStrokeColor(0xffff0000);
  canvas.strokeRect(1, 1, 46, 46);
}
```

**Note**: Using an image created this way with ImageLayer doesn't work properly yet. See http://code.google.com/p/forplay/issues/detail?id=7 for details.

## The differences among `Layer`, `Canvas` and `Surface` ##

You may be asking yourself "How do I choose among `Layer`, `Canvas` and
`Surface`?" If your game structure fits naturally into layers, then it almost
always makes sense to use the `Layer` hierarchy directly. Layers will be
rendered in the most efficient way possible on each platform.

For imperative rendering (i.e., when you want to draw part of your scene
back-to-front by hand, as in the [Cute](Cute.md) sample), you can choose between
`Canvas` and `Surface`.  The distinction is actually fairly simple. `Canvas` is
a full-featured 2D rendering API, with text, paths, curves, gradients, and so
forth. `Surface` is a much simpler API. The difference between the two is
performance -- `Canvas` makes no guarantees, and is indeed often quite slow;
`Surface` is explicitly designed to be easy to map to OpenGL calls, and can
thus be much faster.

A typical use-case for a `Canvas` is when you have more complex 2D rendering to
perform, but once it's done you can cache the results, using the `Canvas`
either directly within a `Layer` or as a parameter to `Surface.drawImage()`.
This gives you the flexibility you need to render almost anything, but still
allows you to retain control over the speed of rendering.