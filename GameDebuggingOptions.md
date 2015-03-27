_This page is referenced by [HtmlPlatform.java](http://code.google.com/p/forplay/source/browse/trunk/core/src/forplay/html/HtmlPlatform.java)_


# Game development (and debugging) #

There are a few options available for development and debugging of your game. There are various reasons to use the different modes, which are listed here in order of likelihood you'll want to use them.

  1. **Java runtime environment**
> > By default, and especially when you are designing and debugging the core functionality in your game, use the Java runtime environment, which utilizes Java2D for graphics. This provides the easiest and most productive setup for game development.
> > <p>To get started:<br>
<ol><li>Right-click your game's Eclipse project<br>
</li><li>Select <code>Run As... -&gt; Java application</code>
</li><li>If prompted, select your game's main Java class<br>
</li><li>Play, debug, iterate<br>
</li></ol></li></ul><ol><li><b>GWT Production Mode</b>
<blockquote>While this mode lets you test your game as it will be played by your users, and gives you the best indicator of what game performance will look like (on comparable hardware and corresponding browsers), you'll find that the edit-save-refresh cycle is the longest, since modifications to the game code require a full recompile before they are visible in the browser. However, because this is the mode which your users will use to play your game, it is sometimes the best environment for troubleshooting low level issues. You'll have the full set of tools that any JavaScript developer has, like browser developer tools, JavaScript debuggers, HTTP debugging proxies, etc.<br>
<p>To get started:<br>
<ol><li>Right-click your game's Eclipse project<br>
</li><li>Select <code>Google -&gt; GWT Compile</code>
</li><li>If you plan to debug the compiled JavaScript, be sure to set the <code>Output Style</code> to <code>PRETTY</code>, otherwise leave the default <code>OBFUSCATED</code> mode for fully optimized JavaScript<br>
</li><li>To run the game locally, right-click your game's Eclipse project, select <code>Run As... -&gt; Web Application</code>, copy the URL out of the GWT Development Mode view and paste it into your browser, but remove the <code>?gwt.codesvr=....</code> parmeter to run in Production Mode<br>
<ul><li>Alternatively, deploy your compiled game to a (development) web server. To deploy to App Engine, select <code>Google -&gt; Deploy to App Engine</code>
</li></ul></li><li>Play, debug, iterate<br>
</li></ol></blockquote></li><li><b>GWT Development Mode</b>
<blockquote>This is a hybrid method, which allows you to run the game in the web browser, but use the JVM's debugger to step through code, inspect and modify variables, set breakpoints and even hot-swap code. This method, however can introduce significant overhead into your game's main event loop, drastically slowing down frame rates. Due to Chrome's security sandbox, which requires process isolation for the GWT Development Mode plugin, this (development time only) performance problem is particularly pronounced in this browser, even though you may find it to be the best performer in Production Mode. For this reason, Firefox is recommended for general debugging while in Development Mode. On the other hand, Development Mode will be relatively slow no matter which browser you use, so you should be using the above options whenever possible.<br>
<p>To get started:<br>
<ol><li>Right-click your game's Eclipse project<br>
</li><li>Select <code>Run As... -&gt; Web Application</code>, copy the URL, including the <code>?gwt.codesvr=....</code> URL parameter, out of the GWT Development Mode view and paste it into your browser<br>
<ul><li>Select <code>Debug As... -&gt; Web Application</code> instead if you plan to use the JVM debugger<br>
</li><li>Note that the first time the game is loaded after launching Development Mode, you will have to wait longer than after subsequent refreshes<br>
</li></ul></li><li>Play, debug, iterate, keeping in mind that:<br>
<ul><li>You can make code changes in your IDE, and then just hit Refresh (F5) in your browser to quickly see your changes, without having to restart development mode. In fact, you should NOT restart development mode, as that will just take longer.<br>
</li><li>Any changes you make to server side code (which is also running on your local machine) will take effect after you hit the small yellow refresh icon in the GWT Development Mode view.<br>
</li><li>When hot swapping client code (i.e. changing method implementations while game execution is paused), your IDE may warn you that hot swapping code is not supported and that you will need to restart the JVM. Ignore this warning. The GWT development mode class loader will be able to pickup your changes as soon as you hit Refresh (F5) in your browser. There's no need to restart development mode.