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

import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A template processor that loads templates from files. This class only
 * implements the processing of an already loaded template and leaves the
 * implementation of the loading to subclasses.
 *
 * @author Adrian Bingener
 */
public abstract class AbstractFileTemplateProcessor implements TemplateProcessor {

    /**
     * The template that is loaded by this template processor.
     */
    protected String templateFileName;

    /**
     * Creates a new instance of {@link AbstractFileTemplateProcessor}. The
     * given template file name defines the template which is loaded before it
     * is processed.
     *
     * @param templateFileName The template name that is being processed
     */
    public AbstractFileTemplateProcessor(String templateFileName) {
        this.templateFileName = templateFileName;
    }

    @Override
    public void process(String packageName, String enumName, List<String> keys, Writer writer) throws IOException, TemplateException {

        // Load the template from the file that the user provided in the
        // constructor
        Optional<Template> optionalTemplate = load(templateFileName);

        // Build the data-model
        Map<String, Object> data = new HashMap<>();
        data.put(Defaults.TEMPLATE_VARIABLE_PACKAGE_NAME, packageName);
        data.put(Defaults.TEMPLATE_VARIABLE_ENUM_NAME, enumName);
        data.put(Defaults.TEMPLATE_VARIABLE_KEYS, keys);

        // Write processed data to the provided writer
        if (optionalTemplate.isPresent()) {
            optionalTemplate.get().process(data, writer);
            writer.flush();
        }
    }
}
