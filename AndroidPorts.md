# Running your ForPlay game on Android #

**Note:  This article assumes that you already have a working ForPlay game, with the game and ForPlay loaded in projects in Eclipse.  It also assumes that you have a basic familiarity with Android development and have already installed the Android SDK and API 11 into Eclipse.   If you are unfamiliar with these topics, please see the documentation on the official [Android Developers site](http://developer.android.com/sdk/index.html) and the other articles in this Wiki.**

## Project Set-Up ##

Begin by creating a new Android project in Eclipse.  Name it what you like, although a descriptive name such as _mygame-android- is best.  Make sure that you are targeting API level 11, and allow Eclipse to create a new Activity for you._

If you haven't already, you will now need to import the _forplay-android_ project into Eclipse.  Go to **File -> Import -> General -> Existing Projects** into Workspace.  The root directory is in _.../forplay/android_, where _forplay/_ is the root folder of your existing ForPlay project.  Select the _forplay-android_ project and hit **Finish**.

Open your _mygame-android_ project's **Properties**.  If you set your project to target the wrong API, you can fix this now via the Android selection in the left navigation bar.  If not, click on the **Java Build Path** selection.  Under the **Sources** tab, you should already have two sources: _mygame-src_ and _mygame-res_.  In addition to this, you will need to add a Linked Source to the _forplay-android_ source.  Hit the Linked Source... button and browse to the _.../forplay/android/src_ folder for the Linked Folder Location.  Call the Folder Name _forplay-android\_src_ or something simliar.

Now open the **Projects** tab.  Add the existing _forplay_ and _mygame_ projects.  Hit **OK** to save your changes.


## Configure AndroidManifest.xml ##

Open up the _AndroidManifest.xml_ file in your Android game project.  Add the following lines within the `<manifest>`  tag:

```
<uses-sdk android:minSdkVersion="6" android:targetSdkVersion="11" />
 <uses-permission android:name="android.permission.WAKE_LOCK" /> 
```

and add these within the `<activity>` tag:

```
android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
android:configChanges="keyboardHidden|orientation">
```

## Writing the Activity ##

Now open the _MyGameActivity.java_ file that Eclipse should have generated for you. The extended _OnCreate()_ method is the entry point into your game, and will serve the same purpose as the _main()_ function in your Java game.

First, set up your imports by adding the following lines, where _MyGame_ is your existing main class that extends _forplay.core.Game_.

```
import forplay.android.GameActivity;
import forplay.core.ForPlay;
import com.mygames.MyGame;
```

Edit your Activity declaration so that your Activity extends _GameActivity_.  Then write your _OnCreate()_ method so that it resembles this:

```
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    platform().assetManager().setPathPrefix("com/mygames/resources");
    ForPlay.run(new MyGame());
  }
```

Make sure that the path prefix points to the base package for your resource files.  It should look the same as the path prefix in the Java version of your game, but without the _src/_ at the start of the path.

Congratulations!  Your should now be able to run this new project as an Android application and see your game run on the emulator or a connected Android device.  If it runs fine, you're done!  If it crashes, read on for some tips on some possible reasons why.



## Tips for an Easy Port ##

When developing your ForPlay game, it's easy to begin thinking that it will always be run in a Java or web desktop environment.  If you plan on running your game on Android devices, however, there are several key things to keep in mind that will make your life much easier when the time comes:

  * Test your game on Android throughout development.
    * This is the key to an easy porting process.  Developing and testing the Android version of your game in parallel with the desktop and web versions will help you catch any issues early, before they get too embedded in your code to easily locate and debug.
  * Avoid references to device-specific (or GWT-only) classes in your game code.
    * This one can be a killer.  When developing and building your code, Eclipse will not throw up an error  if you use these classes in game code that will run on every platform.  However, this can cause InvocationExceptions and other errors at runtime.
    * Common mistakes include catching _JavaScriptExceptions_ instead of generic _RuntimeExceptions_, which will cause your game to crash on Android even if the exception is not thrown, or referencing _Window.alert()_ instead of the platform-agnostic _ForPlay.log().error()_.
  * Make sure you plan your controls around different input schemes.
    * This is both a design concern and a coding concern.  Try to design your game so that it will work using different input schemes, including mouse, pointer events, multitouch, and desktop and mobile keyboards.
    * Make sure that your make _Game_-extending class implements multiple control type Listeners, especially for the various mouse-like input controls (Mouse, Touch, and Pointer).  You can also check if _ForPlay.mouse()_, _ForPlay.touch()_, and _ForPlay.pointer()_ are null in order to establish which Listeners your game should set itself at at runtime.