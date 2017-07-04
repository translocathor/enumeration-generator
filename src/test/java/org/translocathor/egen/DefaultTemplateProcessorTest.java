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

import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Adrian Bingener
 */
public class DefaultTemplateProcessorTest extends PropertiesFixtureTest {

    /**
     * The template processor that is being tested.
     */
    private TemplateProcessor templateProcessor;

    @Before
    public void setUpProcessor() {
        // Create class instance to test
        templateProcessor = new DefaultTemplateProcessor(new File(Defaults.ENUMERATION_TEMPLATE_DIRECTORY), Defaults.ENUMERATION_TEMPLATE_FILENAME);
    }

    @Test
    public void outputShouldMatchSimpleEnum() throws Exception {
        // Get the set of keys from the simple properties
        List<String> keys = new ArrayList<>();
        propertiesSimpleKeys.keySet().forEach((object) -> keys.add(String.valueOf(object)));

        // Needs to be done, since the properties key set is not returned in the
        // order the values are stored in the file. This workaround requires the
        // reference file, that is used for comparison, to be sorted as well.
        Collections.sort(keys);

        // Process the default template using the default template processor and
        // the simple keys. The output is writter to the given writer.
        Writer stringWriter = new StringWriter();
        templateProcessor.process("org.translocathor.egen.test", "StringKeys", keys, stringWriter);

        // Load the file that contains the expected content for the simple
        // properties
        InputStream resourceAsStream = getClass().getResourceAsStream("/org/translocathor/egen/fixtures/PropertiesSimpleKeysExpected.txt");
        String expected = IOUtils.toString(resourceAsStream, "UTF-8");

        Assert.assertTrue("Expected enums to be equal", expected.equals(stringWriter.toString()));
    }
}
