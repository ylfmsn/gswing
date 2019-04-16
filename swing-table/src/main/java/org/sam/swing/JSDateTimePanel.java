package org.sam.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EventListener;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.EventListenerList;

/**
 * 创建一个日期时间panel
 * @author 李超
 *
 */
public class JSDateTimePanel extends JPanel {

	private static final long serialVersionUID = 7277909838401968568L;
	//begin
	protected Color weekBackgroundColor = new Color(189, 235, 238);
	protected Color weekendBtnFontColor = new Color(240, 64, 64); // color
	protected Color selectedColor = weekBackgroundColor;
	protected Font labelFont = new Font("Arial", Font.PLAIN, 10);
	protected Color defaultBtnFontColor = Color.BLACK;
	protected Color defaultBtnBackgroundColor = Color.WHITE;
	private Calendar cal = null;
	private Calendar todayCal = null;
	private int year;
	private int month;	
	private int day;
	private int hour;
	private int minute;
	private int seconds;
	private JPanel controllPanel = null;
	private JPanel dateContainerPanel = null;
	protected JSDateButton[][] buttonDays = null;
	private JComboBox<Integer> yearChoice; 
	private JComboBox<Integer> monthChoice;
	private JComboBox<Integer> dayChoice;
	private JComboBox<String> hourChoice;
	private JComboBox<String> minuteChoice;
	private JComboBox<String> secondsChoice;
	protected String[] weekTitle = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
	protected int[] months = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
	private EventListenerList actionListenerList = new EventListenerList();
	//end
	
	public JSDateTimePanel(Date date) {
		buttonDays = new JSDateButton[6][7];
		cal = Calendar.getInstance();
		cal.setTime(date);
		todayCal = Calendar.getInstance();
		todayCal.setTime(date);
		this.year = cal.get(Calendar.YEAR);
		this.month = cal.get(Calendar.MONTH);
		this.day = cal.get(Calendar.DATE);
		this.hour = cal.get(Calendar.HOUR_OF_DAY);
		this.minute = cal.get(Calendar.MINUTE);
		this.seconds = cal.get(Calendar.SECOND);
		JPanel oprPanel = createControlPanel();
		this.setLayout(new BorderLayout(0, 0));
		dateContainerPanel = new JPanel();
		createDayPanel(cal);
		setActiveDay(day);
		this.add(oprPanel, BorderLayout.NORTH);
		this.add(dateContainerPanel, BorderLayout.CENTER);

	}
	
