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

package br.com.cyberkitty.felinetoolset.cframe;

import br.com.cyberkitty.felinetoolset.cframe.complements.Parent;
import br.com.cyberkitty.felinetoolset.cframe.complements.Child;
import br.com.cyberkitty.felinetoolset.cframe.event.CTextListener;

public abstract class CTextArea extends java.awt.TextArea implements CTextListener, Child {
	
	/**
	 * Default serial version.
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Parent parent;
	private Boolean editable;
	private javax.swing.JScrollPane scroll;
	
	public String getName(){
		return this.name;
	}
	public boolean isEditable(){
		return this.editable;
	}
	
	public javax.swing.JScrollPane getScrollPane(){
		return this.scroll;
	}
	
	protected final void init(String name, Boolean editable){
		this.name = name;
		this.parent = null;
		this.editable = editable == null ? true : editable;
		this.setEditable(this.editable);
		this.scroll = new javax.swing.JScrollPane(this);
		this.addTextListener(this);
	}
	
	public CTextArea(String name){
		this(name, null);
	}
	public CTextArea(String name, Boolean editable){
		super();
		this.init(name, editable);
	}
	public CTextArea(String name, String text, Boolean editable){
		super(text);
		this.init(name, editable);
	}
	
	public void newLine() {
		this.append("\n");
	}
	public void addStr(String str) {
		this.append(str);
	}
	public void addLine(String str) {
		this.addStr(str);
		this.newLine();
	}
	public void addText(String[] text) {
		if (text != null)
			for(String l:text){
				this.addLine(l);
			}
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
