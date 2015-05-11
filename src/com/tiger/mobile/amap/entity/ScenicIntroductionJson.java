package com.tiger.mobile.amap.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScenicIntroductionJson implements Serializable {
	private String scenicId;

	public String getScenicId() {
		return scenicId;
	}

	public void setScenicId(String scenicId) {
		this.scenicId = scenicId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public ArrayList<Recommend> getImageList() {
		return imageList;
	}

	public void setImageList(ArrayList<Recommend> imageList) {
		this.imageList = imageList;
	}

	public String getScenicLevel() {
		return scenicLevel;
	}

	public void setScenicLevel(String scenicLevel) {
		this.scenicLevel = scenicLevel;
	}

	public String getScenicType() {
		return scenicType;
	}

	public void setScenicType(String scenicType) {
		this.scenicType = scenicType;
	}

	private String desc;
	private ArrayList<Recommend> imageList;

	private String scenicLevel;
	private String scenicType;

	@Override
	public String toString() {
		return "ScenicIntroductionJson [scenicId=" + scenicId + ", desc="
				+ desc + ", imageList=" + imageList + ", scenicLevel="
				+ scenicLevel + ", scenicType=" + scenicType + "]";
	}
}
