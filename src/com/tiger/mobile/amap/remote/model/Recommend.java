package com.tiger.mobile.amap.remote.model;

import java.io.Serializable;

public class Recommend implements Serializable {
	private String name;
	private String imageUrl;
    private  String intentLink;// 跳转 目标
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

    @Override
    public String toString() {
        return "Recommend{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", intentLink='" + intentLink + '\'' +
                '}';
    }

    public String getIntentLink() {
		return intentLink;
	}

	public void setIntentLink(String intentLink) {
		this.intentLink = intentLink;
	}
}




