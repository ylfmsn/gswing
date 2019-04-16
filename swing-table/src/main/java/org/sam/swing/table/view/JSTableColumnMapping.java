/**
 * 
 */
package org.sam.swing.table.view;

import java.io.Serializable;

import org.sam.swing.table.JSTableColumn;

/**
 * 列映射关系接口
 * @author sam
 *
 */
public interface JSTableColumnMapping extends Serializable {
	
	/**
	 * 获取列定义的列表
	 * @return
	 */
	public JSTableColumn[] getColumns();
	
	/**
	 * 设置当前列定义列表
	 * @param columns
	 */
	public void setColumns(JSTableColumn... columns);
	
	/**
	 * 增加一个列
	 * @param column
	 */
	public void addColumn(JSTableColumn column);
	
	/**
	 * 设置数据访问层对象
	 */
	public <Dao> void setDao(Dao dao);
	
	/**
	 * 获取实体层对象
	 * @return
	 */
	public <Dao> Dao getDao();
	
	/**
	 * 获取实体的类型
	 * @return
	 */
	public Class<?> getEntityClss();

	/**
	 * 设置实体的类型
	 * @param cls
	 */
	public void setEntityCls(Class<?> cls);
}
