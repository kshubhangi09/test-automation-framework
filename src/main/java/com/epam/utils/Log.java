package com.epam.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    private static final Logger logger = LogManager.getLogger(Log.class);

    public static void info(String message){
        logger.info(message);
    }
    public static void warn(String message){
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void fatal(String message) {
        logger.fatal(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void startTestCase(String testCaseName) {
        logger.info("========== STARTING TEST CASE: " + testCaseName + " ==========");
    }

    public static void endTestCase(String testCaseName) {
        logger.info("========== ENDING TEST CASE: " + testCaseName + " ==========");
    }
}
