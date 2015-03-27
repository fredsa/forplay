# Code Style #

We use an Eclipse auto-format configuration to keep everyone more or less in sync on code style. We need not be unnecessarily pedantic about style, but this helps to avoid a lot of diff-cruft and bikeshedding. The auto-format configuration is in the repository under forplay/eclipse\_format.xml (see GettingStarted if you don't know how to use this in Eclipse).

# Package Names #

The ForPlay library's root package is just "forplay". Why not org.godknowswhat.forplay? Because that naming convention is excruciating, and its time has passed. Systems like Python get by just fine without being that stupidly pedantic, and so can we.

# Getters and Setters #

ForPlay eschews the Java convention of defining pseudo-properties using `getFoo()` and `setFoo()`, instead going with just `foo()` and `setFoo()`. Why? Because the code looks better that way. You tend to call getters a lot more commonly than setters, and I (jgw@) for one am sick of seeing `getFoo()` everywhere.

# Static Imports #

When using ForPlay, the first thing you should do is write this import at the top of your .java files:

```
  import static forplay.core.ForPlay.*;
```

That way, you can get to all the subsystems with really simple calls (and you'll get auto-completion for these), like so:

```
    keyboard().setListener(this);
    pointer().setListener(this);
    assetManager().getImage("images/background.png");
```

Aren't all these static methods bad? No, because they're the one super-special part of the system that allows everything else to be platform-dependent. You'll never `new` any of the ForPlay classes -- they're all accessible directly or indirectly from these root methods.