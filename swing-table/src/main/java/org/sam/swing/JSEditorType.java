package org.sam.swing;

/**
 * 当前列的编辑类型
 * @author sam
 *
 */
public enum JSEditorType {

	/**
	 * 最普通的editor
	 */
	Editor,
	/**
	 * 密码风格
	 */
	PassWordEditor,
	/**
	 * 整数编辑器
	 */
	IntEditor,
	/**
	 * 带小数编辑器
	 */
	NumberEditor,
	/**
	 * 直接输入的掩码格式日期
	 */
	DateEditor,
	/**
	 * 直接输入的日期时间类型掩码
	 */
	DateTimeEditor,
	/**
	 * 直接输入的时间掩码格式
	 */
	TimeEditor,
	/**
	 * 选择方式的掩码输入格式
	 */
	DatePicker,
	/**
	 * 选择方式的日期时间类型
	 */
	DateTimePicker,
	/**
	 * 选择方式的时间格式
	 */
	TimePicker,
	/**
	 * 选择框
	 */
	CheckBox,
	/**
	 * 下拉列表风格
	 */
	DropDownListBox,
	/**
	 * 嵌套的下拉列表，可能是多个显示数据
	 */
	DropDownChild,
	/**
	 * 单选按钮风格
	 */
	RadioButtons
}
