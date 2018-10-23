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

public abstract class CTabPanel extends javax.swing.JTabbedPane implements Parent, Child {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private Parent parent;
	private java.util.Map<String, CPanel> childrenContainer;
	
	protected final void init(String name) {
		this.name = name;
		this.childrenContainer = new java.util.HashMap<String, CPanel>();
	}
	
	public CTabPanel(String name){
		super();
		this.init(name);
	}
	
	public void createTab(String title, javax.swing.ImageIcon icon, CPanel panel, String tip){
		this.childrenContainer.put(title, panel);
		this.inprintOnChild((Child)panel);
		this.addTab(title, icon, panel, tip);
	}
	public void createTab(String title, javax.swing.ImageIcon icon, CPanel panel){
		this.createTab(title, icon, panel, null);
	}
	public void createTab(String title, CPanel panel){
		this.createTab(title, null, panel);
	}
	
	public void removeTab(int index){
		this.childrenContainer.remove(this.getTitleAt(index));
		this.removeTabAt(index);
	}
	
	public String getName(){
		return this.name;
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
	public void inprintOnChild(Child child) {
		child.registerParent(this);
	}

	@Override
	public Child findChildByName(String name) {
		for(CPanel p:this.childrenContainer.values()){
			Child out = p.findChildByName(name);
			if (out != null)
				return out;
		}
		return null;
	}

	@Override
	public <T> T findChildByName(String name, Class<T> clazz) {
		Child out = this.findChildByName(name);
		if (out == null)
			return null;
		return clazz.isInstance(out) ? clazz.cast(out) : null;
	}

	@Override
	public Parent findHigherParent() {
		if (this.parent == null)
			return null;
		else
			return this.parent.findHigherParent();
	}

}
