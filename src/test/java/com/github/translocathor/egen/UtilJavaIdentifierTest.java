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

import com.github.translocathor.egen.Util;
import java.util.Arrays;
import java.util.Collection;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Adrian Bingener
 */
@RunWith(Parameterized.class)
public class UtilJavaIdentifierTest {

    /**
     * The template processor that is being tested.
     */
    private final String identifier;
    private final boolean valid;

    public UtilJavaIdentifierTest(String identifier, boolean valid) {
        this.identifier = identifier;
        this.valid = valid;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"k", true},
            {"key", true},
            {"key1", true},
            {"KEY", true},
            {"KEY1", true},
            {"KEY1_", true},
            {"KEY1_KEY", true},
            {"Key", true},
            {"ß", true},
            {"", false},
            {" ", false},
            {"§", false},
            {"%", false},
            {"&", false},
            {"/", false},
            {"(", false},
            {")", false},
            {"=", false},
            {"{", false},
            {"}", false},
            {"}", false},
            {"?", false},
            {"\\", false},
            {"#", false},
            {"'", false},
            {"+", false},
            {"*", false},
            {"~", false},});
    }

    @Test
    public void identifierShouldBeValid() throws Exception {
        boolean isValid = Util.isValidJavaIdentifier(identifier);
        Assert.assertEquals(String.format("Identifier %s validity should be %s but was %s", new Object[]{identifier, valid, isValid}), valid, isValid);
    }
}
