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

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test for the {@link DefaultKeyDerivator} class.
 *
 * @author Adrian Bingener
 */
public class KeyDerivatorTest extends AbstractPropertiesFixtureTest {

    /**
     * The key derivator that is tested.
     */
    private KeyDerivator defaultKeyDerivator;

    @Before
    public void setUpDerivator() {
        // If there will be more instances this will be converted to a
        // parameterized test with multiple KeyDerivator instances to test
        defaultKeyDerivator = new DefaultKeyDerivator();
    }

    @Test
    public void derivateKeysFromEmptyPropertiesShouldBeEmpty() {
        List<String> keys = defaultKeyDerivator.derivateKeys(propertiesEmpty);
        Assert.assertTrue("Expected derived key list to be empty", keys.isEmpty());
    }

    @Test
    public void derivateKeysFromSimplePropertiesShouldNotBeEmpty() {
        List<String> keys = defaultKeyDerivator.derivateKeys(propertiesSimpleValid);
        Assert.assertFalse(keys.isEmpty());
    }

    @Test
    public void derivateKeysFromComplexPropertiesShouldNotBeEmpty() {
        List<String> keys = defaultKeyDerivator.derivateKeys(propertiesComplexValid);
        Assert.assertFalse(keys.isEmpty());
    }

    @Test
    public void derivateKeysFromComplexPropertiesShouldMatchInSize() {
        List<String> keys = defaultKeyDerivator.derivateKeys(propertiesComplexValid);
        Assert.assertTrue(keys.size() == propertiesComplexValid.keySet().size());
    }

    @Test
    public void derivateKeysFromSimplePropertiesShouldMatchInSize() {
        List<String> keys = defaultKeyDerivator.derivateKeys(propertiesSimpleValid);
        Assert.assertTrue(keys.size() == propertiesSimpleValid.keySet().size());
    }
    
    @Test
    public void derivateKeysFromInvalidPropertiesShouldBeEmpty() {
        List<String> keys = defaultKeyDerivator.derivateKeys(propertiesInvalid);
        Assert.assertTrue(keys.isEmpty());
    }
    
    @Test(expected = NullPointerException.class)
    public void derivateKeysOnNullRefShouldNotThrowNullpointer() {
        defaultKeyDerivator.derivateKeys(null);
    }

    @Test
    public void derivateKeysShouldContainThePropertiesKeys() {
        List<String> keys = defaultKeyDerivator.derivateKeys(propertiesComplexValid);

        // Check if all keys from the properties are contained in the derived
        // key list
        propertiesComplexValid.keySet().forEach(key -> {
            Assert.assertTrue(keys.contains(String.valueOf(key)));
        });
    }
}
