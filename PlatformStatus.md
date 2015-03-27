# Java Desktop #

The purpose of the Java desktop platform is simply to support development and debugging. All the APIs are fully implemented here, albeit on Java2D graphics for now (see ...), which are a bit on the slow side.

# HTML5 #

This platform is fully-implemented, and is pretty well optimized at this point (not that there aren't always opportunities for further optimization). There are WebGL and Canvas renderers (the "canvas" renderer is really a combination of CSS3 transforms applied to DOM elements, along with canvas elements for imperative rendering).

It supports the following browsers:
- Chrome (gl)
- WebKit Nightly (gl)
- Firefox 4 (gl)
- Safari 5 (canvas)
- IE9 (canvas)
- Opera 11 (canvas)

# Flash #

Flash is coming along well, but is still missing some functionality. We hope to have this fixed up fairly soon.

# Android #

Android is still in dire need of updating. It kind of works, but there are significant bugs, gaps in functionality, and so forth. To be fair, it was nearly fully working at one point, but then we decided to change a lot of the APIs pretty drastically, and it fell behind. The good news is that, based on our experience when it **did** work, performance was very good.