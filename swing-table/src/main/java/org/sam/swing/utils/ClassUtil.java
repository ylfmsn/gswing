package org.sam.swing.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 类类型操作对象
 * 
 * @author sam
 *
 * @param <E>
 */
public abstract class ClassUtil<E> {

	/**
	 * 获取一个泛型的实例
	 * 
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public E getE() throws InstantiationException, IllegalAccessException {
		Class<E> mTClass = getCls();
		return mTClass.newInstance();
	}

	/**
	 * 获取当前的泛型的类型
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Class<E> getCls() {
		
		Class<E> entityClass = null;
        Type t = getClass().getGenericSuperclass();
        if(t instanceof ParameterizedType){
            Type[] p = ((ParameterizedType)t).getActualTypeArguments();
            entityClass = (Class<E>)p[0].getClass();
        }
        
        return entityClass;
		
	}
}
