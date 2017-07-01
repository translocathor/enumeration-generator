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
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;
import org.codehaus.plexus.util.FileUtils;

/**
 *
 * @author Adrian Bingener
 */
@Mojo(name = "generate-enumeration", defaultPhase = LifecyclePhase.GENERATE_SOURCES, threadSafe = true)
public class EnumerationGenerationMojo extends AbstractMojo {

    /**
     * Parameter that defines the path to template that is being used to
     * generate the output file. If the user doesn't specify a template, the
     * default enumeration template is used
     * {@link Defaults.ENUMERATION_TEMPLATE_FILENAME}.
     */
    @Parameter(property = "templatePath", required = true)
    private File templatePath;

    /**
     * The name of the package that is used in the output file. Since the output
     * file is a Java <code>enum</code>, it needs to start with the package
     * declaration, such as <code>package com.yourname;</code>
     */
    @Parameter(property = "packageName", required = true)
    private String packageName;

    /**
     * The name for the Java <code>enum</code> as well as for the output file.
     * This string must be a valid Java identifier
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
            throw new MojoExecutionException("The file '" + propertiesFile + "' does not exist, but is required");
        }

        // Create output file folder
        if (!outputFile.exists()) {
            outputFile.getParentFile().mkdirs();
        }

        // Check if output filename matches the enum name
        String filenameWithoutExtension = FilenameUtils.removeExtension(outputFile.getName());
        if (!filenameWithoutExtension.equals(enumName)) {
            getLog().warn("Output filename does not match the enum name");
        }

        // TODO: Check if packageName is a valid Java package name
        // TODO: Check if enumName is a valid Java identifier
        // Use the file to load the properties instance
        Properties properties = new Properties();
        try (FileInputStream propertiesInputStream = new FileInputStream(propertiesFile)) {
            properties.load(propertiesInputStream);
        } catch (IOException ex) {
            throw new MojoExecutionException("Could not get input stream from properties file '" + propertiesFile + "'", ex);
        }

        // Get the key set from the loaded properties
        Set<String> keys = keyDerivator.derivateKeys(properties);

        try {
            TemplateProcessor templateProcessor = new FileTemplateProcessor(templatePath);
            Writer fileWriter = new FileWriter(outputFile);
            templateProcessor.process(packageName, enumName, keys, fileWriter);
        } catch (IOException ex) {
            throw new MojoExecutionException("Failed to access the output file'" + outputFile + "'", ex);
        } catch (TemplateException ex) {
            throw new MojoExecutionException("An exception occurred during template processing", ex);
        }
    }
}
