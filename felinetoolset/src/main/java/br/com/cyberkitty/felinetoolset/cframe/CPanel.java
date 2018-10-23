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

import java.awt.Component;

import br.com.cyberkitty.felinetoolset.cframe.CLabel.Aling;
import br.com.cyberkitty.felinetoolset.cframe.complements.Parent;
import br.com.cyberkitty.felinetoolset.cframe.complements.Child;
import br.com.cyberkitty.felinetoolset.cframe.config.Config;
import br.com.cyberkitty.felinetoolset.cframe.utils.SpringUtilities;

public abstract class CPanel extends javax.swing.JPanel implements Parent, Child {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private Parent parent;
	private boolean autoFormat = false;
	private java.util.List<java.awt.Component> topDownComponent;
	private java.util.List<java.awt.Component> leftRightComponent;
	private java.util.List<CPanel> childrenContainer;

	private void includeComponents(){
		int i = -1;
		int j = -1;
		boolean completed = false;
		boolean hadAddition = false;
		while (!completed){
			i++;
			j++;
			hadAddition = false;
			if (this.topDownComponent.size() > 0 && i < this.topDownComponent.size()){
				this.add(this.topDownComponent.get(i));
				hadAddition = true;
			}
			if (this.leftRightComponent.size() > 0 && j < this.leftRightComponent.size()){
				this.add(this.leftRightComponent.get(j));
				hadAddition = true;
			}
			if (!hadAddition)
				completed = true;
		}		
	}
	
	/**
	 * Register a CLabel component and his Target Component on the right order.
	 * @param comp a {@link br.com.cyberkitty.felinetoolset.cframe.CLabel} to be registered.
	 * @param leftRight a {@link boolean} to define if its left-right oriented.
	 * @param topDown a {@link boolean} to define if its top-down oriented.
	 */
	public void registerLabel(CLabel comp, boolean leftRight, boolean topDown){
		if (comp.isLabelFor()) {
			Aling alingment = comp.getAlingment();
			if (alingment == Aling.LEFT){
				this.leftRightComponent.add(comp);
				this.leftRightComponent.add(comp.getLabelTarget());
			}
			else if (alingment == Aling.RIGHT){
				this.leftRightComponent.add(comp.getLabelTarget());
				this.leftRightComponent.add(comp);
			}
			else if (alingment == Aling.TOP){
				this.topDownComponent.add(comp);
				this.topDownComponent.add(comp.getLabelTarget());
			}
			else if (alingment == Aling.DOWN){
				this.topDownComponent.add(comp.getLabelTarget());
				this.topDownComponent.add(comp);
			}
		}
		else {
			if (leftRight)
				this.leftRightComponent.add(comp);
			else if (topDown)
				this.topDownComponent.add(comp);
			else
				this.add(comp);
		}
	}
	
	/**
	 * Removes a CLabel and his Target Component.
	 * @param comp
	 */
	private void removeLabel(CLabel comp){
		if (comp.isLabelFor()){
			this.topDownComponent.remove(comp.getLabelFor());
			this.leftRightComponent.remove(comp.getLabelFor());
		}
		this.topDownComponent.remove(comp);
		this.leftRightComponent.remove(comp);
	}

	protected final void init(String name, boolean autoFormat){
		this.name = name;
		this.parent = null;
		this.autoFormat = autoFormat;
		this.topDownComponent = new java.util.ArrayList<java.awt.Component>();
		this.leftRightComponent = new java.util.ArrayList<java.awt.Component>();
		this.childrenContainer = new java.util.ArrayList<CPanel>();
	}

	protected final void format(){
		if (this.autoFormat){
			// Include components
			this.includeComponents();

			// Set parameters
			int totalRows = this.topDownComponent.size() <= 0 ? 1 : this.topDownComponent.size();
			int totalCols = this.leftRightComponent.size() <= 0 ? 1 : this.leftRightComponent.size();

			// Format all children containers
			for(CPanel cc:this.listChildrenContainer()){
				cc.format();
			}

			// Lay out the panel.
			SpringUtilities
			.makeCompactGrid(this,
					totalRows, totalCols,                    //rows, cols
					Config.getSpringInitX(), Config.getSpringInitY(),  //initX, initY
					Config.getSpringXPad(), Config.getSpringYPad());   //xPad, yPad
		}
	}

	public CPanel(String name, boolean autoFormat){
		super(new javax.swing.SpringLayout());
		this.init(name, autoFormat);
	}

	public String getName(){
		return this.name;
	}
	
	/**
	 * Overridden for <i>auto-format</i> features. If <i>auto-format</i> 
	 * are <b>enabled</b>, this method <b>should not be used directly</b>.
	 * <br>
	 */
	@Override
	public Component add(java.awt.Component comp){
		if (comp instanceof Child)
			this.inprintOnChild((Child)comp);
		return super.add(comp);
	}

	public CPanel[] listChildrenContainer(){
		return this.childrenContainer.toArray(new CPanel[]{});
	}

	public void registerTopDownComponent(java.awt.Component comp){
		if (comp != null){
			if (comp instanceof CPanel)
				this.childrenContainer.add((CPanel)comp);
			if (comp instanceof CLabel)
				this.registerLabel((CLabel)comp, false, true);
			else
				this.topDownComponent.add(comp);
		}
	}

	public void registerLeftRightComponent(java.awt.Component comp){
		if (comp != null){
			if (comp instanceof CPanel)
				this.childrenContainer.add((CPanel)comp);
			if (comp instanceof CLabel)
				this.registerLabel((CLabel)comp, true, false);
			else
				this.leftRightComponent.add(comp);
		}
	}

	public void removeComponent(java.awt.Component comp){
		if (comp != null){
			if (comp instanceof CPanel)
				this.childrenContainer.add((CPanel)comp);
			if (comp instanceof CLabel)
				this.removeLabel((CLabel)comp);
			else {
				this.topDownComponent.remove(comp);
				this.leftRightComponent.remove(comp);
			}
		}
	}
	
	public void setEnableAllControllers(boolean enabled) {
		for (java.awt.Component c:this.topDownComponent){
			c.setEnabled(enabled);
		}
		for (java.awt.Component c:this.leftRightComponent){
			c.setEnabled(enabled);
		}
		for(CPanel cp:this.childrenContainer){
			cp.setEnableAllControllers(enabled);
		}
	}
	
	@Override
	public void inprintOnChild(Child child){
		child.registerParent(this);
	}

	@Override
	public Child findChildByName(String name){
		for(java.awt.Component c:this.topDownComponent){
			if (c.getName().equals(name) && c instanceof Child)
				return (Child)c;
		}
		for(java.awt.Component c:this.leftRightComponent){
			if (c.getName().equals(name))
				return (Child)c;			
		}
		for(CPanel cp:this.childrenContainer){
			Child out = cp.findChildByName(name);
			if (out != null)
				return out;
		}
		return null;
	}
	
	@Override
	public <T> T findChildByName(String name, Class<T> clazz){
		Child out = this.findChildByName(name);
		if (out == null)
			return null;
		return clazz.isInstance(out) ? clazz.cast(out) : null;
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
			return this;
		else
			return this.parent.findHigherParent();
	}

}
