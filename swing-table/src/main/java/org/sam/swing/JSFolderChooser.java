package org.sam.swing;

import java.util.LinkedList;
import java.util.List;

/**
 * 扩展的文件夹选择框
 * @author sam
 *
 */
public class JSFolderChooser {

	/**
	 * 文件后缀
	 */
	private String suffix = "";
	
	/**
	 * 打开后的文件列表
	 */
	private List<String> files = new LinkedList<>();

	/**
	 * 过滤的文件后缀
	 * @return
	 */
	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public List<String> getFiles() {
		return files;
	}
}
