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

import io.github.joanaribeiro.felinetoolset.logging.LogInstance.Filter;
import io.github.joanaribeiro.felinetoolset.logging.LogInstance.Level;

/**
 * Stores the data relative to one log entry.
 * @author Joana_Ribeiro
 * 
 */
public class LogRecordData {
	
	// attributes
	private Long 	 datetime;
	private String 	 callerClassName;
	private String 	 callerMethodName;
	private Class<?> caller;
	private String 	 message;
	private Level	 level;
	private Filter	 filter;
	
	// constructor
	public LogRecordData() { }

	// getters
	public Long 	getDatetime() 			{ return datetime;		   }
	public String 	getCallerClassName() 	{ return callerClassName;  }
	public String 	getCallerMethodName() 	{ return callerMethodName; }
	public Class<?> getCaller() 			{ return caller;		   }
	public String 	getMessage() 			{ return message;		   }
	public Level	getLevel()				{ return level;			   }
	public Filter	getFilter()				{ return filter;		   }
	
	// setters
	public void setDatetime(Long datetime) 					 { this.datetime = datetime;				 }
	public void setCallerClassName(String callerClassName) 	 { this.callerClassName = callerClassName;	 }
	public void setCallerMethodName(String callerMethodName) { this.callerMethodName = callerMethodName; }
	public void setCaller(Class<?> caller) 					 { this.caller = caller;					 }
	public void setMessage(String message) 					 { this.message = message;					 }
	public void setLevel(Level level)						 { this.level = level;						 }
	public void setFilter(Filter filter)					 { this.filter = filter;					 }

}
