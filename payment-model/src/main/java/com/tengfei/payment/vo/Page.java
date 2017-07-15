package com.tengfei.payment.vo;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

/**
 * 分页实体类
 * @author jianfei.xu
 * @date   2016年8月24日
 *
 */
public class Page {
	/**
	 * 是否分页
	 */
	private boolean pageState = false;
	/**
	 * 界面的查询参数
	 */
	private Map<String, Object> mapBean;

	/** 正序. */
	public static final String ASC = "ASC";

	/** 倒序. */
	public static final String DESC = "DESC";

	/** 当前第几页，默认值为1，既第一页. */
	protected int pageNo = 1;

	/** 每页最大记录数，默认值为0，表示pageSize不可用. */
	protected int pageSize = 20;

	/** 排序字段名. */
	protected String orderBy = null;

	/** 使用正序还是倒序. */
	protected String order = ASC;

	/** 查询结果. */
	protected Object res = null;

	/** 总记录数，默认值为-1，表示totalCount不可用. */
	protected long count = -1;

	/** 当前页第一条记录的索引，默认值为0，既第一页第一条记录. */
	protected int start = 0;

	/** 总页数，默认值为-1，表示pageCount不可用. */
	protected int pageCount = -1;

	// ==========================================
	// constructor...
	/** 构造方法. */
	public Page() {
		count = 0;
		res = new ArrayList<Object>();
	}

	/**
	 * 构造方法.
	 *
	 * @param result
	 *            Object
	 * @param totalCount
	 *            int
	 */
	public Page(int totalCount, Object result) {
		this.res = result;
		this.count = totalCount;
	}

	/**
	 * 构造方法.
	 *
	 * @param pageNo
	 *            int
	 * @param pageSize
	 *            int
	 * @param orderBy
	 *            String
	 * @param order
	 *            String
	 */
	public Page(int pageNo, int pageSize, String orderBy, String order) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.orderBy = orderBy;
		this.setOrder(order);
		this.calculateStart();
	}

	// ==========================================
	// 工具方法
	public boolean isPageState() {
		return pageState;
	}

	public void setPageState(boolean pageState) {
		this.pageState = pageState;
	}

	public Map<String, Object> getMapBean() {
		return mapBean;
	}

	public void setMapBean(Map<String, Object> mapBean) {
		this.mapBean = mapBean;
	}

	/**
	 * 是否为正序排序.
	 *
	 * @return boolean
	 */
	public boolean isAsc() {
		return !DESC.equalsIgnoreCase(order);
	}

	/**
	 * 取得倒转的排序方向.
	 * 
	 * @return 如果dir=='ASC'就返回'DESC'，如果dir='DESC'就返回'ASC'
	 */
	public String getInverseOrder() {
		if (DESC.equalsIgnoreCase(order)) {
			return ASC;
		} else {
			return DESC;
		}
	}

	/**
	 * 页面显示最大记录数是否可用.
	 *
	 * @return pageSize > 0
	 */
	public boolean isPageSizeEnabled() {
		return pageSize > 0;
	}

	/**
	 * 页面第一条记录的索引是否可用.
	 *
	 * @return start
	 */
	public boolean isStartEnabled() {
		return start >= 0;
	}

	/**
	 * 排序是否可用.
	 *
	 * @return orderBy是否非空
	 */
	public boolean isOrderEnabled() {
		return (orderBy != null) && (orderBy.trim().length() != 0);
	}

	/**
	 * 是否有前一页.
	 *
	 * @return boolean
	 */
	public boolean isPreviousEnabled() {
		return pageNo > 1;
	}

	/**
	 * 是否有后一页.
	 *
	 * @return boolean
	 */
	public boolean isNextEnabled() {
		return pageNo < pageCount;
	}

	/**
	 * 总页数是否可用.
	 *
	 * @return boolean
	 */
	public boolean isPageCountEnabled() {
		return pageCount >= 0;
	}

	/** 计算本页第一条记录的索引. */
	public void calculateStart() {
		if ((pageNo < 1) || (pageSize < 1)) {
			start = -1;
		} else {
			start = (pageNo - 1) * pageSize;
		}
	}

	/** 计算最大页数. */
	public void calculatePageCount() {
		if ((count < 0) || (pageSize < 1)) {
			pageCount = -1;
		} else {
			pageCount = (int) (((count - 1) / pageSize) + 1);
		}
	}

	// ==========================================
	// getter and setter method...
	/** @return pageNo. */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo
	 *            int.
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		this.calculateStart();
	}

	/** @return pageSize. */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            int.
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.calculateStart();
		this.calculatePageCount();
	}

	/** @return orderBy. */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy
	 *            String.
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/** @return order. */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            String.
	 */
	public void setOrder(String order) {
		if (ASC.equalsIgnoreCase(order) || DESC.equalsIgnoreCase(order)) {
			this.order = order.toUpperCase(Locale.CHINA);
		} else {
			throw new IllegalArgumentException(
					"order should be 'DESC' or 'ASC'");
		}
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	/** @return start. */
	public int getStart() {
		return start;
	}

	/** @return pageCount. */
	public int getPageCount() {
		return pageCount;
	}

	public Object getRes() {
		return res;
	}

	public void setRes(Object res) {
		this.res = res;
	}

}
