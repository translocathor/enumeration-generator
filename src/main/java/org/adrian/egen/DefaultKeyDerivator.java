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
package org.adrian.egen;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * A default implementation of {@link KeyDerivator} that simply returns the keys
 * of the given properties file without any conversion.
 *
 * @author Adrian Bingener
 */
public class DefaultKeyDerivator implements KeyDerivator<String> {

    /**
     * Returns the keys from the given {@link Properties} file.
     *
     * @param properties The properties which is used to derivate a list of keys
     * @return The key set of the given properties file
     */
    @Override
    public Set<String> derivateKeys(Properties properties) {
        Set<String> keySet = new HashSet<>();
        properties.keySet().forEach(key -> keySet.add(String.valueOf(key)));
        return keySet;
    }
}
