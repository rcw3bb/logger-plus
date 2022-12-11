package xyz.ronella.logging;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LoggerPlusTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoggerPlusTest.class);
    private final static LoggerPlus LOGGER_PLUS = new LoggerPlus(LOGGER);

    @Test
    public void methodDebug() {
        try (var mLOG = LOGGER_PLUS.groupLog("methodDebug")) {
            var str = new StringBuilder();
            mLOG.debug(()-> str.append("I'm in").toString());
            assertEquals("I'm in", str.toString());
        }
    }

    @Test
    public void methodDebugF() {
        try (var mLOG = Mockito.spy(LOGGER_PLUS.groupLog("methodDebugF"))) {
            var format = "Hello %s";
            var token = "world";
            mLOG.debugf(format, token);
            Mockito.verify(mLOG).debugf(format, token);
        }
    }

    @Test
    public void nonMethodDebug() {
        var str = new StringBuilder();
        LOGGER_PLUS.debug(()-> str.append("I'm in").toString());
        assertEquals("I'm in", str.toString());
    }

    @Test
    public void nonMethodDebugF() {
        var mockedLogger = Mockito.spy(LOGGER_PLUS);
        var format = "Debug Hello %s";
        var value = "world";
        mockedLogger.debugf(format, value);
        Mockito.verify(mockedLogger, Mockito.times(1)).debug(Mockito.anyString());
    }

    @Test
    public void methodInfo() {
        try (var mLOG = LOGGER_PLUS.groupLog("methodInfo")) {
            var str = new StringBuilder();
            mLOG.info(()-> str.append("I'm in").toString());
            assertEquals("I'm in", str.toString());
        }
    }

    @Test
    public void methodInfoF() {
        try (var mLOG = Mockito.spy(LOGGER_PLUS.groupLog("methodInfoF"))) {
            var format = "Hello %s";
            var token = "world";
            mLOG.infof(format, token);
            Mockito.verify(mLOG).infof(format, token);
        }
    }

    @Test
    public void nonMethodInfo() {
        var str = new StringBuilder();
        LOGGER_PLUS.info(()-> str.append("I'm in").toString());
        assertEquals("I'm in", str.toString());
    }

    @Test
    public void nonMethodInfoF() {
        var mockedLogger = Mockito.spy(LOGGER_PLUS);
        mockedLogger.infof("Info Hello %s", "world");
        Mockito.verify(mockedLogger, Mockito.times(1)).info(Mockito.anyString());
    }

    @Test
    public void methodWarn() {
        try (var mLOG = LOGGER_PLUS.groupLog("methodWarn")) {
            var str = new StringBuilder();
            mLOG.warn(()-> str.append("I'm in").toString());
            assertEquals("I'm in", str.toString());
        }
    }

    @Test
    public void methodWarnF() {
        try (var mLOG = Mockito.spy(LOGGER_PLUS.groupLog("methodWarnF"))) {
            var format = "Hello %s";
            var token = "world";
            mLOG.warnf(format, token);
            Mockito.verify(mLOG).warnf(format, token);
        }
    }

    @Test
    public void nonMethodWarn() {
        var str = new StringBuilder();
        LOGGER_PLUS.warn(()-> str.append("I'm in").toString());
        assertEquals("I'm in", str.toString());
    }

    @Test
    public void nonMethodWarnF() {
        var mockedLogger = Mockito.spy(LOGGER_PLUS);
        mockedLogger.warnf("Warn Hello %s", "world");
        Mockito.verify(mockedLogger, Mockito.times(1)).warn(Mockito.anyString());
    }

    @Test
    public void methodError() {
        try (var mLOG = LOGGER_PLUS.groupLog("methodError")) {
            var str = new StringBuilder();
            mLOG.error(()-> str.append("I'm in").toString());
            assertEquals("I'm in", str.toString());
        }
    }

    @Test
    public void methodErrorF() {
        try (var mLOG = Mockito.spy(LOGGER_PLUS.groupLog("methodErrorF"))) {
            var format = "Hello %s";
            var token = "world";
            mLOG.errorf(format, token);
            Mockito.verify(mLOG).errorf(format, token);
        }
    }

    @Test
    public void nonMethodError() {
        var str = new StringBuilder();
        LOGGER_PLUS.error(()-> str.append("I'm in").toString());
        assertEquals("I'm in", str.toString());
    }

    @Test
    public void nonMethodErrorF() {
        var mockedLogger = Mockito.spy(LOGGER_PLUS);
        mockedLogger.errorf("Error Hello %s", "world");
        Mockito.verify(mockedLogger, Mockito.times(1)).error(Mockito.anyString());
    }

    @Test
    public void methodTrace() {
        try (var mLOG = LOGGER_PLUS.groupLog("methodTrace")) {
            var str = new StringBuilder();
            mLOG.trace(()-> str.append("I'm in").toString());
            assertEquals("I'm in", str.toString());
        }
    }

    @Test
    public void methodTraceF() {
        try (var mLOG = Mockito.spy(LOGGER_PLUS.groupLog("methodTraceF"))) {
            var format = "Hello %s";
            var token = "world";
            mLOG.tracef(format, token);
            Mockito.verify(mLOG).tracef(format, token);
        }
    }

    @Test
    public void nonMethodTrace() {
        var str = new StringBuilder();
        LOGGER_PLUS.trace(()-> str.append("I'm in").toString());
        assertEquals("I'm in", str.toString());
    }

    @Test
    public void nonMethodTraceF() {
        var mockedLogger = Mockito.spy(LOGGER_PLUS);
        mockedLogger.tracef("Trace Hello %s", "world");
        Mockito.verify(mockedLogger, Mockito.times(1)).trace(Mockito.anyString());
    }

    @Test
    public void methodDebugMessage() {
        try (var mLOG = Mockito.spy(LOGGER_PLUS.groupLog("methodDebugMessage"))) {
            var message = "I'm in.";
            mLOG.debug(message);
            Mockito.verify(mLOG).debug(message);
        }
    }

    @Test
    public void nonMethodDebugMessage() {
        var logger = Mockito.spy(LOGGER_PLUS);
        var message = "I'm in.";
        logger.debug(message);
        Mockito.verify(logger).debug(message);
    }

    @Test
    public void methodInfoMessage() {
        try (var mLOG = Mockito.spy(LOGGER_PLUS.groupLog("methodInfoMessage"))) {
            var message = "I'm in.";
            mLOG.info(message);
            Mockito.verify(mLOG).info(message);
        }
    }

    @Test
    public void nonMethodInfoMessage() {
        var logger = Mockito.spy(LOGGER_PLUS);
        var message = "I'm in.";
        logger.info(message);
        Mockito.verify(logger).info(message);
    }

    @Test
    public void methodErrorMessage() {
        try (var mLOG = Mockito.spy(LOGGER_PLUS.groupLog("methodErrorMessage"))) {
            var message = "I'm in.";
            mLOG.error(message);
            Mockito.verify(mLOG).error(message);
        }
    }

    @Test
    public void nonMethodErrorMessage() {
        var logger = Mockito.spy(LOGGER_PLUS);
        var message = "I'm in.";
        logger.error(message);
        Mockito.verify(logger).error(message);
    }

    @Test
    public void methodWarnMessage() {
        try (var mLOG = Mockito.spy(LOGGER_PLUS.groupLog("methodWarnMessage"))) {
            var message = "I'm in.";
            mLOG.warn(message);
            Mockito.verify(mLOG).warn(message);
        }
    }

    @Test
    public void nonMethodWarnMessage() {
        var logger = Mockito.spy(LOGGER_PLUS);
        var message = "I'm in.";
        logger.warn(message);
        Mockito.verify(logger).warn(message);
    }

    @Test
    public void methodTraceMessage() {
        try (var mLOG = Mockito.spy(LOGGER_PLUS.groupLog("methodTraceMessage"))) {
            var message = "I'm in.";
            mLOG.trace(message);
            Mockito.verify(mLOG).trace(message);
        }
    }

    @Test
    public void nonMethodTraceMessage() {
        var logger = Mockito.spy(LOGGER_PLUS);
        var message = "I'm in.";
        logger.trace(message);
        Mockito.verify(logger).trace(message);
    }

    @Test
    public void getLogger() {
        var logger = LOGGER_PLUS.getLogger();
        assertEquals(LOGGER, logger);
    }

    @Test
    public void stacktraceString() {
        var logger = Mockito.spy(LOGGER_PLUS);
        Exception exception = null;

        try {
            String object=null;
            System.out.println(object.toString());
        } catch (Exception exp) {
            exception = exp;
            var message = logger.getStackTraceAsString(exp);
            logger.error(message);
        }

        Mockito.verify(logger).getStackTraceAsString(exception);
    }

    @Test
    public void stacktraceStringNullException() {
        var logger = Mockito.spy(LOGGER_PLUS);
        assertNull(logger.getStackTraceAsString(null));
    }

    @Test
    public void withoutHeader() {
        try (var mLOG = Mockito.spy(LOGGER_PLUS.groupLog("methodTraceMessage", false))) {
            var message = "I'm in.";
            mLOG.trace(message);
            Mockito.verify(mLOG).trace(message);
        }
    }

    @Test
    public void debugSupplier() {
        var logger = Mockito.spy(LOGGER);
        var loggerPlus = Mockito.spy(new LoggerPlus(logger));
        var message = "I'm in";
        loggerPlus.debug(message);
        Mockito.verify(logger, Mockito.times(1)).debug(message);
    }

    @Test
    public void infoSupplier() {
        var logger = Mockito.spy(LOGGER);
        var loggerPlus = Mockito.spy(new LoggerPlus(logger));
        var message = "I'm in";
        loggerPlus.info(message);
        Mockito.verify(logger, Mockito.times(1)).info(message);
    }

    @Test
    public void errorSupplier() {
        var logger = Mockito.spy(LOGGER);
        var loggerPlus = Mockito.spy(new LoggerPlus(logger));
        var message = "I'm in";
        loggerPlus.error(message);
        Mockito.verify(logger, Mockito.times(1)).error(message);
    }

    @Test
    public void warnSupplier() {
        var logger = Mockito.spy(LOGGER);
        var loggerPlus = Mockito.spy(new LoggerPlus(logger));
        var message = "I'm in";
        loggerPlus.warn(message);
        Mockito.verify(logger, Mockito.times(1)).warn(message);
    }

    @Test
    public void traceSupplier() {
        var logger = Mockito.spy(LOGGER);
        var loggerPlus = Mockito.spy(new LoggerPlus(logger));
        var message = "I'm in";
        loggerPlus.trace(message);
        Mockito.verify(logger, Mockito.times(1)).trace(message);
    }

}
