/*
 * Copyright 2017 Adrian Bingener.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.translocathor.egen;

/**
 *
 * @author Adrian Bingener
 */
public class Util {

    /**
     * Checks if the given identifier is a valid Java identifier. This will not
     * include fully qualified names.
     *
     * @param identifier The identifier to be tested
     * @return <code>true</code> if the identifier is a valid Java identifier,
     * <code>false</code> otherwise
     */
    public static boolean isValidJavaIdentifier(String identifier) {

        // An empty or null string cannot be a valid identifier
        if (identifier == null || identifier.length() == 0) {
            return false;
        }

        char[] c = identifier.toCharArray();
        if (!Character.isJavaIdentifierStart(c[0])) {
            return false;
        }

        for (int i = 1; i < c.length; i++) {
            if (!Character.isJavaIdentifierPart(c[i])) {
                return false;
            }
        }

        return true;
    }
}
