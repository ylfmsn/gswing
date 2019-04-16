package com.suntoon.event;

import java.awt.*;
import java.util.EventListener;

/**
 * 绘制地图的linster
 * 
 * @author sam
 *
 */
public interface MapPaintListener extends EventListener {

	/**
	 * 绘制之前执行的操作 直接绘制到图片上的方法
	 * 
	 * @param g2d
	 */
	public default void beforePaint(Graphics2D g2d) {
	}

	/**
	 * 地图绘制完成后执行的操作 直接绘制到画布上的方法
	 * @param g2d 2维画布上下文
	 * @param dx
	 * @param dy
	 */
	public void afterPaint(Graphics2D g2d, int dx, int dy);
}
