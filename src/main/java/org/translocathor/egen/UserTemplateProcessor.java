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

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 *
 * @author Adrian Bingener
 */
public class UserTemplateProcessor extends AbstractFileTemplateProcessor {

    /**
     * The configuration which is required by FreeMarker to load templates.
     */
    protected Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
    private final File templateDirectory;

    /**
     * Creates a new instance of {@link UserTemplateProcessor}. The given
     * template file defines the template which is loaded before it is
     * processed. This template file must be located in the resource folder of
     * the application which uses this plugin, otherwise the loading will fail.
     * If you want to load the default template in case the user did not provide
     * any, use the {@link DefaultTemplateProcessor}.
     *
     * @param templateDirectory
     * @param templateFileName The template that is being processed
     */
    public UserTemplateProcessor(File templateDirectory, String templateFileName) {
        super(templateFileName);
        this.templateDirectory = templateDirectory;
    }

    @Override
    public Optional<Template> load(String templateFileName) {

        try {
            // Load template from source folder
            configuration.setDirectoryForTemplateLoading(templateDirectory);
            return Optional.of(configuration.getTemplate(templateFileName));
        } catch (IOException ex) {
            // TODO: Get a logger and print this exception
            ex.printStackTrace();
            return Optional.empty();
        }
    }
}
