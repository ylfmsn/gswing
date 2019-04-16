package org.sam.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.jdesktop.swingx.JXStatusBar;
import org.sam.swing.utils.FontUtil;

/**
 * 系统自定义的控件
 * 
 * @author sam
 *
 */
public class JSStatusBar extends JXStatusBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3123560890720613883L;

	/**
	 * 自定义的状态栏
	 */
	public JSStatusBar() {
		super();
		initCompents();
	}

	/**
	 * 工具条
	 */
	private JProgressBar progressBar = new JProgressBar();

	/**
	 * 工具条
	 */
	private final JPanel contentPanel = new JPanel();

	/**
	 * 空格显示
	 */
	private final JLabel labelEmpty = new JLabel("       ");

	/**
	 * 工具条上的提示信息
	 */
	private JLabel lblTip = new JLabel("");

	/**
	 * 标签文本
	 * @param font
	 */
	@Override
	public void setFont(Font font) {
		this.setFont(font);
		labelEmpty.setFont(font);
		labelEmpty.setFont(font);
	}

	/**
	 * 初始化控件显示
	 */
	public void initCompents() {
		this.setLayout(new BorderLayout());

		progressBar.setMaximum(100);
		progressBar.setMinimum(0);

		this.setFont(FontUtil.getDefaultFont());
		labelEmpty.setFont(this.getFont());
		lblTip.setFont(this.getFont());

		this.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		contentPanel.add(lblTip);
		contentPanel.add(progressBar);
		contentPanel.add(labelEmpty);

		progressBar.setVisible(false);
	}

}
