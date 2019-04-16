package org.sam.swing;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.JDialog;

public class JSDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7236196432243929113L;

	//begin constructor
	
	/**
	 * {@inheritDoc}
	 */
	public JSDialog() {
		this((Frame) null, false);
	}

	/**
	 * {@inheritDoc}
	 */
	public JSDialog(Frame owner) {
		this(owner, false);
	}

	/**
	 * {@inheritDoc}
	 */
	public JSDialog(Frame owner, boolean modal) {
		this(owner, "", modal);
	}

	/**
	 * {@inheritDoc}
	 */
	public JSDialog(Frame owner, String title) {
		this(owner, title, false);
	}

	/**
	 * {@inheritDoc}
	 */
	public JSDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
	}

	/**
	 * {@inheritDoc}
	 */
	public JSDialog(Frame owner, String title, boolean modal, GraphicsConfiguration gc) {
		super(owner, title, modal, gc);
	}

	/**
	 * {@inheritDoc}
	 */
	public JSDialog(Dialog owner) {
		this(owner, false);
	}

	/**
	 * {@inheritDoc}
	 */
	public JSDialog(Dialog owner, boolean modal) {
		this(owner, "", modal);
	}

	/**
	 * {@inheritDoc}
	 */
	public JSDialog(Dialog owner, String title) {
		this(owner, title, false);
	}

	/**
	 * {@inheritDoc}
	 */
	public JSDialog(Dialog owner, String title, boolean modal) {
		super(owner, title, modal);
	}

	/**
	 * {@inheritDoc}
	 */
	public JSDialog(Dialog owner, String title, boolean modal, GraphicsConfiguration gc) {
		super(owner, title, modal, gc);
	}

	/**
	 * {@inheritDoc}
	 */
	public JSDialog(Window owner) {
		this(owner, ModalityType.MODELESS);
	}

	/**
	 * {@inheritDoc}
	 */
	public JSDialog(Window owner, ModalityType modalityType) {
		this(owner, "", modalityType);
	}

	/**
	 * {@inheritDoc}
	 */
	public JSDialog(Window owner, String title) {
		this(owner, title, ModalityType.MODELESS);
	}

	/**
	 * {@inheritDoc}
	 */
	public JSDialog(Window owner, String title, ModalityType modalityType) {
		super(owner, title, modalityType);
	}

	/**
	 * {@inheritDoc}
	 */
	public JSDialog(Window owner, String title, ModalityType modalityType, GraphicsConfiguration gc) {
		super(owner, title, modalityType, gc);
	}
	
	//end
	
	//begin property
	
	/**
	 * 确定操作
	 */
	public static final String OK = "ok";
	
	/**
	 * 取消操作
	 */
	public static final String CANCEL = "cancel";
	
	/**
	 * 当前的执行动作
	 */
	private String action = CANCEL;

	/**
	 * 当前的执行动作
	 * @return
	 */
	public String getAction() {
		return action;
	}

	/**
	 * 当前的执行动作
	 * @param action
	 */
	public void setAction(String action) {
		this.action = action;
	}
	
	//end 
}
