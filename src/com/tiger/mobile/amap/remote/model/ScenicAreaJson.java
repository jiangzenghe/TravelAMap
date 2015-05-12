package com.tiger.mobile.amap.remote.model;

import java.io.Serializable;

/*
 * 景区描述  用于home page和 景区详情中的信息
 * 
 * */
public class ScenicAreaJson implements Serializable {
	
	private String id;
	private String scenicName;
	private String scenicLocation;
	private String smallImage;
	private Double lat;  //纬度
	private Double lng;
	private String warning;  // 0 绿色 1：黄色 2：橙色 3：红色预警
    /*此值根据景区的容纳人数和已进入人数确定。
	 * */
	private String city;
	private String desc;
	private String scenicType;   //自然风景区
	private Integer  commentsNum;
	private Integer favourNum;
	private String  imageUrl;
	private  String scenicLevel;  //5A 4A 

	public ScenicAreaJson(){
		
	}
	public ScenicAreaJson(String id, String scenicName, String scenicLocation,
			String smallImage, Double lat, Double lng, String warning,
			String city, String desc, String scenicType, Integer commentsNum) {
		this.id = id;
		this.scenicName = scenicName;
		this.scenicLocation = scenicLocation;
		this.smallImage = smallImage;
		this.lat = lat;
		this.lng = lng;
		this.warning = warning;
		this.city = city;
		this.desc = desc;
		this.scenicType = scenicType;
		this.commentsNum = commentsNum;
	}


	@Override
	public String toString() {
		return "ScenicAreaJson [Id=" + id + ", scenicName=" + scenicName
				+ ", scenicLocation=" + scenicLocation + ", smallImage="
				+ smallImage + ", lat=" + lat + ", lng=" + lng + ", warning="
				+ warning + ", city=" + city + ", desc=" + desc
				+ ", scenicType=" + scenicType + ", commentsNum=" + commentsNum
				+ "]";
	}


	public String getScenicType() {
		return scenicType;
	}


	public void setScenicType(String scenicType) {
		this.scenicType = scenicType;
	}


	public Integer getCommentsNum() {
		return commentsNum;
	}


	public void setCommentsNum(Integer commentsNum) {
		this.commentsNum = commentsNum;
	}


	public String getSmallImage() {
		return smallImage;
	}


	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}


	public Double getLat() {
		return lat;
	}


	public void setLat(Double lat) {
		this.lat = lat;
	}


	public Double getLng() {
		return lng;
	}


	public void setLng(Double lng) {
		this.lng = lng;
	}


	public String getWarning() {
		return warning;
	}


	public void setWarning(String warning) {
		this.warning = warning;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getScenicName() {
		return scenicName;
	}


	public void setScenicName(String scenicName) {
		this.scenicName = scenicName;
	}


	public String getScenicLocation() {
		return scenicLocation;
	}


	public void setScenicLocation(String scenicLocation) {
		this.scenicLocation = scenicLocation;
	}


	public Integer getFavourNum() {
		return favourNum;
	}


	public void setFavourNum(Integer favourNum) {
		this.favourNum = favourNum;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public String getScenicLevel() {
		return scenicLevel;
	}


	public void setScenicLevel(String scenicLevel) {
		this.scenicLevel = scenicLevel;
	}
}
