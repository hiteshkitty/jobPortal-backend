package com.troika.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TroikaThreadLocal {

	private static final Logger LOGGER = LoggerFactory.getLogger(TroikaThreadLocal.class);

	public static final ThreadLocal userThreadLocal = new ThreadLocal();

	public static final ThreadLocal timerThreadLocal = new ThreadLocal();
	
	public static void set(Object value) {

		LOGGER.debug("setting following in the threadLocal: " + value);

		userThreadLocal.set(value);

	}

	public static void unset() {

		LOGGER.debug("Removing object from the threadlocal");

		if (TroikaThreadLocal.getTimerThreadLocal() != null) {

			LOGGER.debug("In case of cache, removing object from the Timer Thread Local");

			timerThreadLocal.remove();

		}

	}

	/**
	 * @return
	 */
	public static Object get() {

		return userThreadLocal.get();

	}

	public static void setTimerThreadLocal(Long value) {

		LOGGER.debug("setting following in the timerthreadLocal: " + value);

		timerThreadLocal.set(value);

	}

	public static Long getTimerThreadLocal() {

		return (Long) timerThreadLocal.get();

	}

}