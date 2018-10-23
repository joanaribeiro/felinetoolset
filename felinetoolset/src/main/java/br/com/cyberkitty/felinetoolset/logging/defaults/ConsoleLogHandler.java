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

package br.com.cyberkitty.felinetoolset.logging.defaults;

import br.com.cyberkitty.felinetoolset.logging.LogInstance.Filter;
import br.com.cyberkitty.felinetoolset.formater.DateTimeUtil;
import br.com.cyberkitty.felinetoolset.formater.TextUtil;
import br.com.cyberkitty.felinetoolset.logging.LogRecordData;
import br.com.cyberkitty.felinetoolset.logging.interfaces.LogHandler;

public class ConsoleLogHandler implements LogHandler {

	// attributes
	private Filter filter;
	private boolean colored = false;
	
	// constructor
	public ConsoleLogHandler() { }
	
	// protected methods
	protected String formulateMessage(LogRecordData record) {
		if (this.colored)
			return "" 
				+ TextUtil.colorText(DateTimeUtil.formatDateOfMilliseconds(record.getDatetime(), DateTimeUtil.StandartFormat.ddMMaaaahhmissmss), TextUtil.ANSI_COLOR.ANSI_CYAN)
				+ TextUtil.colorText(" " + record.getCallerClassName() + " " + record.getCallerMethodName(), TextUtil.ANSI_COLOR.ANSI_RED) 
				+ TextUtil.colorText("\n[" + record.getLevel().toString() + "]: ", TextUtil.ANSI_COLOR.ANSI_GREEN) 
				+ TextUtil.colorText(record.getMessage(), TextUtil.ANSI_COLOR.ANSI_YELLOW); 
		else
			return ""
				+ DateTimeUtil.formatDateOfMilliseconds(record.getDatetime(), DateTimeUtil.StandartFormat.ddMMaaaahhmissmss)
				+ " " + record.getCallerClassName() + " " + record.getCallerMethodName() 
				+ "\n[" + record.getLevel().toString() + "]: " 
				+ record.getMessage();	
	}
	
	// Overrides
	@Override public void 	setFilter(Filter filter) 	  { this.filter = filter; 							   }
	@Override public Filter getFilter() 			 	  { return this.filter;   							   }
	@Override public void 	publish(LogRecordData record) { System.out.println(this.formulateMessage(record)); }

	// public methods
	public void 	toggleColored() { this.colored = !this.colored; }
	public boolean 	isColored() 	{ return this.colored; 			}
	
}
