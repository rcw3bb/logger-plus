package xyz.ronella.logging;

import org.slf4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Add functionality to logging.
 *
 * @author Ron Webb
 * @since 2019-11-27
 */
public class LoggerPlus {

    private Logger logger;

    /**
     * Creates an instance of LoggerPlus.
     * @param logger Accepts an instance of Logger.
     */
    public LoggerPlus(Logger logger) {
        this.logger = logger;
    }

    /**
     * Groups logging output by method.
     *
     * @author Ron Webb
     * @since 2019-11-27
     */
    public final static class MethodLogger implements AutoCloseable {

        private LoggerPlus logPlus;
        private String methodName;

        private boolean withHeader;

        private BiFunction<String, String, String> messageBlock = (String ___methodName, String ___message) ->
                new StringJoiner(" ").add(___methodName).add(___message).toString();

        /**
         * Create an instance of MethodLogger.
         *
         * @param methodName The method name to associate the log messages.
         * @param logPlus An instance of LoggerPlus.
         * @param withHeader Place a log header message in each method class.
         */
        public MethodLogger(String methodName, LoggerPlus logPlus, boolean withHeader) {
            this.methodName = methodName;
            this.logPlus = logPlus;
            if (withHeader) {
                this.logPlus.debug(() -> messageBlock.apply(this.methodName, "[BEGIN]"));
            }
        }

        /**
         * Add a closing header when the method exits.
         */
        @Override
        public void close() {
            if (withHeader) {
                logPlus.debug(() -> messageBlock.apply(methodName, "[END]"));
            }
        }

        /**
         * Accepts a debug message at method level.
         * @param message The message.
         */
        public void debug(final String message) {
            debug(()-> message);
        }

        /**
         * Accepts a supplier of a message at method level.
         * @param message The supplier of message.
         */
        public void debug(Supplier<String> message) {
            logPlus.debug(() -> messageBlock.apply(methodName, message.get()));
        }

        /**
         * Accepts an info message at method level.
         * @param message The info message.
         */
        public void info(final String message) {
            info(()-> message);
        }

        /**
         * Accepts a supplier of a message at method level.
         * @param message The supplier of message.
         */
        public void info(Supplier<String> message) {
            logPlus.info(() -> messageBlock.apply(methodName, message.get()));
        }

        /**
         * Accepts an error message at method level.
         * @param message The error message.
         */
        public void error(final String message) {
            error(()-> message);
        }

        /**
         * Accepts a supplier of a message at method level.
         * @param message The supplier of message.
         */
        public void error(Supplier<String> message) {
            logPlus.error(() -> messageBlock.apply(methodName, message.get()));
        }

        /**
         * Accepts a warning message at method level.
         * @param message The warning message.
         */
        public void warn(final String message) {
            warn(()-> message);
        }

        /**
         * Accepts a supplier of a message at method level.
         * @param message The supplier of message.
         */
        public void warn(Supplier<String> message) {
            logPlus.warn(() -> messageBlock.apply(methodName, message.get()));
        }

        /**
         * Accepts a trace message at method level.
         * @param message The trace message.
         */
        public void trace(final String message) {
            trace(()-> message);
        }

        /**
         * Accepts a supplier of a message at method level.
         * @param message The supplier of message.
         */
        public void trace(Supplier<String> message) {
            logPlus.trace(() -> messageBlock.apply(methodName, message.get()));
        }
    }

    /**
     * Return the instance of the logger used.
     *
     * @return An instance of Logger.
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Accepts a debug message.
     * @param message The message.
     */
    public void debug(final String message) {
        debug(()-> message);
    }

    /**
     * Accepts a supplier of a message.
     * @param message The supplier of message.
     */
    public void debug(Supplier<String> message) {
        if (logger.isDebugEnabled()) {
            Optional.ofNullable(message).ifPresent(___message -> logger.debug(___message.get()));
        }
    }

    /**
     * Accepts an info message.
     * @param message The info message.
     */
    public void info(final String message) {
        info(()-> message);
    }

    /**
     * Accepts a supplier of a message.
     * @param message The supplier of message.
     */
    public void info(Supplier<String> message) {
        if (logger.isInfoEnabled()) {
            Optional.ofNullable(message).ifPresent(___message -> logger.info(___message.get()));
        }
    }

    /**
     * Accepts an error message.
     * @param message The error message.
     */
    public void error(final String message) {
        error(()-> message);
    }

    /**
     * Accepts a supplier of a message.
     * @param message The supplier of message.
     */
    public void error(Supplier<String> message) {
        if (logger.isErrorEnabled()) {
            Optional.ofNullable(message).ifPresent(___message -> logger.error(___message.get()));
        }
    }

    /**
     * Accepts a warning message.
     * @param message The warning message.
     */
    public void warn(final String message) {
        warn(()-> message);
    }

    /**
     * Accepts a supplier of a message.
     * @param message The supplier of message.
     */
    public void warn(Supplier<String> message) {
        if (logger.isWarnEnabled()) {
            Optional.ofNullable(message).ifPresent(___message -> logger.warn(___message.get()));
        }
    }

    /**
     * Accept a trace message.
     * @param message The trace message.
     */
    public void trace(final String message) {
        trace(()-> message);
    }

    /**
     * Accepts a supplier of a message.
     * @param message The supplier of message.
     */
    public void trace(Supplier<String> message) {
        if (logger.isTraceEnabled()) {
            Optional.ofNullable(message).ifPresent(___message -> logger.trace(___message.get()));
        }
    }

    /**
     * Return an instance of MethodLogger that can be used log messages associated with the method.
     * @param methodName The method name.
     * @param withHeader Place a log header message in each method class.
     * @return An instance of MethodLogger.
     */
    public MethodLogger logByMethodCall(String methodName, boolean withHeader) {
        return new MethodLogger(methodName, this, withHeader);
    }

    /**
     * Return an instance of MethodLogger that can be used log messages associated with the method.
     * @param methodName The method name.
     * @return An instance of MethodLogger.
     */
    public MethodLogger logByMethodCall(String methodName) {
        return logByMethodCall(methodName, true);
    }

    /**
     * Capture the stacktrace as string from an exception.
     *
     * @param exception An instance of exception.
     * @return The string equivalent of stacktrace.
     */
    public String getStackTraceAsString(Exception exception) {
        try(var sWriter = new StringWriter();
            var pWriter = new PrintWriter(sWriter)) {

            exception.printStackTrace(pWriter);
            return sWriter.toString();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
    }
}
