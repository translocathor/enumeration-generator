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
import org.junit.Before;

/**
 * An abstract class that can be inherited to get access to predefined test
 * fixtures.
 *
 * @author Adrian Bingener
 */
public abstract class PropertiesFixtureTest {

    /**
     * Empty properties that contain no keys.
     */
    protected Properties emptyProperties;
    /**
     * Properties that contain complex keys containing upper- and lowercase
     * characters as well as underscores.
     */
    protected Properties propertiesComplexKeys;
    /**
     * Properties that contain simple keys containing only uppercase characters
     * and digits.
     */
    protected Properties propertiesSimpleKeys;

    @Before
    public void setUp() {
        // Build empty properties
        emptyProperties = new Properties();

        // Build simple properties
        propertiesSimpleKeys = new Properties();
        propertiesSimpleKeys.put("KEY1", "Value1");
        propertiesSimpleKeys.put("KEY2", "Value2");
        propertiesSimpleKeys.put("KEY3", "Value3");
        propertiesSimpleKeys.put("KEY4", "Value4");

        // Build complex properties
        propertiesComplexKeys = new Properties();
        propertiesComplexKeys.put("SETTING_BUTTON_OK_Text", "OK");
        propertiesComplexKeys.put("SETTING_BUTTON_OK_Tooltip", "Saves all settings and minimizes the window");
        propertiesComplexKeys.put("SETTINGS_BUTTON_CANCEL_Text", "");
    }
}
