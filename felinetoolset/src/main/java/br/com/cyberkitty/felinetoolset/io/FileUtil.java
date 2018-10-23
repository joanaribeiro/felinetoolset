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


package br.com.cyberkitty.felinetoolset.io;

public class FileUtil {
	
	public static final String dirDiv = System.getProperty("os.name") != null ? System.getProperty("os.name").toLowerCase().contains("window".toLowerCase()) ? "\\" : "/" : "/";
	
	public static String fixFileURL(String... pieces){
		String fixed = "";
		boolean first = true;
		for(String p:pieces){
			if(first)
				fixed = p;
			else
				fixed += fixed.endsWith(dirDiv) ? p.startsWith(dirDiv) ? p.substring(1) : p : p.startsWith(dirDiv) ? p : dirDiv + p;
		}
		return fixed;
	}
	
	public static void newFile(String aPath){
		try {
			java.io.File file = new java.io.File(aPath);
			file.createNewFile();
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public static void cleanFile(String aPath){
		try {
			java.io.File file = new java.io.File(aPath);
			file.delete();
			file.createNewFile();
		} catch (Exception e){
			throw new RuntimeException(e);
		}		
	}
	
	public static boolean existsFile(String aPath){
		try {
			java.io.File file = new java.io.File(aPath);
			return file.exists();
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	public static void newLineOnFile(String aPath){
		try {
			java.io.File file = new java.io.File(aPath);
			java.io.BufferedWriter bfw = new java.io.BufferedWriter(new java.io.FileWriter(file, true));
			bfw.newLine();
			bfw.close();		
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	public static void writeOnFile(String input, String aPath){
		try {
			java.io.File file = new java.io.File(aPath);
			java.io.BufferedWriter bfw = new java.io.BufferedWriter(new java.io.FileWriter(file, true));
			bfw.append(input);		
			bfw.close();
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public static void writeOnFile(String[] input, String aPath){
		try {
			boolean first = true;
			for(String l:input){
				if (first)
					first = false;
				else
					newLineOnFile(aPath);
				writeOnFile(l, aPath);
			}
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	public static String readFirstLine(String aPath){
		try {
			java.io.File file = new java.io.File(aPath);
			java.io.BufferedReader bfr = new java.io.BufferedReader(new java.io.FileReader(file));
			String output = bfr.readLine();
			bfr.close();
			return output;
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public static String[] readAllFile(String aPath){
		try {
			java.util.List<String> output = new java.util.ArrayList<String>();
			java.io.File file = new java.io.File(aPath);
			java.io.BufferedReader bfr = new java.io.BufferedReader(new java.io.FileReader(file));
			String l = bfr.readLine();
			while(l != null){
				output.add(l);
				l = bfr.readLine();
			}
			bfr.close();
			return output.toArray(new String[]{});
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}

}
