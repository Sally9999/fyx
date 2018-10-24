package com.wsy.fyxw.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Component("beanTransUtil")
public class BeanTransUtil<T, M> {

	public void listTrans(ArrayList<T> destList, List<M> origList, Class clazz)
			throws IllegalAccessException, InvocationTargetException, InstantiationException {
		if (CollectionUtils.isNotEmpty(origList)) {
			for (M orig : origList) {
				T dest = getT(clazz);
				BeanUtils.copyProperties(dest, orig);
				destList.add(dest);
			}
		}
	}
	
	public void listTrans2(List<M> destList, ArrayList<T> origList, Class clazz)
			throws IllegalAccessException, InvocationTargetException, InstantiationException {
		if (CollectionUtils.isNotEmpty(origList)) {
			for (T orig : origList) {
				M dest = getM(clazz);
				BeanUtils.copyProperties(dest, orig);
				destList.add(dest);
			}
		}
	}

	public T getT(Class clazz) throws InstantiationException, IllegalAccessException {
		Class<T> tClass = (Class<T>) GenericsUtils.getSuperClassGenricType(clazz, 0);
		return tClass.newInstance();
	}

	public M getM(Class clazz) throws InstantiationException, IllegalAccessException {
		Class<M> mClass = (Class<M>) GenericsUtils.getSuperClassGenricType(clazz, 1);
		return mClass.newInstance();
	}

}
