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

public abstract class CComboBox<T> extends javax.swing.JComboBox<T> implements CActionListener, Child {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private Parent parent;
	private java.util.List<T> collection;
	
	private void syncList(){
		if (collection != null){
			this.clear();
			for(T item:this.collection){
				this.addItem(item);
			}
		}			
	}
	
	protected final void init(String name, java.util.List<T> collection){
		this.name = name;
		this.collection = collection == null ? new java.util.ArrayList<T>()
				: collection;
		this.syncList();
		this.addActionListener(this);
	}
	
	public CComboBox(String name){
		this(name, null);
	}
	public CComboBox(String name, java.util.List<T> collection){
		super();
		this.init(name, collection);
	}
	
	public String getName(){
		return this.name;
	}
	
	public void addOnList(T item){
		this.collection.add(item);
		this.syncList();
	}
	
	public void removeOnList(T item){
		this.collection.remove(item);
		this.syncList();
	}
	
	public void removeOnList(int index){
		this.removeOnList(this.getItemAt(index));
	}
	
	public T getOnList(int index){
		return this.getItemAt(index);
	}
	
	public T getSelected(){
		return this.collection.get(this.collection.indexOf(this.getSelectedItem()));
	}
	
	public void appendOnList(java.util.List<T> sublist){
		this.collection.addAll(sublist);
		this.syncList();
	}
	
	public void overrideList(java.util.List<T> newlist){
		this.collection = newlist;
		this.syncList();
	}
	
	public java.util.List<T> getList(){
		// TODO potencial problema de desempenho, averiguar se eh mesmo necessario duplicar lista!
		java.util.List<T> out = new java.util.ArrayList<T>();
		for(T item:this.collection){
			out.add(item);
		}
		return out;
	}
	
	public void clear(){
		this.collection = new java.util.ArrayList<T>();
		this.syncList();
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
