package com.suntoon;

import javax.swing.*;

/**
 * 程序的入口点
 * @author sam
 *
 */
public class Programme {

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		JMapFrame frame = new JMapFrame();
		frame.setVisible(true);
	}

}
