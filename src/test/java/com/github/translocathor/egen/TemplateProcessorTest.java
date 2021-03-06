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

import com.github.translocathor.egen.Defaults;
import com.github.translocathor.egen.UserTemplateProcessor;
import com.github.translocathor.egen.TemplateProcessor;
import com.github.translocathor.egen.DefaultTemplateProcessor;
import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author Adrian Bingener
 */
@RunWith(Parameterized.class)
public class TemplateProcessorTest extends AbstractPropertiesFixtureTest {

    /**
     * The template processor that is being tested.
     */
    private final TemplateProcessor templateProcessor;

    public TemplateProcessorTest(TemplateProcessor templateProcessor) {
        this.templateProcessor = templateProcessor;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {
                new UserTemplateProcessor(new File("src/test/resources/com/github/translocathor/egen/fixtures/"), "enumeration-template-user.ftl")
            },
            {
                new DefaultTemplateProcessor(new File(Defaults.ENUMERATION_TEMPLATE_DIRECTORY), Defaults.ENUMERATION_TEMPLATE_FILENAME)
            }
        });
    }

    @Test
    public void outputShouldMatchSimpleEnum() throws Exception {
        // Get the set of keys from the simple properties
        List<String> keys = new ArrayList<>();
        propertiesSimpleValid.keySet().forEach((object) -> keys.add(String.valueOf(object)));

        // Needs to be done, since the properties key set is not returned in the
        // order the values are stored in the file. This workaround requires the
        // reference file, that is used for comparison, to be sorted as well.
        Collections.sort(keys);

        // Process the default template using the default template processor and
        // the simple keys. The output is writter to the given writer.
        Writer stringWriter = new StringWriter();
        templateProcessor.process("com.github.translocathor.egen.test", "StringKeys", keys, stringWriter);

        // Load the file that contains the expected content for the simple
        // properties
        InputStream resourceAsStream = getClass().getResourceAsStream("/com/github/translocathor/egen/fixtures/PropertiesSimpleExpected.txt");
        String expected = IOUtils.toString(resourceAsStream, "UTF-8");

        Assert.assertTrue("Expected enums to be equal", expected.equals(stringWriter.toString()));
    }

    @Test
    public void outputShouldMatchComplexEnum() throws Exception {
        // Get the set of keys from the simple properties
        List<String> keys = new ArrayList<>();
        propertiesComplexValid.keySet().forEach((object) -> keys.add(String.valueOf(object)));

        // Needs to be done, since the properties key set is not returned in the
        // order the values are stored in the file. This workaround requires the
        // reference file, that is used for comparison, to be sorted as well.
        Collections.sort(keys);

        // Process the default template using the default template processor and
        // the simple keys. The output is writter to the given writer.
        Writer stringWriter = new StringWriter();
        templateProcessor.process("com.github.translocathor.egen.test", "StringKeys", keys, stringWriter);

        // Load the file that contains the expected content for the simple
        // properties
        InputStream resourceAsStream = getClass().getResourceAsStream("/com/github/translocathor/egen/fixtures/PropertiesComplexExpected.txt");
        String expected = IOUtils.toString(resourceAsStream, "UTF-8");

        Assert.assertTrue("Expected enums to be equal", expected.equals(stringWriter.toString()));
    }

    @Test
    public void outputShouldMatchEmptyEnum() throws Exception {
        // Get the set of keys from the simple properties
        List<String> keys = new ArrayList<>();
        propertiesEmpty.keySet().forEach((object) -> keys.add(String.valueOf(object)));

        // Needs to be done, since the properties key set is not returned in the
        // order the values are stored in the file. This workaround requires the
        // reference file, that is used for comparison, to be sorted as well.
        Collections.sort(keys);

        // Process the default template using the default template processor and
        // the simple keys. The output is writter to the given writer.
        Writer stringWriter = new StringWriter();
        templateProcessor.process("com.github.translocathor.egen.test", "StringKeys", keys, stringWriter);

        // Load the file that contains the expected content for the simple
        // properties
        InputStream resourceAsStream = getClass().getResourceAsStream("/com/github/translocathor/egen/fixtures/PropertiesEmptyExpected.txt");
        String expected = IOUtils.toString(resourceAsStream, "UTF-8");

        Assert.assertTrue("Expected enums to be empty", expected.equals(stringWriter.toString()));
    }
}
