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

public class TextUtil {
	
	public enum ANSI_COLOR {
		
		 ANSI_RESET("\u001B[0m")
		,ANSI_BLACK("\u001B[30m")
		,ANSI_RED("\u001B[31m")
		,ANSI_GREEN("\u001B[32m")
		,ANSI_YELLOW("\u001B[33m")
		,ANSI_BLUE("\u001B[34m")
		,ANSI_PURPLE("\u001B[35m")
		,ANSI_CYAN("\u001B[36m")
		,ANSI_WHITE("\u001B[37m")
		;		
		
		private String definition;		
		
		private ANSI_COLOR(String definition) { this.definition = definition; }
		
		@Override
		public String toString() { return definition; } 
		
	}	
	
	public static String stringToHex(String line, String separator){
		if (line == null)
			return line;
		if (separator == null)
			separator = " ";
		char[] lineC = line.toCharArray();
		String out = "";
		boolean first = true;
		for(int i=0;i<lineC.length;i++){
			if (first){
				out += (Integer.toHexString((int)lineC[i])).toString();
				first = false;
			}
			else
				out += separator + (Integer.toHexString((int)lineC[i])).toString();
		}
		return out;
	}
	
	public static String[] stringToHex(String[] text, String separator){
		if (text == null)
			return text;
		String[] out = new String[text.length];
		for(int i=0;i<text.length;i++){
			out[i] = stringToHex(text[i], separator);
		}
		return out;
	}
	
	public static String hexToString(String hex, String separator){
		if (hex == null)
			return hex;
		String out = "";
		char[] hexA = hex.toCharArray();
		try {
			if (separator == null || separator.length() <= 0){
				if ((hex.length() % 2) != 0)
					return "Cannot decodify! A pair for one or more hexadecimal digits are missing.";
				for(int i=0;i<hexA.length;i+=2){
					out += (char)Integer.parseInt((""+hexA[i]+hexA[i+1]), 16);
				}
			}
			else {
				String[] hexD = hex.split(separator);
				for(int i=0;i<hexD.length;i++){
					out += (char)Integer.parseInt(hexD[i], 16);
				}
			}
		} catch (Exception e) {
			return "Cannot decodify! Bad hex string format or incorrect string separator.";
		}
		return out;
	}
	
	public static String colorText(String text, ANSI_COLOR color) {
		if (text == null)
			return text;
		return color.toString() + text + ANSI_COLOR.ANSI_RESET.toString();
	}

}
