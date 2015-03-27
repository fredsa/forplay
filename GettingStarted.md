# ForPlay Installation + Setup #

**Note the first: The command-line build is working now (run "ant install" from the root to build everything), but some people are still running into problems with the Eclipse projects. We hope to move to a more sound way of building and sharing the Eclipse projects Real Soon Now.**

**Note the second: These instructions are a bit out-of-date. We'll update them again as soon as we get the last of the build kinks worked out.**

## Software Installation ##

  1. Download and install the latest Java 6 JDK
    * Note: you need the JDK, which includes the Java compiler, not just the JRE
  1. Download and extract _Eclipse 3.6_ (Helios) from http://www.eclipse.org/downloads/
  1. Launch the Eclipse IDE (double-click Eclipse icon inside the extracted directory)
  1. Install the Eclipse _GWT_ / _App Engine_ plugins and SDKs according to the **Quick Start** instructions on the Eclipse plugin page (http://code.google.com/eclipse/docs/getting_started.html)
    * Eclipse: **Help -> Install New Software**
    * Click **Add**
    * Enter this URL (for Eclipse 3.6): http://dl.google.com/eclipse/plugin/3.6
  1. (Optional) download _Subclipse_ according to http://subclipse.tigris.org/download.html
    * Eclipse: Help -> Install New Software
    * Click **Add**
    * Enter this URL: http://subclipse.tigris.org/subclipse_1.6.x/changes.html

## Checkout SVN Projects ##

  * The SVN project is at https://code.google.com/p/forplay
  * Following the instructions to checkout the source code
    * SVN URL: [url](svn.md)
    * Checkout into a directory called `forplay`
    * Use your Google Code username and Google Code password
  * Import projects into Eclipse
    * Eclipse: **File -> Import -> Existing projects**
    * Click **Browse** and specify the forplay folder which you created in the previous step. You'll see a few projects which you can import.

## Code Style and Formatting ##

The style guide (such as it is) for the ForPlay library can be found on the CodeStyle page. The most important aspect is that you import the Eclipse format
style, like so:

  1. Select the "Window/Preferences..." (or "Eclipse/Preferences..." on Mac OS X) menu.
  1. Select "Java/Code Style/Formatter" in the preferences tree.
  1. Click the "Import..." button, and select "forplay/eclipse\_format.xml" from the repository.

This will give you an auto-format configuration that will keep everyone more or less in sync on style, which avoids a lot of headaches and diff-cruft.
There's no reason that we need to be sticklers about the details, though.

## Importing a sample project ##

In Eclipse, import the Peas sample project in forplay/samples.

## Running a Java Project ##

To create a new launch configuration for the Java project, right click on the PeasJava project and select **Run As -> Java Application**

## Running the HTML Project ##

  1. _Development Mode_:
    * Right click on the PeasHtml project and select **Run As -> Web Application** to create a new one
    * Right click on the URL which is shown in the Development Mode view and select **Copy**
    * Paste the URL (http://127.0.0.1:8888/Peas.html?gwt.codesvr=127.0.0.1:9997) into your browser
  1. _Production Mode_:
    * Select the PeasHtml project; click the red GWT toolbox icon to compile the application
    * Once compiled, you can copy the application from the `peas/war/` directory
    * Alternatively, just remove the `?gwt.codesvr` parameter from the Development Mode URL, i.e. go to http://127.0.0.1:8888/Peas.html

## Note on Java vs. HTML debugging ##

It is the intention of the ForPlay library that you be able to work directly in Java the vast majority of the time. You should only have to debug the HTML version if you're either implementing HTML-specific functionality (which should be rare), or working on the HTML part of the library directly. If for any reason you find that the behavior of the Java version differs from the HTML version (or even worse, is broken), **please** raise a red flag so that we can fix this. Debugging directly in the JVM will always be easier and more efficient than having to debug within the browser, and we should be able to take full advantage of that fact.