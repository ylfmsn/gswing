package org.sam.swing;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 带
 * 
 * @author sam
 *
 */
public class JSCheckMutableTreeNode extends DefaultMutableTreeNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1286161369927363679L;
	
	//begin 构造函数

	/**
	 * Creates a tree node that has no parent and no children, but which allows
	 * children.
	 */
	public JSCheckMutableTreeNode() {
		this(null);
	}

	/**
	 * Creates a tree node with no parent, no children, but which allows
	 * children, and initializes it with the specified user object.
	 *
	 * @param userObject
	 *            an Object provided by the user that constitutes the node's
	 *            data
	 */
	public JSCheckMutableTreeNode(Object userObject) {
		this(userObject, true);
		this.setOrignal(userObject);
	}

	/**
	 * Creates a tree node with no parent, no children, initialized with the
	 * specified user object, and that allows children only if specified.
	 *
	 * @param userObject
	 *            an Object provided by the user that constitutes the node's
	 *            data
	 * @param allowsChildren
	 *            if true, the node is allowed to have child nodes -- otherwise,
	 *            it is always a leaf node
	 */
	public JSCheckMutableTreeNode(Object userObject, boolean allowsChildren) {
		super(userObject, allowsChildren);
		this.setOrignal(userObject);
	}
	
	//end 
	
	//begin property
	
	/**
	 * 显示字段
	 */
	private String textField = "";
	
	/**
	 * 选中字段
	 */
	private String checkField = "";
	
	/**
	 * 原始数据
	 */
	private Object orignal;
	
	/**
	 * 当前的行状态
	 */
	private ItemStatus itemStatus = ItemStatus.Original;
	
	/**
	 * 是否启用checkbox
	 */
	private boolean useCheckBox = true;
	
	/**
	 * 是否启用checkbox
	 * @return
	 */
	public boolean isUseCheckBox() {
		return useCheckBox;
	}

	/**
	 * 是否启用checkbox
	 * @param useCheckBox
	 */
	public void setUseCheckBox(boolean useCheckBox) {
		this.useCheckBox = useCheckBox;
	}

	/**
	 * 当前的行状态
	 * @return
	 */
	public ItemStatus getItemStatus() {
		return itemStatus;
	}

	/**
	 * 当前的行状态
	 * @param itemStatus
	 */
	public void setItemStatus(ItemStatus itemStatus) {
		this.itemStatus = itemStatus;
	}

	/**
	 * 显示字段
	 * @return
	 */
	public String getTextField() {
		return textField;
	}

	/**
	 * 显示字段
	 * @param textField
	 */
	public void setTextField(String textField) {
		this.textField = textField;
	}

	/**
	 * 选中字段
	 * @return
	 */
	public String getCheckField() {
		return checkField;
	}

	/**
	 * 选中字段
	 * @param checkField
	 */
	public void setCheckField(String checkField) {
		this.checkField = checkField;
	}

	/**
	 * 原始数据
	 * @return
	 */
	public Object getOrignal() {
		return orignal;
	}

	/**
	 * 原始数据
	 * @param orignal
	 */
	public void setOrignal(Object orignal) {
		this.orignal = orignal;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setUserObject(Object userObject) 
	{
		if (null == this.getUserObject())
		{
			super.setUserObject(userObject);
			this.setOrignal(userObject);
		}
	}
	
	//end
	
}
