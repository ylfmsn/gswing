package com.suntoon.action;

import com.suntoon.MapPane;
import com.suntoon.tools.PanTool;

import javax.swing.*;

/**
 * 移动操作
 * 
 * @author sam
 *
 */
public class PanAction extends AbstractMapAction {

	private static final long serialVersionUID = -8580473973445330548L;

	/**
	 * 移动操作
	 * 
	 * @param mapPane
	 */
	public PanAction(MapPane mapPane) {
		super(mapPane);
		this.putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource("/pan_mode.gif")));
		this.putValue(NAME, "");
		this.putValue(SHORT_DESCRIPTION, "拖动");
	}

	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		if (this.getMapPane() != null) {
			this.getMapPane().setCursorTool(new PanTool());
		}
	}

}
