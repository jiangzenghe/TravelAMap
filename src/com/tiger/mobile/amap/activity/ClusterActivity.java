package com.tiger.mobile.amap.activity;

import java.util.ArrayList;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMap.OnCameraChangeListener;
import com.amap.api.maps2d.AMap.OnMapLoadedListener;
import com.amap.api.maps2d.AMap.OnMarkerClickListener;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.model.VisibleRegion;
import com.tiger.mobile.amap.R;
import com.tiger.mobile.amap.entity.City;
import com.tiger.mobile.amap.entity.PointsClusterEntity;
import com.tiger.mobile.amap.entity.ScenicModel;
import com.tiger.mobile.amap.util.CityScenicUtils;
import com.tiger.mobile.amap.util.ClusterUtils;

/**
 * AMapV1地图demo总汇
 */
public final class ClusterActivity extends Activity implements OnCameraChangeListener, OnMapLoadedListener,
	LocationSource, AMapLocationListener, OnMarkerClickListener {

	private AMap mMap;
	private MapView mapView;
	private OnLocationChangedListener mListener;
	private LocationManagerProxy mAMapLocationManager;
	
	private ArrayList<ScenicModel> scenicLists;
	private ArrayList<PointsClusterEntity> mClusterDatas;
	private ClusterUtils utils;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cluster_activity);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);//显示返回箭头
        actionBar.setDisplayShowHomeEnabled(false); 
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("烟台");
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);// 此方法必须重写
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {  
	    case R.id.action_query:  
	       // 查询
	    	Intent intent2 =new Intent(ClusterActivity.this,QueryCityActivity.class);
	    	this.startActivityForResult(intent2, 0);
	        return true;  
	    default:  
	        return super.onOptionsItemSelected(item);  
	    }
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK) {
			return ;
		}
		//接受结束的字符串
		City city = (City)data.getExtras().getParcelable("cityResult");
		
		if(city!=null) {
			LatLng position = city.getCityPosition();
			if(position != null) {
				mMap.moveCamera(CameraUpdateFactory.changeLatLng(position));
			}
		}
			
	}
	
	/**
	 * 初始化AMap对象
	 */
	private void init() {
		scenicLists = new ArrayList<ScenicModel>();
		mClusterDatas = new ArrayList<PointsClusterEntity>();
		scenicLists = CityScenicUtils.createCityScenics();
		if (mMap == null) {
			mMap = mapView.getMap();
			setUpMap();
			mMap.setOnCameraChangeListener(this);
			mMap.setOnMapLoadedListener(this);
			mMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
		}
		
		//test
//		for(ScenicModel each: scenicLists) {		
//			MarkerOptions arg0 = new MarkerOptions().anchor(0.5f, 0.5f).position(each.getLatLng());
//			mMap.addMarker(arg0);
//		}
		
	}
	
	public void clusterShow() {
		if(scenicLists.size() == 0) {
			return;
		}
		
		mClusterDatas.clear();

		utils = new ClusterUtils(ClusterActivity.this, mMap, scenicLists);
		mClusterDatas = utils.resetMarks();
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
		mMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
 
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
		clusterShow();
	}
	
	@Override
	public void onCameraChange(CameraPosition cp) {
		// TODO Auto-generated method stub
		onCameraChangeFinish(cp);
	}
	
    @Override
	public void onCameraChangeFinish(CameraPosition cameraPosition) {
    	clusterShow();
    	
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
		init();//必须写在这里
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
		System.exit(0);
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

	@Override
	public boolean onMarkerClick(Marker arg0) {
		if(arg0.getObject().equals("1")) {
			Intent intent = new Intent(ClusterActivity.this, MapActivity.class);
			startActivity(intent);
		}
		
		return false;
	}
}
