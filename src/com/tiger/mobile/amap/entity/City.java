package com.tiger.mobile.amap.entity;

import java.util.Comparator;


/**
 * City信息
 * 
 * @author 
 * 
* <p>Date       Author      Description</p>
 *<p>------------------------------------------------------------------</p>
 *<p>         创建  </p>
 *
 */
public class City {

	public City(String name,String py) {
		this.cityName = name;
		this.cityPY =py;
	}
	
	private String cityCode;
	private String cityName;
	private String cityPY;
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityPY() {
		return cityPY;
	}
	public void setCityPY(String cityPY) {
		this.cityPY = cityPY;
	}
	
//	public int compare(City s1, City s2) {
//		return s1.getShortPName().compareToIgnoreCase(s2.getPName());
//	}
}
