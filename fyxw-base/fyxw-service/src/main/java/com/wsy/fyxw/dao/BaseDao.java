package com.wsy.fyxw.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface BaseDao<T, Q> {

	/**
	 * 获取分页列表
	 * 
	 * @param q
	 * @return
	 */
	public ArrayList<T> getPage(Q q);

	/**
	 * 获取所有满足条件的列表
	 * 
	 * @param q
	 * @return
	 */
	public ArrayList<T> getList(Q q);

	/**
	 * 统计条数
	 * 
	 * @param q
	 * @return
	 */
	public int getCount(Q q);

	/**
	 * 根据code获取对象
	 * 
	 * @param code
	 * @return
	 */
	public T getByCode(String code);

	/**
	 * 更新
	 * 
	 * @param t
	 * @return
	 */
	public int update(T t);

	/**
	 * 创建
	 * 
	 * @param t
	 * @return
	 */
	public int add(T t);

	/**
	 * 根据code做删除
	 */
	public int deleteByCode(@Param("code") String code);

	/**
	 * 根据code变更状态
	 * 
	 * @param t
	 * @return
	 */
	public int changeStatusByCode(T t);
}
