package org.sam.swing.table.renderer;

import java.awt.Color;
import java.awt.Component;
import java.util.Map;

import javax.swing.CellRendererPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.sam.swing.JSRadioButtonGroup;


/**
 * 单选组对象
 * 
 * @author sam
 *
 */
public class JSTableRadioButtonGroupRenderer<V,T> extends CellRendererPane implements TableCellRenderer {

	private static final long serialVersionUID = -4045115531523620178L;

	/**
	 * 单选控件组
	 */
	private JSRadioButtonGroup<V,T> radiobuttons;

	/**
	 * 未选中的时候背景颜色
	 */
	private Color unselectedForeground;

	/**
	 * 选中的时候背景颜色
	 */
	private Color unselectedBackground;

	/**
	 * 绑定的数据字典
	 */
	private Map<V,T> map;
	
	/**
	 * 绑定的数据字典
	 * @return
	 */
	public Map<V,T> getMap() {
		return map;
	}

	/**
	 * 绑定的数据字典
	 * @param map
	 */
	public void setMap(Map<V,T> map) {
		this.map = map;
	}

	/**
	 * Overrides <code>JComponent.setForeground</code> to assign the
	 * unselected-foreground color to the specified color.
	 *
	 * @param c
	 *            set the foreground color to this value
	 */
	@Override
	public void setForeground(Color c) {
		super.setForeground(c);
		unselectedForeground = c;
	}

	/**
	 * Overrides <code>JComponent.setBackground</code> to assign the
	 * unselected-background color to the specified color.
	 *
	 * @param c
	 *            set the background color to this value
	 */
	@Override
	public void setBackground(Color c) {
		super.setBackground(c);
		unselectedBackground = c;
	}

	/**
	 * 单选组对象
	 * 
	 * @param map
	 *            数据字典
	 * @param defaultValue
	 *            默认值
	 */
	public JSTableRadioButtonGroupRenderer(Map<V,T> map, V defaultValue) {
		this(map);
		radiobuttons.setSelectedValue(defaultValue);
	}
	
	/**
	 * 带数据字典，默认选中值，控件方向的构造函数
	 * @param map
	 *            数据字典
	 * @param defaultValue
	 *            默认值
	 * @param axis
	 *            0 x方向；1 y方向
	 * @see javax.swing.BoxLayout.X_AXIS
	 * @see javax.swing.BoxLayout.Y_AXIS
	 */
	public JSTableRadioButtonGroupRenderer(Map<V,T> map, V defaultValue,int axis) {
		this(map);
		radiobuttons.setSelectedValue(defaultValue);
		radiobuttons.setLayoutAxis(axis);
	}
	
	/**
	 * 只带数据字典的构造函数
	 * @param map
	 */
	public JSTableRadioButtonGroupRenderer(Map<V,T> map)
	{
		super();
		this.map = map;
		this.radiobuttons = new JSRadioButtonGroup<>(map);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		radiobuttons.setSelectedValue((V)value);

		JTable.DropLocation dropLocation = table.getDropLocation();
		if (dropLocation != null && !dropLocation.isInsertRow() && !dropLocation.isInsertColumn()
				&& dropLocation.getRow() == row && dropLocation.getColumn() == column) {
			
			isSelected = true;
		}

		if (isSelected) {
			super.setForeground(table.getSelectionForeground());
			super.setBackground(table.getSelectionBackground());
			this.radiobuttons.setForeground(table.getSelectionForeground());
			this.radiobuttons.setBackground(table.getSelectionBackground());
		} else {
			super.setForeground(unselectedForeground != null ? unselectedForeground : table.getForeground());
			super.setBackground(unselectedBackground != null ? unselectedBackground : table.getBackground());
			this.radiobuttons.setForeground(this.getForeground());
			this.radiobuttons.setBackground(this.getBackground());
		}
		
		return radiobuttons;
	}
    
}
