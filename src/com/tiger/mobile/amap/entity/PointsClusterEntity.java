
package com.tiger.mobile.amap.entity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;

import com.amap.api.maps2d.MapView;


/**
 * 聚合模型.
 *
 * @author    Jiang
 * 
 * <p>Modification History:</p>
 * <p>Date              Author      Description</p>
 * <p>------------------------------------------------------------------</p>
 * <p>            new    </p>
 * <p>  </p>
 */
public class PointsClusterEntity {

	private MapView mapView;
//	private Envelope boundsEnv;// 创建区域
	private int clusterCount;
	private String eventDegree;
	private Double Lng;
	private Double Lat;
	private String text;
	private String clusterId;
	private List<ScenicModel> subScenicEntity = new ArrayList<ScenicModel>();

	public PointsClusterEntity() {

	}
	
	public PointsClusterEntity(String clusterId, String text) {
		this.clusterId = clusterId;
		this.text = text;
	}
	
	public PointsClusterEntity(MapView mapView, Point firstMarkers,
			int gridSize) {
		this.mapView = mapView;
		Point point = mapView.toScreenPoint(firstMarkers);
		Point southwestPoint = new Point(point.getX() - gridSize, point.getY() + gridSize);
		Point northeastPoint = new Point(point.getX() + gridSize, point.getY() - gridSize);
		boundsEnv = new Envelope(southwestPoint.getX(), northeastPoint.getY(), 
				northeastPoint.getX(), southwestPoint.getY());
	}

	/**
	 * 添加marker
	 */
	
	public Envelope getBoundsEnv() {
		return boundsEnv;
	}

	public void setPosition() {
		int size = subEventEntity.size();
		if (size == 1) {
			this.Lat = subEventEntity.get(0).getLat().doubleValue();
			this.Lng = subEventEntity.get(0).getLng().doubleValue();
			return;
		}
		double lat = 0.0;
		double lng = 0.0;
		for (EventReportEntity op : subEventEntity) {
			lat += op.getLat().doubleValue();
			lng += op.getLng().doubleValue();
		}
		this.Lat = lat / size;
		this.Lng = lng / size;// 设置中心位置为聚集点的平均距离
		 
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Double getLng() {
		return Lng;
	}

	public void setLng(Double lng) {
		Lng = lng;
	}

	public Double getLat() {
		return Lat;
	}

	public void setLat(Double lat) {
		Lat = lat;
	}

	public String getEventDegree() {
		return eventDegree;
	}

	public void setEventDegree(String eventDegree) {
		this.eventDegree = eventDegree;
	}

	public String getClusterId() {
		return clusterId;
	}

	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	public int getClusterCount() {
		return clusterCount;
	}

	public void setClusterCount(int clusterCount) {
		this.clusterCount = clusterCount;
	}

	public List<ScenicModel> getSubScenicEntity() {
		return subScenicEntity;
	}

	public void setSubEventEntity(List<ScenicModel> subScenicEntity) {
		this.subScenicEntity = subScenicEntity;
	}

}
