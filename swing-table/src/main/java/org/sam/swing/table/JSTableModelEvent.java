package org.sam.swing.table;

import java.util.EventObject;

/**
 * JXTableModle的时间参数对象
 * @author sam
 *
 */
public class JSTableModelEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2264933133557562986L;
	
	/**
	 * 是否取消下一个步骤操作
	 */
	private boolean cancel = false;
	
	/**
	 * 当前操作的行号
	 */
	private int row = -1;
	
	/**
	 * 操作结果
	 */
	private boolean result = true;
	
	/**
	 * 附加的消息
	 */
	private String msg;
	
	/**
	 * 当前操作的tablemodel对象
	 */
	private JSTableModel<?> tableModel;
	
	/**
	 * 当前操作的tablemodel对象
	 * @return
	 */
	public JSTableModel<?> getTableModel() {
		return tableModel;
	}

	/**
	 * 当前操作的tablemodel对象
	 * @param tableModel
	 */
	public void setTableModel(JSTableModel<?> tableModel) {
		this.tableModel = tableModel;
	}

	/**
	 * 是否取消下一个步骤操作
	 * @return
	 */
	public boolean isCancel() {
		return cancel;
	}

	/**
	 * 是否取消下一个步骤操作
	 * @param cancel
	 */
	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

	/**
	 * 当前操作的行号
	 * @return
	 */
	public int getRow() {
		return row;
	}

	/**
	 * 当前操作的行号
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * 操作结果
	 * @return
	 */
	public boolean getResult() {
		return result;
	}

	/**
	 * 操作结果
	 * @param result
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

	/**
	 * 附加的消息
	 * @return
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 附加的消息
	 * @param msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * JXTableModle的时间参数对象
	 * @param source
	 */
	public JSTableModelEvent(Object source) {
		super(source);
		this.setTableModel((JSTableModel<?>)source);
	}

}
