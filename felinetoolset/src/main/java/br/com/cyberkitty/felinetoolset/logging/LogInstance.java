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

package br.com.cyberkitty.felinetoolset.logging;

import br.com.cyberkitty.felinetoolset.logging.interfaces.LogHandler;

public class LogInstance {
	
	public enum Level {
		 DEBUG 		{ public Filter getLevelFilter() { return Filter.FINEST; 	} }
		,INFO 		{ public Filter getLevelFilter() { return Filter.FINER; 	} }
		,WARNING 	{ public Filter getLevelFilter() { return Filter.FINE; 		} }
		,SEVERE 	{ public Filter getLevelFilter() { return Filter.FINE; 		} }
		,CRITICAL 	{ public Filter getLevelFilter() { return Filter.ALL; 		} }
		; 
		
		public abstract Filter getLevelFilter();		
	}
	
	public enum Filter {
		 FINEST { public Integer getIntLevel() { return 1000; 	} }
		,FINER 	{ public Integer getIntLevel() { return 100; 	} }
		,FINE 	{ public Integer getIntLevel() { return 10; 	} }
		,ALL 	{ public Integer getIntLevel() { return 0; 		} }
		;
		
		public abstract Integer getIntLevel();

		public boolean applicable(Filter eval) {
			return eval == null ? false : this.getIntLevel() <= eval.getIntLevel();
		}
	}
	
	public static final Level DEFAULT_LEVEL = Level.INFO;
	public static final Filter DEFAULT_FILTER = Filter.ALL;
	
	private static java.util.UUID generateUUID(Class<?> target){
		return target == null ? java.util.UUID.fromString("") : java.util.UUID.randomUUID();
	}
	
	private java.util.UUID id;
	private String name;
	private Class<?> target;
	
	protected Level defaultLogLevel;
	protected Filter defaultLogFilter;
	
	protected java.util.List<LogHandler> handlers;
		
	protected void init(Class<?> target) {
		this.target 			= target;
		this.id 				= LogInstance.generateUUID(target);
		this.name 				= target.getName();		
		this.defaultLogLevel 	= DEFAULT_LEVEL;
		this.defaultLogFilter 	= DEFAULT_FILTER;
		this.handlers 			= new java.util.ArrayList<LogHandler>();
	}	
	
	protected void log(Level level, LogRecordData lrd) {
		if (this.handlers == null || this.handlers.size() <= 0) return;
		for(LogHandler h:this.handlers){
			if (h.getFilter().applicable(level.getLevelFilter())){
				h.publish(lrd);
			}
		}
	}
	
	public LogInstance(Class<?> target) {
		if (target == null) { target = LogInstance.class; }
		this.init(target);
	}
	
	public void registerHandler(LogHandler handler) { 
		this.registerHandler(null, handler);
	}
	
	public void registerHandler(Filter filter, LogHandler handler) {
		if (handler == null) return;
		if (handler.getFilter() == null)
			if (filter == null) handler.setFilter(this.defaultLogFilter);
			else handler.setFilter(filter);
		this.handlers.add(handler);	
	}
	
	// public getters
	public java.util.UUID getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Class<?> getTarget() {
		return target;
	}	
	
}
