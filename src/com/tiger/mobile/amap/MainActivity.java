package com.tiger.mobile.amap;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.tiger.mobile.amap.entity.ScenicModel;
import com.tiger.mobile.amap.util.CityScenicUtils;

/**
 * AMapV1地图demo总汇
 */
public final class MainActivity extends Activity {

	private AMap mMap;
	private MapView mapView;
	private ArrayList<ScenicModel> scenicLists;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		setTitle("2D地图Demo" + AMap.getVersion());
		
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);// 此方法必须重写
		init();
	}

	/**
	 * 初始化AMap对象
	 */
	private void init() {
		scenicLists = new ArrayList<ScenicModel>();
		scenicLists = CityScenicUtils.createCityScenics();
		if (mMap == null) {
			mMap = mapView.getMap();
		}
	}
	
	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		System.exit(0);
	}
}
