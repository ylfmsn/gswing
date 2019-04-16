package org.sam.swing.utils;

import java.awt.Font;

/**
 * 系统默认的字体
 * @author sam
 *
 */
public class FontUtil {
	
	/**
	 * 系统默认字体
	 */
	public static final Font DEFAULT_FONT = new Font("宋体", Font.PLAIN, 11);

	/**
	 * 获取系统默认的字体
	 * @return
	 */
	public static Font getDefaultFont()
	{
		return DEFAULT_FONT;
	}
}
