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
import br.com.cyberkitty.felinetoolset.cframe.event.CActionListener;

public abstract class CButton extends javax.swing.JButton implements CActionListener, Child {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Parent parent;
	
	public String getName(){
		return this.name;
	}
	
	protected final void init(String name){
		this.name = name;
		this.parent = null;
		this.addActionListener(this);
	}
	
	public CButton(String name, String text){
		super(text);
		this.init(name);
	}
	public CButton(String name){
		super();
		this.init(name);
	}
	
	/**
	 * <br>Enables the button.
	 */
	public void activate(){
		super.setEnabled(true);
	}
	
	/**
	 * <br>Disables the button.
	 */
	public void deactivate(){
		this.setEnabled(false);
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
