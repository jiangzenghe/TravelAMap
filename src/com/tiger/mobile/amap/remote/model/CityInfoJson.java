package com.tiger.mobile.amap.remote.model;

public class CityInfoJson implements java.io.Serializable{
 
		public String getCityid() {
		return cityid;
	}
	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
		@Override
	public String toString() {
		return "CityInfoJson [cityid=" + cityid + ", cityname=" + cityname
				+ ",pinyin:"+pinyin+"]";
	}
		public String getPinyin() {
			return pinyin;
		}
		public void setPinyin(String pinyin) {
			this.pinyin = pinyin;
		}
		private String cityid;    
		private String cityname;
		private String pinyin;
	}


