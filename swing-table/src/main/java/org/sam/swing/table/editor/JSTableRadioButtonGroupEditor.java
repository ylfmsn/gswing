package org.sam.swing.table.editor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.EventObject;
import java.util.Map;

import javax.swing.AbstractCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;

import org.sam.swing.JSRadioButtonGroup;

/**
 * 单选按钮组editor
 * 
 * @author sam
 *
 */
public class JSTableRadioButtonGroupEditor<V, T> extends AbstractCellEditor implements TableCellEditor, TreeCellEditor {

	private static final long serialVersionUID = 263310385939006964L;

	/**
	 * 当前操作的控件
	 */
	protected JSRadioButtonGroup<V, T> editorComponent;

	/**
	 * 事件代理对象
	 */
	protected EditorDelegate delegate;

	/**
	 * 带有默认值的构造函数
	 * 
	 * @param map
	 *            数据字典
	 * @param defaultValue
	 *            默认选中值
	 */
	public JSTableRadioButtonGroupEditor(Map<V, T> map, V defaultValue) {
		this(map);
		editorComponent.setSelectedValue(defaultValue);
	}

	/**
	 * 带有按钮方向的构造函数
	 * 
	 * @param map
	 *            数据字典
	 * @param defaultValue
	 *            默认值
	 * @param axis
	 *            0 x方向；1 y方向
	 * @see javax.swing.BoxLayout.X_AXIS
	 * @see javax.swing.BoxLayout.Y_AXIS
	 */
	public JSTableRadioButtonGroupEditor(Map<V, T> map, V defaultValue, int axis) {
		this(map);
		editorComponent.setSelectedValue(defaultValue);
		editorComponent.setLayoutAxis(axis);
	}

	/**
	 * 无默认选中值的构造函数
	 * 
	 * @param map
	 */
	public JSTableRadioButtonGroupEditor(Map<V, T> map) {
		if (map == null)
			throw new IllegalArgumentException();

		editorComponent = new JSRadioButtonGroup<V, T>(map);
		delegate = new EditorDelegate() {
			private static final long serialVersionUID = -4724768865052360104L;

			@Override
			public void setValue(Object value) {
				super.setValue(value);
			}

			@Override
			public Object getCellEditorValue() {
				return editorComponent.getSelectedValue();
			}
		};
		delegate.setClickCountToStart(1); // 默认单击编辑
	}

	/**
	 * 鼠标操作多少次启动控件的操作
	 * 
	 * @param count
	 */
	public void setClickCountToStart(int count) {
		delegate.setClickCountToStart(count);
	}

	/**
	 * 鼠标操作多少次启动控件的操作
	 * 
	 * @return
	 */
	public int getClickCountToStart() {
		return delegate.getClickCountToStart();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.CellEditor#getCellEditorValue()
	 */
	@Override
	public Object getCellEditorValue() {
		return delegate.getCellEditorValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.AbstractCellEditor#isCellEditable(java.util.EventObject)
	 */
	@Override
	public boolean isCellEditable(EventObject anEvent) {
		return delegate.isCellEditable(anEvent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.AbstractCellEditor#shouldSelectCell(java.util.EventObject)
	 */
	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		return delegate.shouldSelectCell(anEvent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.AbstractCellEditor#stopCellEditing()
	 */
	@Override
	public boolean stopCellEditing() {
		if (editorComponent.getSelectedValue() == null) {
			JOptionPane.showMessageDialog(null, "请选择单选按钮");
			return false;
		}
		return delegate.stopCellEditing();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.AbstractCellEditor#cancelCellEditing()
	 */
	@Override
	public void cancelCellEditing() {
		delegate.cancelCellEditing();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.tree.TreeCellEditor#getTreeCellEditorComponent(javax.swing.
	 * JTree, java.lang.Object, boolean, boolean, boolean, int)
	 */
	public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
			boolean leaf, int row) {
		String stringValue = tree.convertValueToText(value, isSelected, expanded, leaf, row, false);

		delegate.setValue(stringValue);
		return editorComponent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing
	 * .JTable, java.lang.Object, boolean, int, int)
	 */
	@SuppressWarnings("unchecked")
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		delegate.setValue(value);
		editorComponent.setSelectedValue((V) value);

		return editorComponent;
	}

	/**
	 * 实现的内部方法
	 * 
	 * @author sam
	 *
	 */
	protected class EditorDelegate extends JSEditorDelegateAdapter {

		private static final long serialVersionUID = -9163652875049976071L;

		/**
		 * Stops editing and returns true to indicate that editing has stopped.
		 * This method calls <code>fireEditingStopped</code>.
		 *
		 * @return true
		 */
		public boolean stopCellEditing() {
			fireEditingStopped();
			return true;
		}

		/**
		 * Cancels editing. This method calls <code>fireEditingCanceled</code>.
		 */
		@Override
		public void cancelCellEditing() {
			fireEditingCanceled();
		}

		/**
		 * When an action is performed, editing is ended.
		 * 
		 * @param e
		 *            the action event
		 * @see #stopCellEditing
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			JSTableRadioButtonGroupEditor.this.stopCellEditing();
		}

		/**
		 * When an item's state changes, editing is ended.
		 * 
		 * @param e
		 *            the action event
		 * @see #stopCellEditing
		 */
		@Override
		public void itemStateChanged(ItemEvent e) {
			JSTableRadioButtonGroupEditor.this.stopCellEditing();
		}
	}

}