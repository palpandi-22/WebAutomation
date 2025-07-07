package com.openmrs.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingUtils {
	
	
	private static final Logger LOGGER = Logger.getLogger(LoggingUtils.class.getName());

	public static void logInfo(String message) {
		log(Level.INFO, message);
	}

	public static void logWarning(String message) {
		log(Level.WARNING, message);
	}

	public static void logError(String message, Throwable throwable) {
		log(Level.SEVERE, message);
		if (throwable != null) {
			throwable.printStackTrace();
		}
	}

	private static void log(Level level, String message) {
		LOGGER.log(level, message);
	}
	}