	/**
	 * 创建年月日时分秒下拉combobox
	 * @return
	 */
	@SuppressWarnings("all")
	public JPanel createControlPanel() {
		controllPanel = new JPanel();
		controllPanel.setBackground(Color.WHITE);
		Box hBox = Box.createHorizontalBox();
		String strToday = formatDate(todayCal);
		//todayLabel = new JLabel(strToday);
		
		JLabel yearLabel = new JLabel("年");
		int currentYear = todayCal.get(Calendar.YEAR);
		final int gapYears = 200;
		yearChoice = new JComboBox();
		for (int i = currentYear - gapYears; i < currentYear + gapYears; i++) {
			yearChoice.addItem(i);
		}
		yearChoice.setSelectedIndex(gapYears);
		yearChoice.setPreferredSize(new Dimension(yearChoice.getPreferredSize().width,
				yearChoice.getPreferredSize().height));
		yearChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (yearChoice.getSelectedIndex() != gapYears) {
					Integer selYear = (Integer) yearChoice.getSelectedItem();
					Calendar cal = new GregorianCalendar(year, month, 1);
					cal.set(Calendar.YEAR, selYear);
					year = cal.get(Calendar.YEAR);
					month = cal.get(Calendar.MONTH);
					dayChoiceAddItem(year, month + 1);
					createDayPanel(cal);
				}
			}
		});
		
		JLabel monthLabel = new JLabel("月");
		monthChoice = new JComboBox();
		for (int i = 0; i < months.length; i++) {
			monthChoice.addItem(months[i]);
		}
		monthChoice.setSelectedItem(months[month]);
		monthChoice.setPreferredSize(new Dimension(monthChoice.getPreferredSize().width,
				monthChoice.getPreferredSize().height));
		monthChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int i = monthChoice.getSelectedIndex();
				if (i >= 0) {
					month = i;
					Calendar cal = new GregorianCalendar(year, month, 1);
					year = cal.get(Calendar.YEAR);
					month = cal.get(Calendar.MONTH);
					dayChoiceAddItem(year, month + 1);
					createDayPanel(cal);
				}
			}
		});
		
		JLabel dayLabel = new JLabel("日");
		dayChoice = new JComboBox<>();
		dayChoiceAddItem(year, month + 1);
		dayChoice.setSelectedItem(day);//生成day下拉选择器之后 默认选择当前天
		dayChoice.setPreferredSize(new Dimension(dayChoice.getPreferredSize().width,
				dayChoice.getPreferredSize().height));
		dayChoice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar cal = new GregorianCalendar(year, month, 1);
				year = cal.get(Calendar.YEAR);
				month = cal.get(Calendar.MONTH);
				createDayPanel(cal);
				if(dayChoice.getSelectedItem() != null){
					int day = (int) dayChoice.getSelectedItem();
					setActiveDay(day);
				}
			}
		});
		
		JLabel hourLabel = new JLabel("时");
		hourChoice = new JComboBox();
		setDateByParam(hourChoice, 24);
		hourChoice.setSelectedItem(hour + "");
		hourChoice.setPreferredSize(new Dimension(hourChoice.getPreferredSize().width,
				hourChoice.getPreferredSize().height));
		hourChoice.addActionListener(actionListener(hourChoice));
		
		JLabel minuteLabel = new JLabel("分");
		minuteChoice = new JComboBox();
		setDateByParam(minuteChoice, 60);
		minuteChoice.setSelectedItem(minute + "");
		minuteChoice.setPreferredSize(new Dimension(minuteChoice.getPreferredSize().width,
				minuteChoice.getPreferredSize().height));
		minuteChoice.addActionListener(actionListener(minuteChoice));
		
		JLabel secondsLabel = new JLabel("秒");
		secondsChoice = new JComboBox();
		setDateByParam(secondsChoice, 60);
		secondsChoice.setSelectedItem(seconds + "");
		secondsChoice.setPreferredSize(new Dimension(secondsChoice.getPreferredSize().width,
				secondsChoice.getPreferredSize().height));
		secondsChoice.addActionListener(actionListener(secondsChoice));
		
		hBox.add(yearChoice);
		hBox.add(Box.createHorizontalStrut(8));
		hBox.add(yearLabel);
		hBox.add(Box.createHorizontalStrut(5));
		hBox.add(monthChoice);
		hBox.add(Box.createHorizontalStrut(8));
		hBox.add(monthLabel);
		hBox.add(Box.createHorizontalStrut(5));
		hBox.add(dayChoice);
		hBox.add(Box.createHorizontalStrut(8));
		hBox.add(dayLabel);
		hBox.add(Box.createHorizontalStrut(5));
		hBox.add(hourChoice);
		hBox.add(Box.createHorizontalStrut(8));
		hBox.add(hourLabel);
		hBox.add(Box.createHorizontalStrut(5));
		hBox.add(minuteChoice);
		hBox.add(Box.createHorizontalStrut(8));
		hBox.add(minuteLabel);
		hBox.add(Box.createHorizontalStrut(5));
		hBox.add(secondsChoice);
		hBox.add(Box.createHorizontalStrut(8));
		hBox.add(secondsLabel);
		hBox.add(Box.createHorizontalStrut(5));
		
		controllPanel.add(hBox, BorderLayout.NORTH);
		return controllPanel;

	}

	/**
	 * 创建日期组件
	 * @param cal
	 */
	public void createDayPanel(Calendar cal) {
		dateContainerPanel.removeAll();
		dateContainerPanel.revalidate();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);

		GridLayout grid = new GridLayout(7, 7, 0, 0);
		dateContainerPanel.setLayout(grid);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int weekday = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DAY_OF_MONTH, 1 - weekday);

		for (int i = 0; i < 7; i++) {
			JLabel weekLabel = new JLabel(weekTitle[i], SwingConstants.CENTER);
			weekLabel.setFont(labelFont);
			weekLabel.setOpaque(true);
			weekLabel.setBackground(weekBackgroundColor);
			dateContainerPanel.add(weekLabel);
		}
		DayButtonActionListener dayButtonActionListener = new DayButtonActionListener();

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				int curMonth = cal.get(Calendar.MONTH);
				JSDateButton button = null;
				if (curMonth != month) {
					button = new JSDateButton(" ");
					button.setEnabled(false);
					button.setBackground(defaultBtnBackgroundColor);
				} else {
					int currentDay = cal.get(Calendar.DAY_OF_MONTH);
					button = new JSDateButton(currentDay + "");
					button.setHorizontalTextPosition(SwingConstants.RIGHT);
					button.setFont(labelFont);
					button.setBackground(defaultBtnBackgroundColor);
					button.setSelectedBackground(weekBackgroundColor);
					if (currentDay == todayCal.get(Calendar.DAY_OF_MONTH) && month == todayCal.get(Calendar.MONTH)
							&& year == todayCal.get(Calendar.YEAR)) {
						button.setBorder(BorderFactory.createLineBorder(weekendBtnFontColor));
					}
					if (cal.get(Calendar.MONTH) != month) {
						button.setForeground(Color.BLUE);
					}
					if (j == 0 || j == 6) {
						button.setForeground(weekendBtnFontColor);
					} else {
						button.setForeground(defaultBtnFontColor);
					}
				}
				button.addActionListener(dayButtonActionListener);
				buttonDays[i][j] = button;
				dateContainerPanel.add(buttonDays[i][j]);
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
		}

	}

	/**
	 * 选中某一天
	 * @param selectedDay
	 */
	public void setActiveDay(int selectedDay) {
		clearAllActiveDay();
		if (selectedDay <= 0) {
			day = new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
		} else {
			day = selectedDay;
		}
		int leadGap = new GregorianCalendar(year, month, 1).get(Calendar.DAY_OF_WEEK) - 1;
		JSDateButton selectedButton = buttonDays[(leadGap + selectedDay - 1) / 7][(leadGap + selectedDay - 1) % 7];
		selectedButton.setBackground(weekBackgroundColor);
	}

	/**
	 * 清除所有选择的日期
	 */
	public void clearAllActiveDay() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				JButton button = buttonDays[i][j];
				if (button.getText() != null && button.getText().trim().length() > 0) {
					button.setBackground(defaultBtnBackgroundColor);
					button.revalidate();
				}
			}
		}
	}

	/**
	 * 获取选中的日期
	 * @return String
	 */
	private String getSelectedDate() {
		Calendar cal = new GregorianCalendar(year, month, day);
		return formatDate(cal);
	}
	
	private String formatDate(Calendar cal) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(cal.getTime());
	}
	
	/**
	 * 采用触发事件的方式,这样的话,就可以将DateChooser添加到任何组件之上,
	 * 任何组件都可以捕获日期选择的事件,然后进行相应处理
	 */
	class DayButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if (button.getText() != null && button.getText().trim().length() > 0) {
				day = Integer.parseInt(button.getText());
				setActiveDay(day);
				dayChoice.setSelectedItem(day);//反选日期到dayChoice
				fireActionPerformed(e);
			}
		}
	}

	public void addActionListener(ActionListener actionListener) {
		actionListenerList.add(ActionListener.class, actionListener);
	}

	public void removeActionListener(ActionListener actionListener) {
		actionListenerList.remove(ActionListener.class, actionListener);
	}
	/**
	 * comboBox下拉选择器事件
	 * @param comboBox
	 * @return
	 */
	private ActionListener actionListener (JComboBox<String> comboBox){
		return	new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO 现在还用不到
			}
		};
	}
	
	/**
	 * 事件管理,触发事件
	 * @param actionEvent
	 */
	protected void fireActionPerformed(ActionEvent actionEvent) {
		EventListener listenerList[] = actionListenerList.getListeners(ActionListener.class);
		for (int i = 0, n = listenerList.length; i < n; i++) {
			((ActionListener) listenerList[i]).actionPerformed(actionEvent);
		}
	}
	/**
	 * 根据年月计算 月的天数，生成day Combobox下拉选择器
	 * @param year
	 * @param month
	 */
	private void dayChoiceAddItem(int year, int month){
		String strDate = year + "-" + month; 
		Calendar calendar = new GregorianCalendar(); 
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM").parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		calendar.setTime(date1); //放入你的日期 		
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		dayChoice.removeAllItems();
		for(int i = 1; i <= days; i++){
			dayChoice.addItem(i);
		}
	}
	/**
	 * 根据传过来的conbobox控件和值
	 * 生成combobox下拉选择器中的具体item
	 * @param comboBox 
	 * @param param
	 */
	private void setDateByParam(JComboBox<String> comboBox, int param){
		for(int i = 0; i < param; i++){
			if(i < 10){
				comboBox.addItem("0"+i);
			}else{
				comboBox.addItem(i + "");
			}
		}
	}
	/**
	 * 获取选择的时间 暴露给外层调用
	 * @return
	 * @throws ParseException
	 */
	public Date getCurrentDate() throws ParseException {
		String dateStr= getSelectedDate() + " " +hourChoice.getSelectedItem() + ":" +minuteChoice.getSelectedItem() + ":" +secondsChoice.getSelectedItem();
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
	}
}
