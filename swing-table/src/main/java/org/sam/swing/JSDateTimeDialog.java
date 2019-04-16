package org.sam.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * 弹出窗口形式的日期时间对话框
 * @author sam
 * @author 李超
 */
public class JSDateTimeDialog extends JSDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4814693171348063462L;

	/**
	 * 日期时间区域
	 */
	private JSDateTimePanel dtPanel;

	/**
	 * 确定按钮
	 */
	private JButton okButton;

	/**
	 * 当前的时间
	 */
	private Date currentDate;

	/**
	 * 当前的时间
	 * 
	 * @return
	 */
	public Date getCurrentDate() {
		return currentDate;
	}

	/**
	 * 当前的时间
	 * 
	 * @param currentDate
	 */
	private void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	/**
	 * 带有初始化日期的对话框
	 * 
	 * @param date
	 */
	public JSDateTimeDialog(Date date) {
		super();
		this.setModal(true);
		this.setTitle("请选择日期");
		this.setSize(430, 330);
		this.setResizable(false);
		this.setCurrentDate(date);
		this.setLayout(new BorderLayout());
		dtPanel = new JSDateTimePanel(date);
		this.add(dtPanel, BorderLayout.CENTER);

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.SOUTH);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			{
				okButton = new JButton("确定");
				okButton.setActionCommand(OK);
				buttonPane.add(okButton);
				okButton.addActionListener(this);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取消");
				cancelButton.setActionCommand(CANCEL);
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}

		this.setLocationRelativeTo(null); // 屏幕居中

	}

	/**
	 * 使用当前时间的日期对话框
	 */
	public JSDateTimeDialog() {
		this(Calendar.getInstance().getTime());
	}

	/**
	 * 按钮点击事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (OK.equals(e.getActionCommand())) {
			this.setAction(OK);
			try {
				this.setCurrentDate(dtPanel.getCurrentDate());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			this.setVisible(false);
			this.dispose();
		} else if (CANCEL.equals(e.getActionCommand())) {
			this.setAction(CANCEL);
			this.setVisible(false);
			this.dispose();
		}
	}

}
