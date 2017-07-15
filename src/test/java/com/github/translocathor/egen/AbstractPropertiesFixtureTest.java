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

import java.util.Properties;
import org.junit.Before;

/**
 * An abstract class that can be inherited to get access to predefined test
 * fixtures.
 *
 * @author Adrian Bingener
 */
public abstract class AbstractPropertiesFixtureTest {

    /**
     * Empty properties that contain no keys.
     */
    protected Properties propertiesEmpty;
    /**
     * Properties that contain simple keys containing only uppercase characters
     * and digits.
     */
    protected Properties propertiesSimpleValid;
    /**
     * Properties that contain complex keys containing upper- and lowercase
     * characters as well as underscores.
     */
    protected Properties propertiesComplexValid;
    /**
     * Properties that contain complex keys containing all kinds of keys, some
     * of which are not valid Java identifiers. Th e
     */
    protected Properties propertiesInvalid;

    @Before
    public void setUpProperties() {
        // Build empty properties
        propertiesEmpty = new Properties();

        // Build simple properties
        propertiesSimpleValid = new Properties();
        propertiesSimpleValid.put("KEY1", "Value1");
        propertiesSimpleValid.put("KEY2", "Value2");
        propertiesSimpleValid.put("KEY3", "Value3");
        propertiesSimpleValid.put("KEY4", "Value4");

        // Build complex properties
        propertiesComplexValid = new Properties();
        propertiesComplexValid.put("SETTING_BUTTON_OK_Text", "OK");
        propertiesComplexValid.put("SETTING_BUTTON_OK_Tooltip", "Saves all settings and minimizes the window");
        propertiesComplexValid.put("SETTINGS_BUTTON_CANCEL_Text", "");
        propertiesComplexValid.put("Text", "");
        propertiesComplexValid.put("T", "");
        propertiesComplexValid.put("_", "");
        
        propertiesInvalid = new Properties();
        propertiesInvalid.put("SETTING_BUTTON_OK.Text", "Button");
        propertiesInvalid.put("SETTING_BUTTON_OK Tooltip", "Executes an eaction");
        propertiesInvalid.put(")", "Parantheses");
        propertiesInvalid.put("?", "Questionmark");
        propertiesInvalid.put("", "");
        propertiesInvalid.put(" ", "");
    }
}
