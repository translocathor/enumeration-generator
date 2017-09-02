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
public class DefaultTemplateProcessor extends AbstractTemplateProcessor {

    /**
     * The configuration which is required by FreeMarker to load templates.
     */
    protected Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
    /**
     * The directory from where the template is loaded.
     */
    private final File templateDirectory;

    /**
     * Creates a new instance of {@link DefaultTemplateProcessor}. The given
     * <code>templateFileName</code> defines the template which is loaded before
     * it is processed. This template file must be located in the resource
     * folder of this plugin, otherwise the loading will fail. Further you need
     * to provide the <code>templateDirectory</code>, which defines the folder
     * from where the template is loaded. If you want to load a template that
     * was provided by the user of this plugin, use the
     * {@link UserTemplateProcessor}.
     *
     * @param templateDirectory The directory from where the template is loaded
     * @param templateFileName The template that is being processed
     */
    public DefaultTemplateProcessor(File templateDirectory, String templateFileName) {
        super(templateFileName);
        this.templateDirectory = templateDirectory;
    }

    @Override
    public Optional<Template> load(String templateFileName) {

        try {
            // Load template from source folder
            configuration.setClassForTemplateLoading(this.getClass(), templateDirectory.getPath());
            return Optional.ofNullable(configuration.getTemplate(templateFileName));
        } catch (IOException ex) {
            ex.printStackTrace();
            // TODO: Get a logger and print this exception
            return Optional.empty();
        }
    }
}
