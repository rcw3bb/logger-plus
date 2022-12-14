# Logger Plus

Additional functionality to logging. 

## Requires

* Java 17

## Usage

1. Add the following **maven** dependency to your project:

   | Property    | Value               |
   | ----------- | ------------------- |
   | Group ID    | xyz.ronella.logging |
   | Artifact ID | logger-plus         |
   | Version     | 1.2.0               |

   > Using gradle, this can be added as a dependency entry like the following:
   >
   > ```groovy
   > implementation 'xyz.ronella.logging:logger-plus:1.2.0'
   > ```

2. Include the following to your **module-info.java**:

   ```java
   requires xyz.ronella.logging.logger.plus;
   ```

3. Within your class, you can create an instance **LoggerPlus** like the following:

   ```java
   private final static LoggerPlus LOGGER_PLUS = new LoggerPlus(LoggerFactory.getLogger(Main.class));
   ```

   > The **LoggerFactory** class here must be from **SLF4J**.

## Available Log Level Methods

There are **two sets** of log level methods available. One that accepts message as **String parameter** and the one that accepts an instance of **Supplier<String>** to generate the message.

| String Parameter | Instance of Supplier<String> as Parameter |
| ---------------- | ----------------------------------------- |
| error(String)    | error(Supplier<String>)                   |
| warn(String)     | warn(Supplier<String>)                    |
| info(String)     | info(Supplier<String>)                    |
| debug(String)    | debug(Supplier<String>)                   |
| trace(String)    | trace(Supplier<String>)                   |

> The more efficient sets of method to use are the ones that accepts an instance of Supplier<String> that generates the message since it already called the enabled methods *(i.e. isErrorEnabled(), isWarnEnabled(), isInfoEnabled(), isDebugEnabled() and isTraceEnabled())*.

## Formatted Message

Use the following syntax for the formatted message.

```
<LOG_LEVEL_METHOD>(String format, Object ... values)
```

> LOG_LEVEL_METHOD can be one of the following: debug, error, info, trace, warn.

**Formatting** is done using the **String.format method**. Hence you can use all the formatting values available with that method.

**Example**

```java
logger.info("Hello %s", "world")
```

## Group the Log by Name

Having a log entries that you can identify what group *(or part of the codes)* wrote them is very helpful. For example, if you have an **accept** method that you wanted it's log entries trackable. You can do it like the following:

```java
public void accept(Boolean mustProvision) {
    try(var gLOG = LOGGER_PLUS.groupLog("accept")) {
        /*
         *
         */
        gLOG.info("Processing");
        /*
         *
         */
    }
}
```

Expect an output similar to the following:

```
22:26:53.237 DEBUG accept [BEGIN]
22:26:53.239 INFO  accept Processing
22:26:54.824 DEBUG accept [END]
```

> Notice that all the log entries made by the accept method has the **group name included** and with **[BEGIN]** and **[END]** marker. The markers will be at **DEBUG** level. If you wanted to remove the markers, you pass **false** as a second argument to the **groupLog** method invocation, like the following:
>
> ```java
> LOGGER_PLUS.groupLog("accept", false)
> ```

## The getStackTraceAsString(Exception) Method

Normally, we wanted to catch the actual error stack trace as string into the log file. This can be simplified by the getStackTraceAsString method that accepts an instance of Exception. See the sample usage as follows:

```java
LOGGER_PLUS.error(LOGGER_PLUS.getStackTraceAsString(exception));
```

## The getLogger() Method

If you need some specific functionality of the Logger, you can get an instance of it using the getLogger() method. The instance that you will receive is the one you've passed from the constructor.

## Sample Gradle Project

### build.gradle

```groovy
plugins {
    id 'java'
}

group 'xyz.ronella.sample'
version '1.0.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'xyz.ronella.logging:logger-plus:1.2.0'
    implementation 'org.apache.logging.log4j:log4j-slf4j2-impl:2.19.0'
    implementation 'org.apache.logging.log4j:log4j-api:2.19.0'
    implementation 'org.apache.logging.log4j:log4j-core:2.19.0'
}
```

### log4j2.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="INFO">
    <Properties>
        <Property name="filename">logs/logger-plus.log</Property>
    </Properties>
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d [%t] %-5level %logger{36} - %msg%n"/>
        </Console>
        <RollingFile name="File" fileName="${filename}" filePattern="logs/$${date:yyyy-MM}/logger-plus-%d{MM-dd-yyyy}-%i.log.gz">
            <PatternLayout pattern="%d [%t] %-5level %logger{36} - %msg%n"/>
            <DefaultRolloverStrategy max="10"/>
            <Policies>
                <TimeBasedTriggeringPolicy />
                <SizeBasedTriggeringPolicy size="10 MB"/>
            </Policies>
        </RollingFile>
    </Appenders>
    <Loggers>
        <Root level="TRACE">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="File"/>
        </Root>
    </Loggers>
</Configuration>
```

### module-info.java

```java
module logger.plus.test.main {
    requires xyz.ronella.logging.logger.plus;
    requires org.apache.logging.log4j;
}
```

### Main.java

```java
package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.ronella.logging.LoggerPlus;

public class Main {

    private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private final static LoggerPlus LOGGER_PLUS = new LoggerPlus(LOGGER);

    public static void main(String ... args) {
        final var message = "An info logger";
        LOGGER_PLUS.info(message::toString);   //Using the supplier argument.
        LOGGER_PLUS.info("Hello %s", "world"); //Using arguments with format.
    }
}
```

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## [Build](BUILD.md)

## [Changelog](CHANGELOG.md)

## Author

* Ronaldo Webb
