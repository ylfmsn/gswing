package org.sam.swing.table.view;

import java.util.LinkedList;
import java.util.List;

import org.sam.swing.table.JSTableColumn;

/**
 * 实现默认接口方法的抽象类
 * @author sam
 *
 */
public abstract class JSTableColumnMappingAbstract implements JSTableColumnMapping {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6355696734931125001L;
	
	/**
	 * 当前对象的类定义列表
	 */
	private List<JSTableColumn> cols = new LinkedList<>();
	
	/**
	 * 当前的实体class类型
	 */
	private Class<?> entityCls;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JSTableColumn[] getColumns() {
		if (cols == null || cols.isEmpty())
			return null;
		
		return cols.toArray(new JSTableColumn[cols.size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setColumns(JSTableColumn... columns) {
		this.cols.clear();
		
		if (columns != null && columns.length > 0)
		{
			for(JSTableColumn col : columns)
			{
				this.cols.add(col);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addColumn(JSTableColumn column) {
		
		if (column != null)
			this.cols.add(column);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> getEntityClss() {
		return this.entityCls;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEntityCls(Class<?> cls) {
		this.entityCls = cls;
	}

	/**
	 * 继承类必须实现这个方法
	 */
	public abstract void initCols();
}
