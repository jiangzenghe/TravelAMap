package com.tiger.mobile.amap.util;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.tiger.mobile.amap.entity.PointsClusterEntity;
import com.tiger.mobile.amap.entity.ScenicModel;

public class ClusterUtils {
	private int gridSize = 25;
	private int height;// 屏幕高度(px)
	private int width;// 屏幕宽度(px)
	private Activity activity;
	private AMap aMap;
	/**
	 * 所有的point
	 */
	ArrayList<ScenicModel> pointsList;
	
	Handler handler;

	public ClusterUtils(Activity activity, AMap aMap,
			ArrayList<ScenicModel> pointsList) {
		super();
		this.activity = activity;
		this.aMap = aMap;
//		this.commonLayer = commonLayer;
		handler = new Handler();
		this.pointsList = pointsList;
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		width = dm.widthPixels;
		height = dm.heightPixels;
	}

	/**
	 * 获取视野内的  根据聚合算法合成自定义的 显示视野内的
	 * 
	 */
	public ArrayList<PointsClusterEntity> resetMarks() {
		// 自定义的聚合类MarkerCluster
		ArrayList<PointsClusterEntity> clustersList = new ArrayList<PointsClusterEntity>();
		// 开始刷新界面
		for (ScenicModel fp : pointsList) {
			
			if (clustersList.size() == 0) {
				add2Cluster(fp, clustersList);
			} else {
				boolean isIn = false;
				for (PointsClusterEntity cluster : clustersList) {
					if (cluster.getBoundsEnv().contains(fp.getLatLng())) {
						cluster.setClusterCount(cluster.getClusterCount() + 1);
						
						ScenicModel object = new ScenicModel();
						object.setLatLng(fp.getLatLng());
						cluster.getSubScenicEntity().add(object);
						isIn = true;
						break;
					}
				}
				if (!isIn) {
					add2Cluster(fp, clustersList);
				}
			}
		}
		
		for (PointsClusterEntity pointCluster : clustersList) {
			pointCluster.setPosition();// 设置聚合点的位置和icon
		}
		
		addClusterGrphic(clustersList);
		return clustersList;
	}

	private void add2Cluster(ScenicModel each, ArrayList<PointsClusterEntity> normalClustList) {
		LatLng point = each.getLatLng();
		PointsClusterEntity arg0 = new PointsClusterEntity(aMap, point, 
				gridSize);
		arg0.setClusterCount(1);
		arg0.setClusterId(normalClustList.size() + 1 + "");
		
		ScenicModel object = new ScenicModel();
		object.setLatLng(point);
		arg0.getSubScenicEntity().add(object);
		normalClustList.add(arg0);
	}
	
	private void addClusterGrphic(ArrayList<PointsClusterEntity> mClusterDatas) {
		//test the time to draw
		Log.e("draw start", new Date().getTime() + "");
		ArrayList<Marker> markerList = (ArrayList)aMap.getMapScreenMarkers();
		if(markerList!=null) {
			for(Marker each:markerList) {
				if(each.getObject() != null && each.getObject().equals("1")) {
					each.remove();
				}
			}
		}
		
		for (PointsClusterEntity each : mClusterDatas){
			MarkerOptions arg0 = new MarkerOptions().anchor(0.5f, 1.0f)
					.position(new LatLng(each.getLat(), each.getLng()));
			Marker eachMarker = aMap.addMarker(arg0);
			eachMarker.setObject("1");//1--景点聚合标志
		}
		
		//test the time to draw
		Log.e("draw end", new Date().getTime() + "");
	}
	
}
