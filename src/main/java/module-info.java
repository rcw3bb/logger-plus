/**
 * The module definition for logger-plus
 */
module xyz.ronella.logging.logger.plus {
    requires transitive org.slf4j;

    requires java.scripting;

    exports xyz.ronella.logging;
}