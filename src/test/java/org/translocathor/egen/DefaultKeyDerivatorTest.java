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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test for the {@link DefaultKeyDerivator} class.
 *
 * @author Adrian Bingener
 */
public class DefaultKeyDerivatorTest {

    private DefaultKeyDerivator defaultKeyDerivator;
    private Properties emptyProperties;
    private Properties nonEmptyProperties;

    @Before
    public void setUp() {
        // Create class instance to test
        defaultKeyDerivator = new DefaultKeyDerivator();

        // Create properties that are used as data source
        emptyProperties = new Properties();
        nonEmptyProperties = new Properties();
        nonEmptyProperties.put("SETTING_BUTTON_OK.Text", "OK");
        nonEmptyProperties.put("SETTING_BUTTON_OK.Tooltip", "Saves all settings and minimizes the window");
        nonEmptyProperties.put("SETTINGS_BUTTON_CANCEL.Text", "");
    }

    @Test
    public void derivateKeysShouldBeEmpty() {
        Set<String> keys = defaultKeyDerivator.derivateKeys(emptyProperties);
        Assert.assertTrue(keys.isEmpty());
    }

    @Test
    public void derivateKeysShouldNotBeEmpty() {
        Set<String> keys = defaultKeyDerivator.derivateKeys(nonEmptyProperties);
        Assert.assertFalse(keys.isEmpty());
    }

    @Test
    public void derivateKeysShouldContainThreeKeys() {
        Set<String> keys = defaultKeyDerivator.derivateKeys(nonEmptyProperties);
        Assert.assertTrue(keys.size() == 3);
    }

    @Test(expected = NullPointerException.class)
    public void derivateKeysShouldNotThrowNullPointer() {
        defaultKeyDerivator.derivateKeys(null);
    }

    @Test
    public void derivateKeysShouldContainThePropertiesKeys() {
        Set<String> keys = defaultKeyDerivator.derivateKeys(nonEmptyProperties);
        Assert.assertTrue(keys.contains("SETTING_BUTTON_OK.Text"));
        Assert.assertTrue(keys.contains("SETTING_BUTTON_OK.Tooltip"));
        Assert.assertTrue(keys.contains("SETTINGS_BUTTON_CANCEL.Text"));
    }
}
