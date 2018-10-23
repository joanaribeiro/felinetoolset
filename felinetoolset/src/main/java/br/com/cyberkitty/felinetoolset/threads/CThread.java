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

package br.com.cyberkitty.felinetoolset.threads;

public abstract class CThread extends Thread {
	
	private boolean alive;
	private boolean active;
		
	public boolean alive(){
		return this.alive;
	}
	
	public boolean active(){
		return this.active;
	}
	
	protected abstract void step();
	
	protected void init(){
		this.alive = true;
		this.active = true;
	}
	
	public CThread(String name){
		super(name);
		this.init();
	}
	
	@Override
	public final void run(){
		while(this.alive()){
			while(this.active()){
				this.step();
			}
		}
	}
	
	public void freeze(){
		this.active = false;
	}
	
	public void unfreeze(){
		this.active = true;
	}
	
	public void kill(){
		this.freeze();
		this.alive = false;
		this.interrupt();
	}	

}
