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

import java.awt.event.WindowEvent;

import io.github.joanaribeiro.felinetoolset.cframe.complements.Parent;
import io.github.joanaribeiro.felinetoolset.cframe.complements.Child;
import io.github.joanaribeiro.felinetoolset.cframe.event.CWindowListener;

public abstract class CFrame extends javax.swing.JFrame implements CWindowListener, Parent {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private CPanel mainPanel;
	
	public CPanel getMainPanel(){
		return this.mainPanel;
	}
	
	private void defaultCloseOperation(boolean terminateOnClose){
		if (terminateOnClose){
			this.setDefaultCloseOperation(CFrame.EXIT_ON_CLOSE);
		}
	}
	
	protected abstract void build();
	
	protected void format(){
		this.mainPanel.format();
		this.pack();
	}
	
	protected final void init(String name, CPanel mainPanel, boolean terminateOnClose){
		this.name = name;
		this.mainPanel = mainPanel;
		this.setContentPane(mainPanel);
		this.defaultCloseOperation(terminateOnClose);
		this.build();
		this.format();
	}
	
	/**
	 * Overridden for <i>hierarchy</i> features.
	 * <b>Should not be used directly!</b>.
	 * <br>
	 * @param contentPane a {@link java.awt.Component} to be registered as main container of the frame.
	 */
	@Override
	public void setContentPane(java.awt.Container contentPane) {
		if (contentPane instanceof Child)
			this.inprintOnChild((Child)contentPane);
		super.setContentPane(contentPane);
	}
	
	public String getName(){
		return this.name;
	}
	
	public CFrame(String name, CPanel mainPanel, boolean terminateOnClose){
		super();
		this.init(name, mainPanel, terminateOnClose);
	}
	public CFrame(String name, String tittle, CPanel mainPanel, boolean terminateOnClose){
		super(tittle);
		this.init(name, mainPanel, terminateOnClose);
	}
	
	/**
	 * Add the component to a top-down ordination.
	 * @param comp a {@link java.awt.Component} to be registered.
	 */
	public void addTopDownComponent(java.awt.Component comp){
		this.mainPanel.registerTopDownComponent(comp);
	}
	
	/**
	 * Add the component to a left-right ordination.
	 * @param comp a {@link java.awt.Component} to be registered.
	 */
	public void addLeftRightComponent(java.awt.Component comp){
		this.mainPanel.registerLeftRightComponent(comp);
	}
	
	public void disableAllChildren(){
		this.mainPanel.setEnableAllControllers(false);
	}
	
	public void enableAllChildren(){
		this.mainPanel.setEnableAllControllers(true);
	}
	
	/**
	 * Shows the Frame.
	 */
	public void expose(){
		this.setVisible(true);
	}
	
	/**
	 * Hides the Frame.
	 */
	public void conceal(){
		this.setVisible(false);
	}
	
	/**
	 * Destroy the Frame. If it was visible before, this hides it first then destroy it.
	 */
	public void destroy(){
		this.conceal();
		this.dispose();
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void inprintOnChild(Child child) {
		if (child != null)
			child.registerParent(this);
	}

	@Override
	public Child findChildByName(String name) {
		return this.mainPanel.findChildByName(name);
	}

	@Override
	public <T> T findChildByName(String name, Class<T> clazz) {
		return this.mainPanel.findChildByName(name, clazz);
	}

	@Override
	public Parent findHigherParent() {
		return this;
	}

}
