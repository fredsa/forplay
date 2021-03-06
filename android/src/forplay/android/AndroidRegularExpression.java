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
package forplay.android;

import java.util.regex.Pattern;

import forplay.core.RegularExpression;

class AndroidRegularExpression implements RegularExpression {

  /**
   * Equivalent to Pattern.compile(regexp).matcher(source).find()
   */
  @Override
  public boolean matches(String regexp, String source) {
    return Pattern.compile(regexp).matcher(source).find();
  }
}
