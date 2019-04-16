package org.sam.swing;

/**
 * 代表当前的行，或者对象的编辑状态
 * @author sam
 *
 */
public enum ItemStatus {
	/**
	 * 原始状态
	 */
	Original(0), New(1), Modified(2), Delete(3);
	
	/**
	 * 当前的状态编码
	 */
	private int status = 0;
	
	/**
	 * 当前的状态编码
	 * @return
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 当前的行状态
	 * @param status
	 */
	private ItemStatus(int status)
	{
		this.status = status;
	}
}
