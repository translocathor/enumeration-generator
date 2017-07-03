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

import java.util.Properties;
import java.util.Set;

/**
 * A KeyDerivator is used to get a set of key from a provided properties file.
 *
 * @author Adrian Bingener
 * @param <T> The type of the key elements
 */
public interface KeyDerivator<T> {

    /**
     * Derivates a list of keys from the given {@link Properties}. This list can
     * be larger or smaller than the actual set of keys that are present in the
     * properties
     *
     * @param properties The properties which is used to derivate a list of keys
     * @return A set of keys, derivated from the given properties file
     */
    public Set<T> derivateKeys(Properties properties);
}
