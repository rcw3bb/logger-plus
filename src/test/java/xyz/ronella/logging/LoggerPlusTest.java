package xyz.ronella.logging;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoggerPlusTest {

    private final static LoggerPlus LOGGER_PLUS = new LoggerPlus(LoggerFactory.getLogger(LoggerPlusTest.class));

    @Test
    public void methodTest() {
        try (var mLOG = LOGGER_PLUS.logByMethodCall("methodTest")) {
            var str = new StringBuilder();
            mLOG.debug(()-> str.append("I'm in").toString());
            assertEquals("I'm in", str.toString());
        }
    }

    @Test
    public void nonMethodTest() {
        var str = new StringBuilder();
        LOGGER_PLUS.debug(()-> str.append("I'm in").toString());
        assertEquals("I'm in", str.toString());
    }

}
