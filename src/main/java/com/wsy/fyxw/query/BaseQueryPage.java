package com.wsy.fyxw.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.wsy.fyxw.util.NumberUtil;

public class BaseQueryPage<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1409129456280297147L;
	private Integer pageNo = 1;
	private Integer pageSize = 10;
	private Integer totalRecord;
	private Integer totalPage;
	private Integer pageFirstItem;
	private String orderBy;
	private String orderAsc;
	private ArrayList<T> resultItems;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
		if (null != this.totalPage && pageNo.intValue() > this.totalPage.intValue()) {
			this.pageNo = this.totalPage;
		}
		if (pageNo.intValue() <= 0) {
			this.pageNo = 1;
		}
		this.setPageFirstItem();
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
		this.setTotalPage();
	}

	public void setTotalPage() {
		this.totalPage = NumberUtil.divide(this.totalRecord, this.pageSize, BigDecimal.ROUND_CEILING);
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public Integer getPageFirstItem() {
		this.setPageFirstItem();
		return pageFirstItem;
	}

	public void setPageFirstItem() {
		this.pageFirstItem = NumberUtil.multiple(NumberUtil.sub(this.pageNo, 1), this.pageSize);
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderAsc() {
		return orderAsc;
	}

	public void setOrderAsc(String orderAsc) {
		this.orderAsc = orderAsc;
	}

	public ArrayList<T> getResultItems() {
		return resultItems;
	}

	public void setResultItems(ArrayList<T> resultItems) {
		this.resultItems = resultItems;
	}

}
