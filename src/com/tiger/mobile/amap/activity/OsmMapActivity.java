package com.tiger.mobile.amap.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.MapsInitializer;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.GroundOverlayOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.PolylineOptions;
import com.amap.api.maps2d.model.TileOverlay;
import com.amap.api.maps2d.model.TileOverlayOptions;
import com.tiger.mobile.amap.R;
import com.tiger.mobile.amap.util.MyUrlTileProvider;

/*
 * 替换底图资源显示，这里用于显示openstreetmap的底图
 * (OSM数据在国内采用原始84坐标系，在国内谨慎使用，  国外场景下可使用osm数据)
 * */
public class OsmMapActivity extends Activity {
	private MapView mapView;
	private AMap mAMap;
	private TileOverlay tileOverlay;
	
//	private static final String OSM_URL = "http://a.tile.openstreetmap.org/%d/%d/%d.png";
	private static final String OSM_URL = "http://imyuu.com/tile/22/%d/%d_%d.png";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.osmmap_activity);

		// 用开源栅格图源替换AMap图源（坐标系符合国测局规定坐标系）
		// 相关限制：1、需要在地图初始化之前调用该方法
		// 2、设置该方法会导致中英文地图切换失效
		// 3、提供地址默认替换顺序为 zoom、x、y
		MapsInitializer.replaceURL(OSM_URL, "OSM"); // 地图初始化之前设置,将底图资源进行替换
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);// 此方法必须重写

		mAMap = mapView.getMap();
		addOverlayToMap();
	}

	private void addOverlayToMap() {
		//,
//		mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
//				new LatLng(25.29954199,110.30164737), 18));// 设置当前地图显示//25.29954199,110.30164737//37.520049,121.360109
//		
//		
		mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
				new LatLng(25.29954199,110.30164737), 18));
//		if (tileOverlay != null)
//        {
//            tileOverlay.remove();
//        }
//		tileOverlay = mAMap.addTileOverlay((new TileOverlayOptions())
//        		.tileProvider(new MyUrlTileProvider(256, 256))
//        		.zIndex(-10));
//		tileOverlay.setVisible(true);
		
//		mAMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f).position(new LatLng(25.299542,110.301648)));
		ArrayList<LatLng> arg0 = new ArrayList<LatLng>();
		arg0.add(new LatLng(25.299542,110.301648));
		arg0.add(new LatLng(25.3,110.4));
//		arg0.add(new LatLng(25.299544,110.301650));
//		arg0.add(new LatLng(25.299545,110.301651));
//		arg0.add(new LatLng(25.299546,110.301652));
//		mAMap.addNavigateArrow(new NavigateArrowOptions().addAll(arg0)
//				.topColor(R.color.black)
//				.sideColor(R.color.red));
		mAMap.addPolyline(new PolylineOptions().addAll(arg0).color(Color.RED));
		
		LatLngBounds bounds = new LatLngBounds.Builder()
		.include(new LatLng(25.29797,110.29929))   //25.29797,110.29929 //37.515658,121.352212
		.include(new LatLng(25.301792,110.304086)).build(); //25.301792,110.304086  //37.528217,121.365323

//		groundoverlay = mAMap.addGroundOverlay(new GroundOverlayOptions()
//				.anchor(0.5f, 0.5f)
//				.transparency(0f)
//				.image(BitmapDescriptorFactory
//						.fromResource(R.drawable.groundoverlay))
//				.positionFromBounds(bounds).zIndex(-10));
		mAMap.getUiSettings().setCompassEnabled(true);
		
		
	 
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

}
