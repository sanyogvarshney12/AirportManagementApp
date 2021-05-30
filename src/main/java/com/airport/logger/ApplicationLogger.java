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

    /**
     *
     * @param className
     * @param methodName
     * @param msg
     */
    public void debug(String className, String methodName, String msg) {
        logger.debug("CLASSNAME : {} :: METHODNAME : {} :: {}", className, methodName, msg);
    }

    /**
     *
     * @param className
     * @param methodName
     * @param msg
     * @param obj
     */
    public void debug(String className, String methodName, String msg, Object obj) {
        logger.debug("CLASSNAME : {} :: METHODNAME : {}  :: {} {}", className, methodName, msg, obj);
    }

    /**
     *
     * @param msg
     * @param e
     */
    public void error(String msg, Throwable e) {
        logger.error(msg, e);
    }

}