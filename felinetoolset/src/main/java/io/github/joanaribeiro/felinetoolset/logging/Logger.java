/*
MIT License

Copyright (c) 2018 Joana Ribeiro

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package io.github.joanaribeiro.felinetoolset.logging;

import io.github.joanaribeiro.felinetoolset.logging.LogInstance.Level;
import io.github.joanaribeiro.felinetoolset.logging.defaults.ConsoleLogHandler;
import io.github.joanaribeiro.felinetoolset.logging.interfaces.LogHandler;

public class Logger {
	
	// attibutes
	private static java.util.Map<Class<?>, LogInstance> loggers = new java.util.HashMap<Class<?>, LogInstance>();
	private static java.util.List<LogHandler> handlers = new java.util.ArrayList<LogHandler>();
	
	// protected methods	
	protected static LogInstance getLogger(Class<?> caller) {
		if (caller == null) caller = Logger.class;
		LogInstance logger = Logger.loggers.get(caller);
		if (logger == null) { logger = new LogInstance(caller); loggers.put(caller, logger); }
		for(LogHandler h:Logger.handlers) { logger.registerHandler(h); }
		if (logger.handlers.size() <= 0) logger.registerHandler(new ConsoleLogHandler());
		return logger;
	}
	protected static StackTraceElement getCallerElement() {
		StackTraceElement[] stp = Thread.currentThread().getStackTrace();
		for(int i=1;i<stp.length;i++) {
			if (!stp[i].getClassName().startsWith("io.github.joanaribeiro.felinetoolset.logging.Logger")) {
				return stp[i];
			}
		}
		return null;
	}
	protected static Class<?> getCallerClass(StackTraceElement callerElement) {
		try {
			return Class.forName(callerElement.getClassName());				
		} catch (Exception e) {
			return null;
		}
	}	
	protected static String getCallerMethod(StackTraceElement callerElement) {
		return callerElement.getMethodName();
	}	
	protected static LogRecordData buildLogRecord(Level level, StackTraceElement ste, String message) {
		LogRecordData lrd = new LogRecordData();
		lrd.setCaller			(Logger.getCallerClass(ste));
		lrd.setCallerClassName	(lrd.getCaller() == null ? null : lrd.getCaller().getName());
		lrd.setCallerMethodName	(Logger.getCallerMethod(ste));
		lrd.setDatetime			(System.currentTimeMillis());
		lrd.setLevel			(level == null ? LogInstance.DEFAULT_LEVEL : level);
		lrd.setFilter			(level == null ? LogInstance.DEFAULT_FILTER : level.getLevelFilter());
		lrd.setMessage			(message);
		return lrd;
	}	
	protected static void log(LogInstance logger, LogRecordData lrd) {
		logger.log(lrd.getLevel(), lrd);
	}
	
	// public methods	
	public static void debug(String message) {
		StackTraceElement ste = Logger.getCallerElement();
		Logger.log(Logger.getLogger(Logger.getCallerClass(ste))
				, Logger.buildLogRecord(Level.DEBUG, ste, message));
	}
	public static void info(String message) {
		StackTraceElement ste = Logger.getCallerElement();
		Logger.log(Logger.getLogger(Logger.getCallerClass(ste))
				, Logger.buildLogRecord(Level.INFO, ste, message));
	}
	public static void warning(String message) {
		StackTraceElement ste = Logger.getCallerElement();
		Logger.log(Logger.getLogger(Logger.getCallerClass(ste))
				, Logger.buildLogRecord(Level.WARNING, ste, message));
	}
	public static void severe(String message) {
		StackTraceElement ste = Logger.getCallerElement();
		Logger.log(Logger.getLogger(Logger.getCallerClass(ste))
				, Logger.buildLogRecord(Level.SEVERE, ste, message));
	}
	public static void critical(String message) {
		StackTraceElement ste = Logger.getCallerElement();
		Logger.log(Logger.getLogger(Logger.getCallerClass(ste))
				, Logger.buildLogRecord(Level.CRITICAL, ste, message));
	}
	public static void log(String message) {
		StackTraceElement ste = Logger.getCallerElement();
		Logger.log(Logger.getLogger(Logger.getCallerClass(ste))
				, Logger.buildLogRecord(null, ste, message));
	}
	public static void gatherCallInformation() {
		StackTraceElement[] stp = Thread.currentThread().getStackTrace();
		System.out.println("Printing stack trace data...");
		for(int i=0;i<stp.length;i++) {
			System.out.println("Data at ["+i+"] index: ["+stp[i].toString()+"]");
		}
		System.out.println("Done.");
	}		

}
