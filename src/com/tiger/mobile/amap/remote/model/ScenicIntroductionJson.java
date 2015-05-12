package com.tiger.mobile.amap.remote.model;

import java.io.Serializable;
import java.util.List;

public class ScenicIntroductionJson  implements Serializable 
{
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
public List<Recommend> getImageList() {
	return imageList;
}
public void setImageList(List<Recommend> imageList) {
	this.imageList = imageList;
}
private String desc;
  private List< Recommend> imageList;
}
