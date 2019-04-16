package org.sam.swing.table.renderer;

import java.awt.Component;
import java.io.Serializable;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * 数据字典类型的显示控件
 * @author sam
 *
 * @param <V> 绑定值
 * @param <T> 显示值
 */
public class JSTableMapRender<V,T> extends DefaultTableCellRenderer implements TableCellRenderer,Serializable {

	private static final long serialVersionUID = 4431452687038719128L;
	
	/*
	 * 当前的显示值
	 */
	private Map<V,T> datas;

	/**
	 * 数据
	 * @param datas
	 */
	public JSTableMapRender(Map<V,T> datas) {
		this.datas = datas;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		this.setValue(value);
		if (value != null) {
			try {
				this.setText(this.datas.get(value).toString());
			} catch (Exception e) {
				e.printStackTrace();
				this.setText("");
			}
		} else {
			this.setText("");
		}
		
		if (isSelected) {
			this.setForeground(table.getSelectionForeground());
			this.setBackground(table.getSelectionBackground());
		} else {
			this.setForeground(table.getForeground());
			this.setBackground(table.getBackground());
		}
		
		return this;
	}

}
