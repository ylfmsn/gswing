package org.sam.swing;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认的一些字典
 * @author sam
 *
 */
public class DefaultDictionary {

	/**
	 * 数字类型的字典
	 */
	private static Map<Integer,Boolean> integerMap = new HashMap<>();
	
	/**
	 * 文本类型的字典
	 */
	private static Map<String,Boolean> stringMap = new HashMap<>();
	
	/**
	 * 字符类型的字典
	 */
	private static Map<Character,Boolean> charMap = new HashMap<>();

	/**
	 * 数字类型的字典
	 * @return
	 */
	public static Map<Integer, Boolean> getIntegerMap() {
		return integerMap;
	}

	/**
	 * 文本类型的字典
	 * @return
	 */
	public static Map<String, Boolean> getStringMap() {
		return stringMap;
	}

	/**
	 * 字符类型的字典
	 * @return
	 */
	public static Map<Character, Boolean> getCharMap() {
		return charMap;
	}
	
	static //静态构造函数
	{
		integerMap.put(null, false);
		integerMap.put(1, true);
		integerMap.put(0, false);
		
		stringMap.put("Y", true);
		stringMap.put("y", true);
		stringMap.put("T", true);
		stringMap.put("t", true);
		stringMap.put("true", true);
		stringMap.put("True", true);
		stringMap.put("TRUE", true);
		stringMap.put("N", false);
		stringMap.put("n", false);
		stringMap.put("F", false);
		stringMap.put("f", false);
		stringMap.put("false", false);
		stringMap.put("False", false);
		stringMap.put("FALSE", false);
		stringMap.put("1", true);
		stringMap.put("0", false);
		stringMap.put(null, false);
		
		charMap.put(null, false);
		charMap.put('Y', true);
		charMap.put('y', true);
		charMap.put('1', true);
		charMap.put('T', true);
		charMap.put('t', true);
		charMap.put('N', false);
		charMap.put('n', false);
		charMap.put('0', false);
		charMap.put('F', false);
		charMap.put('f', false);
	}
}
