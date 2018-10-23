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

package io.github.joanaribeiro.felinetoolset.formater;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateTimeUtil {
	
	public enum StandartFormat {
		/**
		 * Produces: <b>dd/MM/aaaa</b>
		 */
		 ddMMaaaa(true,false,true,false)
		/**
		 * Produces: <b>dd/MM/aaaa - hh:mi:ss</b>
		 */
		,ddMMaaaahhmiss(true,true,true,false)
		/**
		 * Produces: <b>dd/MM/aaaa - hh:mi:ss:mss</b>
		 */
		,ddMMaaaahhmissmss(true,true,true,true)
		/**
		 * Produces: <b>hh:mi:ss</b>
		 */
		,hhmiss(true,true,false,false)
		/**
		 * Produces: <b>hh:mi:ss:mss</b>
		 */
		,hhmissmss(true,true,false,true)
		/**
		 * Produces: <b>ddMMaaaa</b>
		 */
		,NODIVISORS_ddMMaaaa(false,false,true,false)
		/**
		 * Produces: <b>ddMMaaaahhmiss</b>
		 */
		,NODIVISORS_ddMMaaaahhmiss(false,true,true,false)
		/**
		 * Produces: <b>ddMMaaaahhmissmss</b>
		 */
		,NODIVISORS_ddMMaaaahhmissmss(false,true,true,true)
		/**
		 * Produces: <b>hhmiss</b>
		 */
		,NODIVISORS_hhmiss(false,true,false,false)
		/**
		 * Produces: <b>hhmissmss</b>
		 */
		,NODIVISORS_hhmissmss(false,true,false,true)
		;
		
		private boolean hasDivisors;
		private boolean hasTime;
		private boolean hasDate;
		private boolean hasMilliseconds;
		
		private StandartFormat(boolean hasDivisors, boolean hasTime, boolean hasDate, boolean hasMilliseconds) {
			this.hasDivisors = hasDivisors;
			this.hasTime = hasTime;
			this.hasDate = hasDate;
			this.hasMilliseconds = this.hasTime ? hasMilliseconds : false;
		}
		
		public boolean hasDivisors() 	 { return hasDivisors; 		}
		public boolean hasTime()		 { return hasTime;			}
		public boolean hasDate()		 { return hasDate;			}
		public boolean hasMilliseconds() { return hasMilliseconds;	}
	}
	
	public static final Long millisecondsToHours = new Long(1000) * new Long(60) * new Long(60);
	public static final Long millisecondsToMinutes = new Long(1000) * new Long(60);
	public static final Long millisecondsToSeconds = new Long(1000);
	public static final Long millisecondsToTimeCap = new Long(999) + (59 * millisecondsToSeconds) + (59 * millisecondsToMinutes) + (23 * millisecondsToHours);
	
	public static String getCurrentDatetime(){
		String stamp = "";
		Calendar c = new GregorianCalendar();
		c.setTimeInMillis(System.currentTimeMillis());
		stamp += c.get(Calendar.YEAR) + "-";
		stamp += ((c.get(Calendar.MONTH)+1) < 10 ? "0"+(c.get(Calendar.MONTH)+1) : (c.get(Calendar.MONTH)+1)) + "-";
		stamp += c.get(Calendar.DAY_OF_MONTH) < 10 ? "0"+c.get(Calendar.DAY_OF_MONTH) : c.get(Calendar.DAY_OF_MONTH) + "-";
		stamp += c.get(Calendar.HOUR_OF_DAY) < 10 ? "0"+c.get(Calendar.HOUR_OF_DAY) : c.get(Calendar.HOUR_OF_DAY) + "-";
		stamp += c.get(Calendar.MINUTE) < 10 ? "0"+c.get(Calendar.MINUTE) : c.get(Calendar.MINUTE) + "-";
		stamp += c.get(Calendar.SECOND) < 10 ? "0"+c.get(Calendar.SECOND) : c.get(Calendar.SECOND) + "-";
		stamp += c.get(Calendar.MILLISECOND) < 10 ? "0"+c.get(Calendar.MILLISECOND) : c.get(Calendar.MILLISECOND);
		return stamp;
	}
	
	public static String getCurrentDate(){
		String stamp = "";
		Calendar c = new GregorianCalendar();
		c.setTimeInMillis(System.currentTimeMillis());
		stamp += c.get(Calendar.YEAR) + "-";
		stamp += ((c.get(Calendar.MONTH)+1) < 10 ? "0"+(c.get(Calendar.MONTH)+1) : (c.get(Calendar.MONTH)+1)) + "-";
		stamp += c.get(Calendar.DAY_OF_MONTH) < 10 ? "0"+c.get(Calendar.DAY_OF_MONTH) : c.get(Calendar.DAY_OF_MONTH);
		return stamp;
	}
	
	public static String getCurrentTime(){
		String stamp = "";
		Calendar c = new GregorianCalendar();
		c.setTimeInMillis(System.currentTimeMillis());
		stamp += c.get(Calendar.HOUR_OF_DAY) < 10 ? "0"+c.get(Calendar.HOUR_OF_DAY) : c.get(Calendar.HOUR_OF_DAY) + "-";
		stamp += c.get(Calendar.MINUTE) < 10 ? "0"+c.get(Calendar.MINUTE) : c.get(Calendar.MINUTE) + "-";
		stamp += c.get(Calendar.SECOND) < 10 ? "0"+c.get(Calendar.SECOND) : c.get(Calendar.SECOND) + "-";
		stamp += c.get(Calendar.MILLISECOND) < 10 ? "0"+c.get(Calendar.MILLISECOND) : c.get(Calendar.MILLISECOND);
		return stamp;
	}
	
	/**
	 * Calculates the time in milliseconds for the given date and time.
	 * @param date {@link String} on format dd/MM/aaaa
	 * @param time {@link String} on format hh:mi:ss:mmmm
	 * @return a {@link java.lang.Long} that represents the given datetime in milliseconds 
	 */
	public static Long getMillisecondsOfDatetime(String date, String time){
		if (date == null || time == null)
			return null;
		String[] dates = date.split("/");
		String[] times = time.split(":");
		Calendar c = new GregorianCalendar();
		c.setTimeInMillis(0);
		c.set(Calendar.DAY_OF_MONTH, (new Long(dates[0])).intValue());
		c.set(Calendar.MONTH, (new Long(dates[1])).intValue()-1); // month ranges from 0 to 11 into the calendar object
		c.set(Calendar.YEAR, (new Long(dates[2])).intValue());
		c.set(Calendar.HOUR_OF_DAY, (new Long(times[0])).intValue());
		c.set(Calendar.MINUTE, (new Long(times[1])).intValue());
		c.set(Calendar.SECOND, (new Long(times[2])).intValue());
		c.set(Calendar.MILLISECOND, (new Long(times[3])).intValue());
		return c.getTimeInMillis();
	}
	
	/**
	 * Get the String form of date-time within given milliseconds.
	 * @param mm as {@link Long}
	 * @return a {@link java.lang.String} that represents the given datetime.
	 */
	public static String getDatetimeOfMilliseconds(Long mm){
		if (mm == null)
			return null;
		String stamp = "";
		Calendar c = new GregorianCalendar();
		c.setTimeInMillis(mm);
		stamp += c.get(Calendar.YEAR) + "-";
		stamp += ((c.get(Calendar.MONTH)+1) < 10 ? "0"+(c.get(Calendar.MONTH)+1) : (c.get(Calendar.MONTH)+1)) + "-";
		stamp += c.get(Calendar.DAY_OF_MONTH) < 10 ? "0"+c.get(Calendar.DAY_OF_MONTH) : c.get(Calendar.DAY_OF_MONTH) + "-";
		stamp += c.get(Calendar.HOUR_OF_DAY) < 10 ? "0"+c.get(Calendar.HOUR_OF_DAY) : c.get(Calendar.HOUR_OF_DAY) + "-";
		stamp += c.get(Calendar.MINUTE) < 10 ? "0"+c.get(Calendar.MINUTE) : c.get(Calendar.MINUTE) + "-";
		stamp += c.get(Calendar.SECOND) < 10 ? "0"+c.get(Calendar.SECOND) : c.get(Calendar.SECOND) + "-";
		stamp += c.get(Calendar.MILLISECOND) < 10 ? "0"+c.get(Calendar.MILLISECOND) : c.get(Calendar.MILLISECOND);
		return stamp;
		
	}
	
	/**
	 * Get the String form of date within given milliseconds.
	 * @param mm as {@link Long}
	 * @return a {@link java.lang.String} that represents the date from the given datetime.
	 */
	public static String getDateOfMilliseconds(Long mm){
		if (mm == null)
			return null;
		String stamp = "";
		Calendar c = new GregorianCalendar();
		c.setTimeInMillis(mm);
		stamp += c.get(Calendar.YEAR) + "-";
		stamp += ((c.get(Calendar.MONTH)+1) < 10 ? "0"+(c.get(Calendar.MONTH)+1) : (c.get(Calendar.MONTH)+1)) + "-";
		stamp += c.get(Calendar.DAY_OF_MONTH) < 10 ? "0"+c.get(Calendar.DAY_OF_MONTH) : c.get(Calendar.DAY_OF_MONTH);
		return stamp;
		
	}
	
	/**
	 * Get the String form of time within given milliseconds.
	 * @param mm as {@link Long}
	 * @return a {@link java.lang.String} that represents the time from the given datetime.
	 */
	public static String getTimeOfMilliseconds(Long mm){
		if (mm == null)
			return null;
		String stamp = "";
		Calendar c = new GregorianCalendar();
		c.setTimeInMillis(mm);
		stamp += c.get(Calendar.HOUR_OF_DAY) < 10 ? "0"+c.get(Calendar.HOUR_OF_DAY) : c.get(Calendar.HOUR_OF_DAY) + "-";
		stamp += c.get(Calendar.MINUTE) < 10 ? "0"+c.get(Calendar.MINUTE) : c.get(Calendar.MINUTE) + "-";
		stamp += c.get(Calendar.SECOND) < 10 ? "0"+c.get(Calendar.SECOND) : c.get(Calendar.SECOND) + "-";
		stamp += c.get(Calendar.MILLISECOND) < 10 ? "0"+c.get(Calendar.MILLISECOND) : c.get(Calendar.MILLISECOND);
		return stamp;
		
	}
	
	/**
	 * Converts the given time in milliseconds to a time-string. The returned format are hh:mi:ss:mmm.
	 * @param mm as {@link Long} containing the total time in milliseconds.
	 * @return {@link String} as time-string representing the total time from the parameter
	 */
	public static String millisecondsToTimeString(Long mm){
		Long hours = new Long(0);
		Long minutes = new Long(0);
		Long seconds = new Long(0);
		Long milliseconds = new Long(0);
		
		// format
		if (mm > millisecondsToTimeCap)
			return "++24 hours";
		hours = mm / millisecondsToHours;
		minutes = (mm - (hours * millisecondsToHours)) / millisecondsToMinutes;
		seconds = (mm - (hours * millisecondsToHours) - (minutes * millisecondsToMinutes)) / millisecondsToSeconds;
		milliseconds = mm - (hours * millisecondsToHours) - (minutes * millisecondsToMinutes) - (seconds * millisecondsToSeconds);	
		
		// valor
		return (hours < 10 ? "0"+hours.toString() : hours.toString()) + ":" +
				(minutes < 10 ? "0"+minutes.toString() : minutes.toString()) + ":" +
				(seconds < 10 ? "0"+seconds.toString() : seconds.toString()) + ":" +
				(milliseconds > 10 ? (milliseconds > 100 ? milliseconds.toString() : "0"+milliseconds.toString()) : "00"+milliseconds.toString());
		
	}
	
	/**
	 * Converts the given time to milliseconds. Accepts the format hh:mi:ss:mmm only.
	 * @param timeData as array of {@link java.lang.Long}. Accepts a maximum of 4 parameters, starting from Hour till Milliseconds
	 * @return a {@link java.lang.Long} as the total milliseconds representation of the time (in range of one day only).
	 */
	public static Long timeStringToMilliseconds(Long... timeData){
		if (timeData == null || timeData.length <= 0 || timeData.length > 4)
			return null;
		Long out = new Long(0);
		if (timeData[0] != null)
			out += timeData[0] * millisecondsToHours;
		if (timeData[1] != null)
			out += timeData[1] * millisecondsToMinutes;
		if (timeData[2] != null)
			out += timeData[2] * millisecondsToSeconds;
		if (timeData[3] != null)
			out += timeData[3];
		return out;
	}
	
	/**
	 * Formats out the date-time represented by the given milliseconds.
	 * @param milliseconds as {@link Long}. Represents the date-time to be formated on a {@link GregorianCalendar}.
	 * @param formater as {@link StandartFormat}. Represents the formatting pattern.
	 * @return {@link String} containing the formated date-time.
	 */
	public static String formatDateOfMilliseconds(Long milliseconds, StandartFormat formater) {
		if (milliseconds == null || formater == null)
			return null;
		String out = "";
		String dateDiv = "/";
		String timeDiv = ":";
		String dateTimeDiv = " - ";
		Calendar c = new GregorianCalendar();
		c.setTimeInMillis(milliseconds);
		if (!formater.hasDivisors()) { dateDiv = ""; timeDiv = ""; dateTimeDiv = ""; }
		if (formater.hasDate()) {
			out += (c.get(Calendar.DAY_OF_MONTH) < 10 ? "0"+c.get(Calendar.DAY_OF_MONTH) : c.get(Calendar.DAY_OF_MONTH));
			out += dateDiv + ((c.get(Calendar.MONTH)+1) < 10 ? "0"+(c.get(Calendar.MONTH)+1) : (c.get(Calendar.MONTH)+1));
			out += dateDiv + c.get(Calendar.YEAR);
		}
		if (formater.hasDate() && formater.hasTime()) {	out += dateTimeDiv;	}
		if (formater.hasTime()) {
			out += (c.get(Calendar.HOUR_OF_DAY) < 10 ? "0"+c.get(Calendar.HOUR_OF_DAY) : c.get(Calendar.HOUR_OF_DAY));
			out += timeDiv + (c.get(Calendar.MINUTE) < 10 ? "0"+c.get(Calendar.MINUTE) : c.get(Calendar.MINUTE));
			out += timeDiv + (c.get(Calendar.SECOND) < 10 ? "0"+c.get(Calendar.SECOND) : c.get(Calendar.SECOND));
		}
		if (formater.hasMilliseconds()) {
			out += timeDiv + (c.get(Calendar.MILLISECOND) < 10 ? "00"+c.get(Calendar.MILLISECOND) : c.get(Calendar.MILLISECOND) < 100 ? "0"+c.get(Calendar.MILLISECOND) : c.get(Calendar.MILLISECOND));
		}
		return out;
	}



}
