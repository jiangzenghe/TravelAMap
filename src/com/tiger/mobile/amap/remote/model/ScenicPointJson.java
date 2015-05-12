package com.tiger.mobile.amap.remote.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/*
 * ����������Ϣ
 * */

@Table(name = "scenicpoints")
public class ScenicPointJson extends Model {
    @Column(name = "spotId" )
    private String id;
    @Column(name = "scenicPointName")
    private String scenicPointName;
    @Column(name = "desc")
    private String desc;
    @Column(name = "audioUrl")
    private String audioUrl;
    @Column(name = "imageUrl")
    private String imageUrl;
    @Column(name = "lat")
    private double lat;  //γ��
    @Column(name = "lng")
    private double lng;  //����
    @Column(name = "px")
    private int px;  //ͼ�ϵ�λ��
    private int py;
    @Column(name = "spotType")
    private String spotType;

    public String getScenicId() {
        return scenicId;
    }

    public void setScenicId(String scenicId) {
        this.scenicId = scenicId;
    }
    @Column(name = "scenicId", index = true)
    private String  scenicId;
    public ScenicPointJson(){


    }
	public ScenicPointJson(String id, String scenicPointName, String desc,
			String audioUrl, String imageUrl, double lat, double lng, int px,
			int py, String spotType) {
		this.id = id;
		this.scenicPointName = scenicPointName;
		this.desc = desc;
		this.audioUrl = audioUrl;
		this.imageUrl = imageUrl;
		this.lat = lat;
		this.lng = lng;
		this.px = px;
		this.py = py;
		this.spotType = spotType;
	}

	public String getSpotId() {
		return this.id;
	}
	public void setSpotId(String id) {
		this.id = id;
	}
	public String getScenicPointName() {
		return scenicPointName;
	}
	public void setScenicPointName(String scenicPointName) {
		this.scenicPointName = scenicPointName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAudioUrl() {
		return audioUrl;
	}
	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public int getPx() {
		return px;
	}
	public void setPx(int px) {
		this.px = px;
	}
	public int getPy() {
		return py;
	}
	public void setPy(int py) {
		this.py = py;
	}
	public String getSpotType() {
		return spotType;
	}
	public void setSpotType(String spotType) {
		this.spotType = spotType;
	}
	@Override
	public String toString() {
		return "ScenicPointJson [id=" + id + ", scenicPointName="
				+ scenicPointName + ", desc=" + desc + ", audioUrl=" + audioUrl
				+ ", imageUrl=" + imageUrl + ", lat=" + lat + ", lng=" + lng
				+ ", px=" + px + ", py=" + py + ", spotType=" + spotType + "]";
	}

    public static List<ScenicPointJson> getAll(String scenicId) {
        try {
            return new Select()
                    .from(ScenicPointJson.class)
                    .where("scenicId = ?", scenicId)
                    .orderBy("spotId ASC")
                    .execute();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    private double distance;
}
