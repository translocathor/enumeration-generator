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

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * Processes the default enum template, which is used if the user did not
 * provide his own template.
 *
 * @author Adrian Bingener
 */
public class DefaultTemplateProcessor extends AbstractFileTemplateProcessor {

    /**
     * The configuration which is required by FreeMarker to load templates.
     */
    protected Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);

    /**
     * Creates a new instance of {@link DefaultTemplateProcessor}. The given
     * template file defines the template which is loaded before it is
     * processed. This template file must be located in the resource folder of
     * this plugin, otherwise the loading will fail. If you want to load a
     * template that was provided by the user of this plugin, use the
     * {@link UserTemplateProcessor}.
     *
     * @param templateFile The template that is being processed
     */
    public DefaultTemplateProcessor(File templateFile) {
        super(templateFile);
    }

    @Override
    public Optional<Template> load(File templateFile) {

        try {
            // Load template from source folder
            configuration.setClassForTemplateLoading(this.getClass(), "/org/adrian/egen/");
            return Optional.ofNullable(configuration.getTemplate(templateFile.getName()));
        } catch (IOException ex) {
            // TODO: Get a logger and print this exception
            return Optional.empty();
        }
    }
}
