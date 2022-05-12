# Logger Plus

Additional functionality to logging. 

## Requires

* Java 11
* SLF4J 1.8+

## Usage

1. Add the following **maven** dependency to your project:

   | Property    | Value               |
   | ----------- | ------------------- |
   | Group ID    | xyz.ronella.logging |
   | Artifact ID | logger-plus         |
   | Version     | 1.1.0               |

   > Using gradle, this can be added as a dependency entry like the following:
   >
   > ```groovy
   > compile group: 'xyz.ronella.logging', name: 'logger-plus', version: '1.1.0'
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

> You don't need to call the corresponding **enabled methods** of the preceding log level methods *(i.e. isErrorEnabled(), isWarnEnabled(), isInfoEnabled(), isDebugEnabled() and isTraceEnabled())*. Call to these methods were already done for you. The more efficient sets of method to use are the ones that accepts an instance of Supplier<String> that generates the message.

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

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## [Build](BUILD.md)

## [Changelog](CHANGELOG.md)

## Author

* Ronaldo Webb
