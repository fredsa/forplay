/**
 * Copyright 2011 The ForPlay Authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package forplay.html;

/**
 * Trivially log all output to {@link System#out}. Note, critical messages are
 * not separated out to {@link System#err}, since that causes the two output
 * streams to become mixed and log messages unreadable. Consider enabling
 * ForPlay's enhanced logging capabilities if you need additional features
 * beyond what is provided here.
 */
class HtmlLogSimple extends HtmlLog {

  // Instantiated via GWT.create()
  private HtmlLogSimple() {
  }

  @Override
  public void debug(String msg) {
    sendToConsole(msg);
  }

  @Override
  public void debug(String msg, Throwable e) {
    sendToConsole(msg, e);
  }

  @Override
  public void error(String msg) {
    sendToConsole(msg);
  }

  @Override
  public void error(String msg, Throwable e) {
    sendToConsole(msg, e);
  }

  @Override
  public void info(String msg) {
    sendToConsole(msg);
  }

  public void info(String msg, Throwable e) {
    sendToConsole(msg, e);
  }

  @Override
  public void warn(String msg) {
    sendToConsole(msg);
  }

  @Override
  public void warn(String msg, Throwable e) {
    sendToConsole(msg, e);
  }

  private void sendToConsole(String msg) {
    System.out.println(msg);
  }

  private void sendToConsole(String msg, Throwable e) {
    // Keep console output intact by using System.out for both
    System.out.println(msg);
    e.printStackTrace(System.out);
  }
}