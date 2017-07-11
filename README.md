[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

# Enumeration Generator
Maven plugin to generate Java enumerations from properties files using [Apache FreeMarker](http://freemarker.org/).

## Getting Started
These instructions show a simple example on how to install and configure the plugin. See the [Wiki](https://github.com/translocathor/enumeration-generator/wiki) for more configuration details.

### Prerequisites
Since this is a Maven plugin, you need a Maven project. You also need to have an exising properties file from where the plugin can extract the keys.

### Installing
Add the **enumeration-generator-maven-plugin** to your **pom.xml**.
```xml
...
<plugins>
  ..
  <plugin>
    <groupId>org.translocathor</groupId>
    <artifactId>enumeration-generator-maven-plugin</artifactId>
    <version>1.0-BETA</version>
    <executions>
      <execution>
        <id>generate-string-enum</id>
        <phase>generate-sources</phase>
        <goals>
          <goal>generate-enumeration</goal>
        </goals>
        <configuration>

          <!-- The name that is used as package name in the output java file -->
          <packageName>org.yourname.yourapp</packageName>

          <!-- The enum name identifier in the output java file -->
          <enumName>StringKeys</enumName>

          <!-- The properties file whose keys are used to generate the java enum file -->
          <propertiesFile>src/main/resources/bundles/strings.properties</propertiesFile>

          <!-- The target for the generated java enum file -->
          <outputFile>src/main/java/org/yourname/yourapp/StringKeys.java</outputFile>
        </configuration>
      </execution>
    </executions>
  </plugin>
<plugins>
...
```
### Execute
The plugin is executed during the _generate-sources_ phase by default. So you can simply build the project to execute the plugin. If everything is configured correctly, the Java enum should have been generated in the file you specified in the *outputFile* parameter.

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
