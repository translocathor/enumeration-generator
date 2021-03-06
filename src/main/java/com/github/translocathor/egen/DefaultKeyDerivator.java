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
package com.github.translocathor.egen;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * A default implementation of {@link KeyDerivator} that simply returns the keys
 * of the given properties file that are valid Java identifiers.
 *
 * @author Adrian Bingener
 */
public class DefaultKeyDerivator implements KeyDerivator<String> {

    /**
     * Returns the keys from the given {@link Properties} that are valid Java
     * identifiers.
     *
     * @param properties The properties which is used to derivate a list of keys
     * @return The key set of the given properties file
     */
    @Override
    public List<String> derivateKeys(Properties properties) {
        List<String> keySet = new ArrayList<>();
        properties.keySet().stream().map((key) -> String.valueOf(key)).filter((stringKey) -> (Util.isValidJavaIdentifier(stringKey))).forEachOrdered((stringKey) -> keySet.add(stringKey));
        return keySet;
    }
}
