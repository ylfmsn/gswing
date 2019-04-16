package org.sam.swing;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 自定义的树节点对象
 * 新增加如果属性
 * @author sam
 *
 */
public class JDefaultMutableTreeNode extends DefaultMutableTreeNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1293222640868109874L;
	
	/**
     * Creates a tree node that has no parent and no children, but which
     * allows children.
     */
    public JDefaultMutableTreeNode() {
        this(null);
    }

    /**
     * Creates a tree node with no parent, no children, but which allows
     * children, and initializes it with the specified user object.
     *
     * @param userObject an Object provided by the user that constitutes
     *                   the node's data
     */
    public JDefaultMutableTreeNode(Object userObject) {
        this(userObject, true);
    }

    /**
     * Creates a tree node with no parent, no children, initialized with
     * the specified user object, and that allows children only if
     * specified.
     *
     * @param userObject an Object provided by the user that constitutes
     *        the node's data
     * @param allowsChildren if true, the node is allowed to have child
     *        nodes -- otherwise, it is always a leaf node
     */
    public JDefaultMutableTreeNode(Object userObject, boolean allowsChildren) {
        super(userObject,allowsChildren);
    }
    
    /**
     * 扩展数据
     */
    protected String tag = "";
    
    /**
     * 执行动作
     */
    protected String action = "";
    
    /**
     * 动作参数
     */
    protected Object arg;
    
    /**
     * 高级属性
     */
    private Object advanceArg;

    /**
     * 扩展数据
     * @return
     */
	public String getTag() {
		return tag;
	}

	/**
	 * 扩展数据
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * 动作
	 * @return
	 */
	public String getAction() {
		return action;
	}

	/**
	 * 动作
	 * @param action
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * 动作参数
	 * @return
	 */
	public Object getArg() {
		return arg;
	}

	/**
	 * 动作参数
	 * @param arg
	 */
	public void setArg(Object arg) {
		this.arg = arg;
	}

	/**
	 * 高级属性
	 * @return
	 */
	public Object getAdvanceArg() {
		return advanceArg;
	}

	/**
	 * 高级属性
	 * @param advanceArg
	 */
	public void setAdvanceArg(Object advanceArg) {
		this.advanceArg = advanceArg;
	}
}
