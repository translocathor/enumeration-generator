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
package org.adrian.egen;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Adrian Bingener
 */
public abstract class AbstractTemplateProcessor implements TemplateProcessor {

    /**
     * The template that is loaded by this template processor.
     */
    protected Template template;
    protected File templateFile;

    public AbstractTemplateProcessor(File templateFile) {
        this.templateFile = templateFile;
    }

    @Override
    public void process(String packageName, String enumName, Set<String> keys, Writer writer) throws IOException, TemplateException {

        // Load the template from the file that the user provided in the
        // constructor
        this.template = load(templateFile);

        // Build the data-model
        Map<String, Object> data = new HashMap<>();
        data.put("packageName", packageName);
        data.put("enumName", enumName);
        data.put("keys", keys);

        // Write processed data to the provided writer
        template.process(data, writer);
        writer.flush();
    }
}
