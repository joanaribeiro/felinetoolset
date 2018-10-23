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
import io.github.joanaribeiro.felinetoolset.cframe.event.CTextListener;

public abstract class CTextField extends java.awt.TextField implements CTextListener, Child {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Parent parent;
	private Boolean editable;
	
	public String getName(){
		return this.name;
	}
	public boolean isEditable(){
		return this.editable;
	}
	
	protected final void init(String name, Boolean editable){
		this.name = name;
		this.parent = null;
		this.editable = editable == null ? true : editable;
		this.setEditable(this.editable);
		this.addTextListener(this);
	}
	
	public CTextField(String name, String text, Boolean editable){
		super(text);
		this.init(name, editable);
	}	
	public CTextField(String name, Boolean editable){
		super();
		this.init(name, editable);
	}
	public CTextField(String name){
		this(name, null);
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
