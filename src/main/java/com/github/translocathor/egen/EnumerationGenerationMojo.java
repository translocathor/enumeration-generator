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

import freemarker.template.TemplateException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FilenameUtils;

/**
 * Maven plugin Mojo that generates Java enums from properties files using
 * <a href="http://freemarker.org/">Apache FreeMarker</a>.
 *
 * @author Adrian Bingener
 */
@Mojo(name = "generate-enumeration", defaultPhase = LifecyclePhase.GENERATE_SOURCES, threadSafe = true)
public class EnumerationGenerationMojo extends AbstractMojo {

    /**
     * Parameter that defines the path to the template that is being used to
     * generate the output file. If the user doesn't specify a template, the
     * default enumeration template is used
     * {@link Defaults#ENUMERATION_TEMPLATE_FILENAME ENUMERATION_TEMPLATE_FILENAME}.
     */
    @Parameter(property = "templateFile", required = false)
    private File templateFile;

    /**
     * The name of the package that is used in the output file. Since the output
     * file is a Java <code>enum</code>, it needs to start with the package
     * declaration, such as <code>package com.yourname;</code>
     */
    @Parameter(property = "packageName", required = true)
    private String packageName;

    /**
     * The name for the Java <code>enum</code> as well as for the output file.
     * This string must be a valid Java identifier. The name of the enum must be
     * equal to the name of the output file
     */
    @Parameter(property = "enumName", required = true)
    private String enumName;

    /**
     * The path to the properties file that is used to derivate the enum
     * constants. Each key from the properties file will create an enum constant
     * in the output file.
     */
    @Parameter(property = "propertiesFile", required = true)
    private File propertiesFile;

    /**
     * The path to the output file where the final enum is saved to. The name of
     * this file must be equal to the enum name.
     */
    @Parameter(property = "outputFile", required = true)
    private File outputFile;

    /**
     * The key derivator that is used to get a set of keys from a given
     * properties file.
     */
    private final KeyDerivator<String> keyDerivator = new DefaultKeyDerivator();

    @Override
    public void execute() throws MojoExecutionException {

        // Validate properties file
        if (!propertiesFile.exists()) {
            throw new MojoExecutionException("The properties file '" + propertiesFile + "' does not exist, but is required");
        }

        // Create output file folder if it does not exist
        if (!outputFile.exists()) {
            outputFile.getParentFile().mkdirs();
        }

        // Check if output filename matches the enum name
        String filenameWithoutExtension = FilenameUtils.removeExtension(outputFile.getName());
        if (!filenameWithoutExtension.equals(enumName)) {
            getLog().warn("Output filename does not match the enum name");
        }

        // Use the file to load the properties instance
        Properties properties = new Properties();
        try (FileInputStream propertiesInputStream = new FileInputStream(propertiesFile)) {
            properties.load(propertiesInputStream);
        } catch (IOException ex) {
            throw new MojoExecutionException("Could not get input stream from properties file '" + propertiesFile + "'", ex);
        }

        // Get the key set from the loaded properties
        List<String> keys = keyDerivator.derivateKeys(properties);
        getLog().info(String.format("Loaded %s keys from properties file", keys.size()));

        // Check if the user provided a custom template file. If he did, we will
        // use the default template processor, otherwise the custom template
        // processor is used. These two template processors only differ in the
        // way that the template is loaded since the template loading is
        // different when loading it as plugin resource instead as resource of
        // the project which uses the plugin
        TemplateProcessor templateProcessor;
        if (templateFile == null || !templateFile.exists()) {
            templateProcessor = new DefaultTemplateProcessor(new File(Defaults.ENUMERATION_TEMPLATE_DIRECTORY), Defaults.ENUMERATION_TEMPLATE_FILENAME);
        } else {
            templateProcessor = new UserTemplateProcessor(templateFile.getParentFile(), templateFile.getName());
        }

        // Process the template with the data and write the result to the
        // writer
        try {
            getLog().info(String.format("Writing generated enum to %s", outputFile.getAbsolutePath()));
            Writer fileWriter = new FileWriter(outputFile);
            templateProcessor.process(packageName, enumName, keys, fileWriter);
        } catch (IOException ex) {
            throw new MojoExecutionException("Failed to access the output file'" + outputFile + "'", ex);
        } catch (TemplateException ex) {
            throw new MojoExecutionException("An exception occurred during template processing", ex);
        }
    }
}
