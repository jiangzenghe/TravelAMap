package com.tiger.mobile.amap.remote.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;
@Table(name="SpotInfos")
public class SpotInfo  extends Model implements Serializable {
	@Column(name="spotid")
    private String spotid;
    @Column(name="spotname")
    private String spotName;
	public String getSpotid() {
		return spotid;
	}

	public void setSpotid(String spotid) {
		this.spotid = spotid;
	}

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
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

	public String getSpotType() {
		return spotType;
	}

	public void setSpotType(String spotType) {
		this.spotType = spotType;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

    public SpotInfo() {

        this.spotid = "";
    }

    @Override
	public String toString() {
		return "SpotInfo [spotid=" + spotid + ", lat=" + lat + ", lng=" + lng
				+ ", spotType=" + spotType + ", order=" + order + "]";
	}
    @Column(name="lat")
	private double lat; // γ��
    @Column(name="lng")
	private double lng; // ����
    @Column(name="spotType")
	private String spotType;
    @Column(name="lineorder")
	private int order;// -----����·�е�˳���

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    @Column(name = "lineId")
    private String lineId;
}
