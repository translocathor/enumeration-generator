# Notice
This Plugin is **NOT** yet available at Maven Central. If you want to use it, you will need to clone and build it yourself.

# Enumeration Generator
Maven plugin to generate Java enumerations from properties files using [Apache FreeMarker](http://freemarker.org/).

## Motivation
It is a common use case to load predefined key-value pairs from a properties file in a Java application. Whether you load application settings or localization text, you always need to provide a key for the value you want to load. Ususally you end up with code like this
```java
properties.get("KEY1");
```
Since it is a tedious task to add all keys from your properties file into your application, it is desirable to have a Java enumeration with all the keys generated.

## What does it do?
The Enumaration Generator uses a *.properties file from your Maven resource folder that looks like this
```properties
KEY1=Value1
KEY2=Value2
```
and generates a Java enumeration file in your projects source folder that contains the keys from the properties files as enumeration constants
```java
package org.example;

public enum StringKeys {
  KEY1,
  KEY2;
}
```
Now you can access the key-value pairs by using an enumeration constant like
```java
properties.get(StringKeys.KEY1.name());
```

## Usage
### Prerequirements
Since this is a Maven plugin, you need an existing Maven project. You also need to have an exising properties file from where the plugin can extract the keys.
### 1. Add the plugin to your pom.xml
Add the _enumeration-generator-maven-plugin_ to your **pom.xml**. The snippet below shows the 
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
