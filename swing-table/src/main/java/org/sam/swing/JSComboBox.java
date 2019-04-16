package org.sam.swing;

import java.util.Vector;

import javax.swing.JComboBox;

/**
 * 扩展的swing JComboBox下拉列表框 +可以绑定代码操作
 * 
 * @author sam
 *
 * @param <E>
 */
public class JSComboBox<T,V> extends JComboBox<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6360305558680319285L;

	/**
	 * 绑定的数据列表
	 */
	protected Vector<V> values;
	
	/**
	 * 无数据的下拉列表框
	 */
	public JSComboBox()
	{
		this(new Vector<T>() , new Vector<V>());
	}

	/**
	 * 实现绑定值和显示值2个操作 请按照次序绑定上
	 * 
	 * @param items
	 *            显示值
	 * @param datas
	 *            绑定值
	 */
	public JSComboBox(T[] items, V[] datas) {
		super(items);
		values = new Vector<V>(datas.length);
		int i, c;
		for (i = 0, c = items.length; i < c; i++)
			values.addElement(datas[i]);
	}

	/**
	 * 实现绑定值和显示值2个操作 请按照次序绑定上
	 * @param items 显示值
	 * @param datas 绑定值
	 */
	public JSComboBox(Vector<T> items, Vector<V> datas)
	{
		super(items);
		this.values = datas;
	}

	/**
	 * 获取当前选中的节点对应的值
	 * @return 没有返回空
	 */
	public V getSelectedValue()
	{
		int index = this.getSelectedIndex();
		
		if (index < 0)
			return null;
		
		if (this.values == null || this.values.size() <= 0 || index >= this.values.size())
			return null;
		
		return values.get(this.getSelectedIndex());
	}
	
	
	
	/**
	 * 设置选择值
	 * @param v 要选择的值
	 */
	public void setSelectedValue(V v)
	{
		int index = this.values.indexOf(v);
		this.setSelectedIndex(index);
	}
}
