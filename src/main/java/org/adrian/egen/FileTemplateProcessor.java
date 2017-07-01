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

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Adrian Bingener
 */
public class FileTemplateProcessor implements TemplateProcessor {

    protected final File templateFile;

    /**
     * The freemarker configuration object. Since the official freemarker
     * documentation says
     * <a href="http://freemarker.org/docs/pgui_quickstart_createconfiguration.html">configuration
     * instances meant to be application-level singletons</a>, this is created
     * once per class.
     *
     */
    protected Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);

    public FileTemplateProcessor(File templateFile) {
        this.templateFile = templateFile;

    }

    @Override
    public void process(String packageName, String enumName, Set<String> keys, Writer writer) throws IOException, TemplateException {

        //Load template from source folder
        configuration.setDirectoryForTemplateLoading(templateFile.getParentFile());
        Template template = configuration.getTemplate(templateFile.getName());

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
