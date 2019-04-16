package org.sam.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.lang.reflect.Field;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.TreeCellRenderer;

public class JSCheckBoxTreeRender extends JPanel implements TreeCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1474422397548188618L;

	// begin 属性

	/**
	 * 绑定对象
	 */
	private Object tag;

	/**
	 * 是否选中
	 * 
	 * @return
	 */
	public Boolean getChecked() {
		return this.checkBox.isSelected();
	}

	/**
	 * 是否选中
	 * 
	 * @param checked
	 */
	public void setChecked(Boolean checked) {
		this.checkBox.setSelected(checked);
	}

	/**
	 * 文本
	 * 
	 * @return
	 */
	public String getText() {
		return this.label.getText();
	}

	/**
	 * 文本
	 * 
	 * @param text
	 */
	public void setText(String text) {
		this.label.setText(text);
	}

	/**
	 * 绑定对象
	 * 
	 * @return
	 */
	public Object getTag() {
		return tag;
	}

	/**
	 * 绑定对象
	 * 
	 * @param tag
	 */
	public void setTag(Object tag) {
		this.tag = tag;
	}

	// end

	// begin UI

	/**
	 * 显示的文本
	 */
	private JLabel label;

	/**
	 * 文本框
	 */
	private JCheckBox checkBox;

	// end

	/**
	 * 带参数的构造函数
	 * 
	 * @param text
	 *            文本
	 * @param checked
	 *            选中状态
	 * @param tag
	 *            绑定的对象
	 */
	public JSCheckBoxTreeRender(String text, boolean checked, Object tag) {
		super();
		this.label = new JLabel(text);
		this.checkBox = new JCheckBox();
		this.checkBox.setSelected(checked);

		checkBox.setBackground(UIManager.getColor("Tree.textBackground"));
		label.setForeground(UIManager.getColor("Tree.textForeground"));

		this.setLayout(new BorderLayout());
		this.add(this.checkBox, BorderLayout.WEST);
		this.add(this.label, BorderLayout.CENTER);

		this.setTag(tag);
	}

	/**
	 * 带参数的构造函数
	 * 
	 * @param text
	 *            文本
	 * @param checked
	 *            选中状态
	 */
	public JSCheckBoxTreeRender(String text, boolean checked) {
		this(text, checked, null);
	}

	/**
	 * 带参数的构造函数
	 * 
	 * @param text
	 */
	public JSCheckBoxTreeRender(String text) {
		this(text, false, null);
	}

	/**
	 * 不带参数的构造函数
	 */
	public JSCheckBoxTreeRender() {
		this("", false, null);
	}

	/**
	 * 重写的方法
	 */
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {

		setEnabled(tree.isEnabled());
		label.setFont(tree.getFont());

		if (value != null && value instanceof JSCheckMutableTreeNode) 
		{
			JSCheckMutableTreeNode node = (JSCheckMutableTreeNode)value;
			if (node.getUserObject() instanceof String) 
			{
				String stringValue = tree.convertValueToText(value, selected, expanded, leaf, row, hasFocus);
				label.setText(stringValue);
			}
			else if (node.getUserObject() instanceof Object)
			{
				try {			
					Field field = node.getUserObject().getClass().getDeclaredField(node.getTextField());
					field.setAccessible(true);
					label.setText(field.get(node.getUserObject()).toString());

					field = node.getUserObject().getClass().getDeclaredField(node.getCheckField());
					field.setAccessible(true);

					if (field.getType().equals(Boolean.class)) {
						this.checkBox.setSelected(field.getBoolean(node.getUserObject()));
					} else if (field.getType().equals(Integer.class)) {				
						this.checkBox.setSelected(Integer.valueOf(1).equals((Integer)field.get(node.getUserObject())));
					} else if (field.getType().equals(String.class)) {
						String str = field.get(node.getUserObject()).toString();
						this.checkBox.setSelected(str.startsWith("1") || str.startsWith("y") || str.startsWith("Y")
								|| str.startsWith("o") || str.startsWith("O"));
					} else {
						this.checkBox.setSelected(false);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			} 
			else 
			{
				String stringValue = tree.convertValueToText(value, selected, expanded, leaf, row, hasFocus);
				label.setText(stringValue);
			}
		} 
		else 
		{
			String stringValue = tree.convertValueToText(value, selected, expanded, leaf, row, hasFocus);
			label.setText(stringValue);
		}

		if (leaf) {
			label.setIcon(UIManager.getIcon("Tree.leafIcon"));
		} else if (expanded) {
			label.setIcon(UIManager.getIcon("Tree.openIcon"));
		} else {
			label.setIcon(UIManager.getIcon("Tree.closedIcon"));
		}
		
		if (hasFocus)
		{
			label.setBackground(Color.BLUE);
		}
		else 
		{
			label.setBackground(tree.getBackground());
		}

		return this;
	}
}
