package com.tengfei.payment.dto;

import java.util.List;

/**
 * 菜单
 * @author jianfei.xu
 *
 */
public class Menu {
	/**
	 * 菜单Id
	 */
	private String menuId;
	/**
	 * 父菜单Id
	 */
	private String parentMenuId;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 菜单地址
	 */
	private String menuUrl;
	/**
	 * 是否叶子
	 */
	private Boolean leaf;
	/**
	 * 菜单排序
	 */
	private Integer orderNum;
	/**
	 * 子菜单
	 */
	private List<Menu> childrenMenus;
	
	public String getMenuId() {
		return menuId;
	}
	
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getMenuName() {
		return menuName;
	}
	
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	public String getMenuUrl() {
		return menuUrl;
	}
	
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	
	public Boolean getLeaf() {
		return leaf;
	}
	
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	
	public Integer getOrderNum() {
		return orderNum;
	}
	
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
	public List<Menu> getChildrenMenus() {
		return childrenMenus;
	}
	
	public void setChildrenMenus(List<Menu> childrenMenus) {
		this.childrenMenus = childrenMenus;
	}
	
}
