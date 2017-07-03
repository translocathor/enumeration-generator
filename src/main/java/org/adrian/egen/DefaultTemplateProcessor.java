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

/**
 *
 * @author Adrian Bingener
 */
public class DefaultTemplateProcessor extends AbstractFileTemplateProcessor {

    protected Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);

    public DefaultTemplateProcessor(File templateFile) {
        super(templateFile);
    }

    @Override
    public Template load(File templateFile) {

        try {
            // Load template from source folder
            configuration.setClassForTemplateLoading(this.getClass(), "/org/adrian/egen/");
            return configuration.getTemplate(templateFile.getName());
        } catch (IOException ex) {
            return null;
        }
    }
}
