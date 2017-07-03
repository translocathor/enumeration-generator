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
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
    protected File templateFile;

    /**
     * Creates a new instance of {@link AbstractFileTemplateProcessor}. The
     * given template file defines the template which is loaded before it is
     * processed.
     *
     * @param templateFile The template that is being processed
     */
    public AbstractFileTemplateProcessor(File templateFile) {
        this.templateFile = templateFile;
    }

    @Override
    public void process(String packageName, String enumName, Set<String> keys, Writer writer) throws IOException, TemplateException {

        // Load the template from the file that the user provided in the
        // constructor
        Optional<Template> optionalTemplate = load(templateFile);

        // Build the data-model
        // TODO: Replace with Constants
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
