package com.suntoon.action;

import com.suntoon.MapPane;
import com.suntoon.tools.DistanceTool;

import javax.swing.*;

/**
 * 缩小操作
 * 
 * @author sam
 *
 */
public class DistanceAction extends AbstractMapAction {

	private static final long serialVersionUID = 2667458318140535983L;

	/**
	 * 缩小操作
	 * 
	 * @param mapPane
	 */
	public DistanceAction(MapPane mapPane) {
		super(mapPane);
		this.putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource("/distance.png")));
		this.putValue(NAME, "");
		this.putValue(SHORT_DESCRIPTION, "测距工具");
	}

	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		if (this.getMapPane() != null) {
			this.getMapPane().setCursorTool(new DistanceTool());
		}
	}
}
