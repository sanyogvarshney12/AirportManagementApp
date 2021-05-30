package com.airport.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sanyog Varshney
 * @since 1.0
 * @version 1.0
 * @apiNote LOGGER CONFIG
 */
public class ApplicationLogger {

    private final Logger logger = LoggerFactory.getLogger(ApplicationLogger.class);

    public void debug(String className, String methodName, String msg) {
        logger.debug("CLASSNAME : {} :: METHODNAME : {} :: {}", className, methodName, msg);
    }

    public void debug(String className, String methodName, String msg, Object obj) {
        logger.debug("CLASSNAME : {} :: METHODNAME : {}  :: {} {}", className, methodName, msg, obj);
    }

    public void error(String msg, Throwable e) {
        logger.error(msg, e);
    }

}