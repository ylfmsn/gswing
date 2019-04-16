package org.sam.swing;

import javax.swing.JTextField;

/**
 * 高级的文本输入框
 * @author sam
 *
 */
public class JSTextField extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7107438243257321277L;
	
	/**
	 * 原始值
	 */
	private String orginal = "";
	
	/**
	 * 后台绑定值
	 */
	private String tag = "";

	/**
	 * 
	 * @param text
	 */
	public JSTextField(String text)
	{
		super(text);
		this.orginal = text;
	}

	/**
	 * 原始值
	 * @return
	 */
	public String getOrginal() {
		return orginal;
	}

	/**
	 * 原始值
	 * @param orginal
	 */
	public void setOrginal(String orginal) {
		this.orginal = orginal;
	}

	/**
	 * 后台绑定值
	 * @return
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * 后台绑定值
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
}
