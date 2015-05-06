package com.tiger.mobile.amap.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMap.OnCameraChangeListener;
import com.amap.api.maps2d.AMap.OnMapLoadedListener;
import com.amap.api.maps2d.AMapOptions;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.model.TileOverlay;
import com.amap.api.maps2d.model.TileOverlayOptions;
import com.amap.api.maps2d.model.VisibleRegion;
import com.tiger.mobile.amap.R;
import com.tiger.mobile.amap.util.MyUrlTileProvider;

/**
 * AMapV1地图demo总汇
 */
public final class MapActivity extends Activity implements OnCameraChangeListener, OnMapLoadedListener,
	LocationSource, AMapLocationListener {

	private AMap mMap;
	private MapView mapView;
	private TextView cilckText;
	private GridView layoutShow;
	private OnLocationChangedListener mListener;
	private LocationManagerProxy mAMapLocationManager;
	private TileOverlay tileOverlay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_activity);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);//显示返回箭头
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);// 此方法必须重写
		
		cilckText = (TextView) findViewById(R.id.help);
		layoutShow = (GridView) findViewById(R.id.layout_show);
//		layoutShow.setVisibility(View.VISIBLE);
		cilckText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(layoutShow.getVisibility() == View.VISIBLE) {
					TranslateAnimation mHideAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,     
							Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,     
							0.0f, Animation.RELATIVE_TO_SELF, 1.0f);  
					mHideAction.setDuration(50); 
					layoutShow.setAnimation(mHideAction);
					layoutShow.setVisibility(View.GONE);
				} else {
					TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,     
							Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,     
							1.0f, Animation.RELATIVE_TO_SELF, 0.0f);  
					mShowAction.setDuration(50); 
					layoutShow.setAnimation(mShowAction);
					layoutShow.setVisibility(View.VISIBLE);
				}
			}
		});
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float a = 1.0f;
		
		return false;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		 case android.R.id.home:
		        finish(); 
		        return true; 
		default:
			return super.onOptionsItemSelected(item);
		}
	}	
	
	/**
	 * 初始化AMap对象
	 */
	private void init() {
		if (mMap == null) {
			mMap = mapView.getMap();
			setUpMap();
			mMap.setOnCameraChangeListener(this);
			mMap.setOnMapLoadedListener(this);
			mMap.getUiSettings().setCompassEnabled(true);
			mMap.getUiSettings().setZoomControlsEnabled(false);
//			mMap.getUiSettings().setZoomPosition(AMapOptions.ZOOM_POSITION_RIGHT_CENTER);
		}
		
	}
	
	private void setUpMap() {
        
        // 自定义系统定位蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
		myLocationStyle.myLocationIcon(BitmapDescriptorFactory
				.fromResource(R.drawable.location_marker));// 设置小蓝点的图标
		myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
		myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
		// myLocationStyle.anchor(int,int)//设置小蓝点的锚点
		myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
		mMap.setMyLocationStyle(myLocationStyle);// 将自定义的 myLocationStyle 对象添加到地图上
		mMap.setLocationSource(this);// 设置定位监听 //设置定位资源。如果不设置此定位资源则定位按钮不可点击。
		mMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
		mMap.setMyLocationEnabled(false);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
 
	}
	
	/**
	 * 定位成功后回调函数
	 */
	@Override
	public void onLocationChanged(AMapLocation aLocation) {
		if (mListener != null && aLocation != null) {
			mListener.onLocationChanged(aLocation);// 显示系统小蓝点
		}
	}

	/**
	 * 激活定位
	 */
	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mAMapLocationManager == null) {
			mAMapLocationManager = LocationManagerProxy.getInstance(this);
			/*
			 * mAMapLocManager.setGpsEnable(false);
			 * 1.0.2版本新增方法，设置true表示混合定位中包含gps定位，false表示纯网络定位，默认是true Location
			 * API定位采用GPS和网络混合定位方式
			 * ，第一个参数是定位provider，第二个参数时间最短是2000毫秒，第三个参数距离间隔单位是米，第四个参数是定位监听者
			 */
			mAMapLocationManager.requestLocationUpdates(
					LocationProviderProxy.AMapNetwork, 2000, 10, this);
		}
	}

	/**
	 * 停止定位
	 */
	@Override
	public void deactivate() {
		mListener = null;
		if (mAMapLocationManager != null) {
			mAMapLocationManager.removeUpdates(this);
			mAMapLocationManager.destory();
		}
		mAMapLocationManager = null;
	}
	
	@Override
	public void onMapLoaded() {
		addOverlayToMap();
	}
	
	/**
	 * 往地图上添加一个groundoverlay覆盖物
	 */
	private void addOverlayToMap() {
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
				new LatLng(25.29954199,110.30164737), 18));
		if (tileOverlay != null)
        {
            tileOverlay.remove();
        }
		tileOverlay = mMap.addTileOverlay((new TileOverlayOptions())
        		.tileProvider(new MyUrlTileProvider(256, 256))
        		.zIndex(-10));
		tileOverlay.setVisible(true);
		
	}
	
	@Override
	public void onCameraChange(CameraPosition cp) {
		// TODO Auto-generated method stub
		onCameraChangeFinish(cp);
	}
	
    @Override
	public void onCameraChangeFinish(CameraPosition cameraPosition) {
    	
	    VisibleRegion visibleRegion = mMap.getProjection().getVisibleRegion(); 
	    // 获取可视区域
//	    LatLngBounds latLngBounds = visibleRegion.latLngBounds;
//	    if(!bounds.contains(latLngBounds))
//	    {
//	    // 获取可视区域的Bounds
//	    //boolean isContain = latLngBounds.contains(Constants.SHANGHAI);
//	    // 判断上海经纬度是否包括在当前地图可见区域
//	    	LatLng center = new LatLng(37.520049,121.360109);
//	    }
	    
	}
	
	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
		init();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
		deactivate();
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
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}
