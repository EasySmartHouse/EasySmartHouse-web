/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.util;

import com.google.gwt.i18n.client.NumberFormat;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * Limited implementation of MessageFormat using JSNI. Only supports string
 * replacement, and not date or number formatting for arguments.
 *
 * @author <a href="mailto:nicolas@apache.org">Nicolas De loof</a>
 */
public class MessageFormat extends Format {

    private String pattern;

    public MessageFormat(String pattern) {
        applyPattern(pattern);
    }

    public void applyPattern(String pattern) {
        this.pattern = pattern;
    }

    public static String format(String pattern, Object... arguments) {
        return doFormat(pattern, arguments);
    }

    public final String format(Object obj) {
        if (obj instanceof Object[]) {
            return doFormat(pattern, (Object[]) obj);
        }
        return doFormat(pattern, new Object[]{obj});
    }

    private static String doFormat(String s, Object[] arguments) {
        // A very simple implementation of format
        int i = 0;
        while (i < arguments.length) {
            String delimiter = "{" + i + "}";
            while (s.contains(delimiter)) {
                s = s.replace(delimiter, String.valueOf(arguments[i]));
            }
            i++;
        }
        return s;
    }

    public static String getFormatted(double value, int decimalCount) {
        StringBuilder numberPattern = new StringBuilder(
                (decimalCount <= 0) ? "" : ".");
        for (int i = 0; i < decimalCount; i++) {
            numberPattern.append('0');
        }
        return NumberFormat.getFormat(numberPattern.toString()).format(value);
    }
}
