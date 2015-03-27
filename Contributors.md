# First Things First #

  * You'll need to sign a [Contributor License Agreement](http://code.google.com/p/forplay/source/browse/trunk/CONTRIBUTORS) before submitting a patch. It's fairly simple and painless.
    * Once you've done that, please ping the [discussion group](http://groups.google.com/group/gwt-forplay) so we can add you to the contributors list.
  * Familiarize yourself with the CodeStyle. It's not unusual, but we do a few things differently than you might be used to.

# Code Reviews #

We do pre-submit code reviews using the [Rietveld](http://code.google.com/p/rietveld/) code-review tool. We've found pre-submit reviews to be a great way to catch problems early, and to share knowledge.

Once you've got a patch ready for review, you can upload it to [our Rietveld instance](http://forplay-code-reviews.appspot.com/) using the Pythong script `tools/upload.py`. This will automatically create an issue, and give you a link to it. It's generally best to ping the [discussion group](http://groups.google.com/group/gwt-forplay) to get someone to have a look.

If your patch is for a specific issue, please put a reference to the issue number in the patch description (bonus points for adding a link to the patch in the issue you're fixing). If there's no such issue, please create one. This helps communicate to everyone what's being worked on, and helps avoid duplicated effort.

Once the review is complete, the reviewer or someone else with commit access will submit it to svn for you.

## "TBR" Reviews ##
For simple, uncontroversial patches (e.g., obvious typos, spelling/documentation fixes, fixing broken tests or builds, and the like), it is acceptable for a committer to submit a change "TBR", meaning "to be reviewed". In this case, the Rietveld issue should cc a specific reviewer and note that it was submitted TBR. It is up to the reviewer to come back and make sure there were no problems with the submitted change.