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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

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
    @Parameter(property = "templatePath", required = false)
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
     * constants. Each key from the properties file will create a enum constant
     * in the output file.
     */
    @Parameter(property = "propertiesFile", required = true)
    private File propertiesFile;

    @Override
    public void execute() throws MojoExecutionException {
    }
}
