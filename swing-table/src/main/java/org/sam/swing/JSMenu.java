package org.sam.swing;

import javax.swing.Action;
import javax.swing.JMenu;

/**
 * 创建一个自动生成的menu菜单对象
 * 采用xml,或者数据库配置的方式
 * @author sam
 *
 */
public class JSMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6168496697551159825L;
	
	/**
     * Constructs a new <code>JMenu</code> with no text.
     */
    public JSMenu() {
        this("");
    }

    /**
     * Constructs a new <code>JMenu</code> with the supplied string
     * as its text.
     *
     * @param s  the text for the menu label
     */
    public JSMenu(String s) {
        super(s);
    }

    /**
     * Constructs a menu whose properties are taken from the
     * <code>Action</code> supplied.
     * @param a an <code>Action</code>
     *
     * @since 1.3
     */
    public JSMenu(Action a) {
        this();
        setAction(a);
    }

    /**
     * Constructs a new <code>JMenu</code> with the supplied string as
     * its text and specified as a tear-off menu or not.
     *
     * @param s the text for the menu label
     * @param b can the menu be torn off (not yet implemented)
     */
    public JSMenu(String s, boolean b) {
        this(s);
    }

}
