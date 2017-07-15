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
import java.util.List;
import java.util.Optional;

/**
 * The template processor interface to load and process FreeMarker
 * {@link Template Templates} to generate a Java enum with the parameters
 * provided by the
 * {@link #process(java.lang.String, java.lang.String, java.util.List, java.io.Writer) process}
 * method.
 *
 * @author Adrian Bingener
 */
public interface TemplateProcessor {

    /**
     * Uses the given file to load the template. This method is called by
     * {@link #process(java.lang.String, java.lang.String, java.util.List, java.io.Writer)  process}
     * and may return an empty Optional if an error occurred during the loading.
     *
     * @param templateFileName The file that specifies the template
     * @return An Optional containing the loaded template or an empty Optional
     * if an error occurred during the loading
     */
    Optional<Template> load(String templateFileName);

    /**
     * Processes the template provided by the {@link #load(java.lang.String) load}
     * method and writes the result into the given writer. All required
     * parameters for the template are given as arguments.
     *
     * @param packageName The name of the package which is used for the package
     * declaration in the generated enum like this
     * <code>package ${packageName};</code>
     * @param enumName The name of the generated enum which is used in the enum
     * declaration like this <code>public enum ${enumName} {</code>
     * @param enumKeys A set that defines the enum constants. Each string of
     * this set is converted to an enum constants as is it.
     * @param writer A writer that is used to write the result of the template
     * processing. The result is the processed template whose variables are
     * replaced by actual values replaced with the
     *
     * @throws IOException if an I/O exception occurs during writing to the
     * writer.
     * @throws TemplateException If an exception occurs during template
     * processing
     */
    void process(String packageName, String enumName, List<String> enumKeys, Writer writer) throws IOException, TemplateException;
}
