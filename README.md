[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
# Notice
This plugin is **NOT** yet available at the the Maven Central repository. If you want to use it, you will need to clone and build it yourself. The plugin will be deployed to  Maven Central as soon as possible.
  
# Enumeration Generator
Maven plugin to generate Java enumerations from properties files using [Apache FreeMarker](http://freemarker.org/).

## Getting Started
These instructions show a simple example on how to install and configure the plugin. See the [Wiki](https://github.com/translocathor/enumeration-generator/wiki) for more configuration details.

### Prerequisites
Since this is a Maven plugin, you need a Maven project. You also need to have an exising properties file from where the plugin can extract the keys.

### Installing
#### Add the dependency
Add the **enumeration-generator-maven-plugin** as dependency to your dependencies section in your **pom.xml**.
```xml
...
<dependencies>
  ...
    <dependency>
        <groupId>com.github.translocathor</groupId>
        <artifactId>enumeration-generator-maven-plugin</artifactId>
        <version>1.0</version>
    </dependency>
</dependencies>
```
#### Add the plugin
Add the **enumeration-generator-maven-plugin** to the plugin section in your **pom.xml**.
```xml
...
<plugins>
  ..
  <plugin>
    <groupId>com.github.translocathor</groupId>
    <artifactId>enumeration-generator-maven-plugin</artifactId>
    <version>1.0</version>
    <executions>
      <execution>
        <id>generate-string-enum</id>
        <goals>
            <goal>generate-enumeration</goal>
        </goals>
        <configuration>
          <!--
              Package name that is used for the package
              declaration in the generated enum.
          -->
          <packageName>com.github.translocathor.egen.sample</packageName>
          <!--
              The name of the enum in the generated Java file.
          -->
          <enumName>StringKeys</enumName>
          <!--
              The properties file whose keys are used to
              derivate the enum constants.
          -->
          <propertiesFile>src/main/resources/bundles/de_DE_Strings.properties</propertiesFile>
          <!--
              The output file into which the generated Java
              file is written. If the specified output folder
              does not exist it will be created. If the file
              already exists it will be overwritten.
          -->
          <outputFile>src/main/java/com/github/translocathor/egen/sample/StringKeys.java</outputFile>
        </configuration>
      </execution>
    </executions>
  </plugin>
<plugins>
...
```
### Execute
The plugin runs in the _generate-sources_ phase by default and thus will be executed during the build automatically. If everything is configured correctly, the Java enum should be generated in the file you specified in the *outputFile* parameter.

License
=======
Copyright 2017 Adrian Bingener

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
