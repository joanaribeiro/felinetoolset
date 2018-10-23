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

package br.com.cyberkitty.felinetoolset.cframe.config;

public class Config {
	
	private static int springInitX = 6;
	private static int springInitY = 6;
	private static int springXPad = 6;
	private static int springYPad = 6;
	
	public static int getSpringInitX() {
		return springInitX;
	}
	public static void setSpringInitX(int springInitX) {
		Config.springInitX = springInitX;
	}
	public static int getSpringInitY() {
		return springInitY;
	}
	public static void setSpringInitY(int springInitY) {
		Config.springInitY = springInitY;
	}
	public static int getSpringXPad() {
		return springXPad;
	}
	public static void setSpringXPad(int springXPad) {
		Config.springXPad = springXPad;
	}
	public static int getSpringYPad() {
		return springYPad;
	}
	public static void setSpringYPad(int springYPad) {
		Config.springYPad = springYPad;
	}

}
