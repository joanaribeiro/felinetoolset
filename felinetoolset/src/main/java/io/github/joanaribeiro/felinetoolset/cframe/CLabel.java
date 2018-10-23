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

package io.github.joanaribeiro.felinetoolset.cframe;

import io.github.joanaribeiro.felinetoolset.cframe.complements.Parent;
import io.github.joanaribeiro.felinetoolset.cframe.complements.Child;

public abstract class CLabel extends javax.swing.JLabel implements Child {
	
	public enum Aling {
		LEFT, RIGHT, TOP, DOWN;
	}

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Parent parent;
	private java.awt.Component labelFor;
	private Aling aling;
	
	public String getName(){
		return this.name;
	}
	
	public boolean isLabelFor(){
		if (this.labelFor != null)
			return true;
		else
			return false;
	}
	
	public java.awt.Component getLabelTarget(){
		return this.labelFor;
	}
	
	public Aling getAlingment(){
		return this.aling;
	}
	
	protected final void init(String name, java.awt.Component labelFor, Aling aling){
		this.name = name;
		this.parent = null;
		this.labelFor = labelFor;
		this.aling = aling;
		this.setLabelFor(this.labelFor);
	}

	public CLabel(String name){
		super();
		this.init(name, null, null);
	}
	public CLabel(String name, String text){
		super(text);
		this.init(name, null, null);
	}	
	public CLabel(String name, java.awt.Component labelFor, Aling aling){
		super();
		this.init(name, labelFor, aling);
	}	
	public CLabel(String name, String text, java.awt.Component labelFor, Aling aling){
		super(text);
		this.init(name, labelFor, aling);
	}

	@Override
	public void registerParent(Parent parent) {
		this.parent = parent;
	}

	@Override
	public Parent findParent() {
		return this.parent;
	}
	
	@Override
	public Parent findHigherParent() {
		if (this.parent == null)
			return null;
		else
			return this.parent.findHigherParent();
	}

}
