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

/**
 * Defines static accessible default values.
 *
 * @author Adrian Bingener
 */
public class Defaults {

    /**
     * The default template that is used to generate an enumeration.
     */
    public static final String ENUMERATION_TEMPLATE_FILENAME = "enumeration-template-default.ftl";
    /**
     * The directory where the default template is located.
     */
    public static final String ENUMERATION_TEMPLATE_DIRECTORY = "/org/translocathor/egen/";
    /**
     * The name of the variable that is replaced by the package name that was
     * specified.
     */
    public static final String TEMPLATE_VARIABLE_PACKAGE_NAME = "packageName";
    /**
     * The name of the variable that is replaced by the enum name that was
     * specified.
     */
    public static final String TEMPLATE_VARIABLE_ENUM_NAME = "enumName";
    /**
     * The name of the variable that is replaced by the enum keys that where
     * specified.
     */
    public static final String TEMPLATE_VARIABLE_KEYS = "enumKeys";

}
