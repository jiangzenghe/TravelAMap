package com.tiger.mobile.amap.entity;


import android.annotation.SuppressLint;

/**
 * 索引列表显示项目实体类
 * @author 
 * 
 * <p> Modification History:</p> 
 * <p> Date         Author				Description </p>  
 * <p>------------------------------------------------------------------</p>  
 * <p>  		                新建</p>
 */
@SuppressLint("DefaultLocale")
public class IndexListItem {
//	@AdapterBinder(resName="tai_name")
	private String name;//全名
	private String pName;//拼音全称
//	@AdapterBinder(resName="tai_first_alpha")
	private String shortPName;//拼音缩写
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPName() {
		return pName;
	}
	public void setPName(String pName) {
		this.pName = pName;
	}
	public String getShortPName() {
		return shortPName;
	}
	public void setShortPName(String shortPName) {
		this.shortPName = shortPName.toUpperCase();
	}
}
