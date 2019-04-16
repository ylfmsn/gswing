package org.sam.swing;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JRadioButton;

/**
 * 带有绑定值的radio对象
 * 
 * @author sam
 *
 */
public class JSRadioButton extends JRadioButton {

	private static final long serialVersionUID = 1549941455677458829L;

	/**
	 * 绑定值
	 */
	private Object tag;

	/**
	 * 绑定值
	 * 
	 * @return
	 */
	public Object getTag() {
		return tag;
	}

	/**
	 * 绑定值
	 * 
	 * @param tag
	 */
	public void setTag(Object tag) {
		this.tag = tag;
	}

	/**
	 * Creates an initially unselected radio button with no set text.
	 */
	public JSRadioButton() {
		this(null, null, null, false);
	}
	
	/**
	 * 只有绑定值的控件，
	 * @param tag 绑定值
	 */
	public JSRadioButton(Object tag)
	{
		this(tag == null ? "" : tag.toString(), null, tag, false);
	}

	/**
	 * 带有图标，绑定值的构造函数
	 * @param icon 图标
	 * @param tag 绑定值
	 */
	public JSRadioButton(Icon icon, Object tag) {
		this(null, icon, tag, false);
	}

	/**
	 * Creates a radiobutton where properties are taken from the Action
	 * supplied.
	 *
	 * @since 1.3
	 */
	public JSRadioButton(Action a) {
		this();
		setAction(a);
	}

	/**
	 * 图标，绑定值，是否选中的构造函数
	 * 
	 * @param icon
	 *            图标
	 * @param tag
	 *            绑定值
	 * @param selected
	 *            是否选中
	 */
	public JSRadioButton(Icon icon, Object tag, boolean selected) {
		this(null, icon, tag, selected);
	}

	/**
	 * 带有显示值和绑定值的构造函数
	 * 
	 * @param text
	 *            显示值
	 * @param tag
	 *            绑定值
	 */
	public JSRadioButton(String text, Object tag) {
		this(text, null, tag, false);
	}

	/**
	 * 是否选中的构造函数
	 * 
	 * @param text
	 *            文本
	 * @param tag
	 *            绑定值
	 * @param selected
	 *            是否选中
	 */
	public JSRadioButton(String text, Object tag, boolean selected) {
		this(text, null, tag, selected);
	}

	/**
	 * 默认不选中的所有构造函数
	 * 
	 * @param text
	 *            文本
	 * @param icon
	 *            图标
	 * @param tag
	 *            绑定值
	 */
	public JSRadioButton(String text, Icon icon, Object tag) {
		this(text, icon, tag, false);
	}

	/**
	 * 带有全部参数的构造函数
	 * 
	 * @param text
	 *            显示文本
	 * @param icon
	 *            图标
	 * @param tag
	 *            绑定值
	 * @param selected
	 *            是否选中
	 */
	public JSRadioButton(String text, Icon icon, Object tag, boolean selected) {
		super(text, icon, selected);
		this.setTag(tag);
		setBorderPainted(false);
		setHorizontalAlignment(LEADING);
	}
}
