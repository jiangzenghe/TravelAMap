package com.tiger.mobile.amap.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;

import com.amap.api.maps2d.MapView;
import com.tiger.mobile.amap.R;

public class ClusterUtils {
	private int gridSize = 25;
	private int height;// 屏幕高度(px)
	private int width;// 屏幕宽度(px)
	private Activity activity;
	private MapView mapView;
	private GraphicsLayer commonLayer;
	/**
	 * 所有的event
	 */
	ArrayList<Feature> eventOptionsList;
	/**
	 * 所有可见的marker&event
	 */
	ArrayList<Feature> eventOptionsListInView = new ArrayList<Feature>();
	
	Handler handler;

	public ClusterUtils(Activity activity, MapView mapView, GraphicsLayer commonLayer,
			ArrayList<Feature> eventOptionsList) {
		super();
		this.activity = activity;
		this.mapView = mapView;
		this.commonLayer = commonLayer;
		handler = new Handler();
		this.eventOptionsList = eventOptionsList;
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		width = dm.widthPixels;
		height = dm.heightPixels;
	}

	/**
	 * 获取视野内的event 根据聚合算法合成自定义的event 显示视野内的event
	 * 
	 */
	public ArrayList<EventClusterEntity> resetMarks() {
		// 自定义的聚合类MarkerCluster
		ArrayList<EventClusterEntity> clustersEventList = new ArrayList<EventClusterEntity>();
		// 开始刷新界面
		eventOptionsListInView.clear();
		
		// 获取在当前视野内的event;提高效率
		for (Feature fp : eventOptionsList) {
			Point op = (Point)fp.getGeometry();
			
			if(op == null || !op.isValid()) {
				continue;
			}
			Point p = mapView.toScreenPoint(op);
			if(p == null) {
				continue;
			}
			
			if(eventOptionsList.size() > 1000) {
				if (p.getX() < 0 || p.getY() < 0 || p.getX() > width || p.getY() > height) {
					// 不添加到计算的列表中
				} else {
					eventOptionsListInView.add(fp);
				}
			} else {
				eventOptionsListInView.add(fp);
			}
		}
		
		for (Feature fp : eventOptionsListInView) {
			Point op = (Point)fp.getGeometry();
			if(op == null || !op.isValid()) {
				continue;
			}
			Point p = mapView.toScreenPoint(op);
			if(p == null) {
				continue;
			}
			
			if (clustersEventList.size() == 0) {
				add2Cluster(fp, clustersEventList);
			} else {
				boolean isIn = false;
				for (EventClusterEntity cluster : clustersEventList) {
					if (cluster.getBoundsEnv().contains(p)) {
						cluster.setClusterCount(cluster.getClusterCount() + 1);
						try {
							int lastDegree = Integer.parseInt(cluster.getEventDegree());//已有严重程度值
							int newDegree = Integer.parseInt(fp.getAttributes().get("SEVERITY_FLAG") + "");//新的严重程度
							if(lastDegree < newDegree) {
								cluster.setEventDegree(fp.getAttributes().get("SEVERITY_FLAG") + "");
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
						EventReportEntity object = new EventReportEntity();
						object.setBusiUuid(fp.getAttributes().get("BUSI_UUID") + "");
						object.setEventType(fp.getAttributes().get("EVENT_TYPE") + "");
						object.setEventDegree(fp.getAttributes().get("SEVERITY_FLAG") + "");
						object.setLat(new BigDecimal(op.getY()));
						object.setLng(new BigDecimal(op.getX()));
						cluster.getSubEventEntity().add(object);
						isIn = true;
						break;
					}
				}
				if (!isIn) {
					add2Cluster(fp, clustersEventList);
				}
			}
		}
		
		for (EventClusterEntity eventCluster : clustersEventList) {
			eventCluster.setPosition();// 设置聚合点的位置和icon
		}
		
		addClusterGrphic(clustersEventList);
		return clustersEventList;
	}

	private void add2Cluster(Feature each, ArrayList<EventClusterEntity> eventClustList) {
		Point point = (Point)each.getGeometry();
		EventClusterEntity arg0 = new EventClusterEntity(mapView, point, 
				gridSize);
		arg0.setClusterCount(1);
		arg0.setClusterId(eventClustList.size() + 1 + "");
		arg0.setEventDegree(each.getAttributes().get("SEVERITY_FLAG") + "");
		
//		arg0.setEventType(each.getAttributes().get("EVENT_TYPE") + "");
		EventReportEntity object = new EventReportEntity();
		object.setBusiUuid(each.getAttributes().get("BUSI_UUID") + "");
		object.setEventType(each.getAttributes().get("EVENT_TYPE") + "");
		object.setEventDegree(each.getAttributes().get("SEVERITY_FLAG") + "");
		object.setLat(new BigDecimal(point.getY()));
		object.setLng(new BigDecimal(point.getX()));
		arg0.getSubEventEntity().add(object);
		eventClustList.add(arg0);
	}
	
	private void addClusterGrphic(ArrayList<EventClusterEntity> mClusterEventDatas) {
		//test the time to draw
		Log.e("draw start", new Date().getTime() + "");
		
		for (EventClusterEntity each : mClusterEventDatas){
			// turn feature into graphic
			PictureMarkerSymbol symbol = new PictureMarkerSymbol(activity, 
					activity.getResources().getDrawable(R.drawable.cluster_q));
			if(each.getSubEventEntity().size() == 1) {
				symbol = Utils
						.setEventPictureSymbol(activity.getApplicationContext(), each.getSubEventEntity().get(0).getEventType() + 
								each.getSubEventEntity().get(0).getEventDegree());
			} else {
				symbol = setEventDegreeColor(each.getEventDegree());
			}
			
			Map<String, Object> attrContent = new HashMap<String, Object>();
			attrContent.put("CLUSTER_ID", each.getClusterId());
			attrContent.put("CLUSTER_COUNT", each.getClusterCount());
			Point point = new Point(each.getLng(), each.getLat());
			symbol.setOffsetY(25);
			Graphic g = new Graphic(point, symbol, attrContent);
			
			commonLayer.addGraphic(g);
			if(each.getClusterCount() > 1) {
				TextSymbol addtion_symbol = new TextSymbol(16, each.getClusterCount()+"", Color.WHITE, 
						TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.BOTTOM);
				addtion_symbol.setOffsetY(20);
				Graphic addtion_g = new Graphic(point, addtion_symbol, attrContent);
				commonLayer.addGraphic(addtion_g);
			}
		}
		
		//test the time to draw
		Log.e("draw end", new Date().getTime() + "");
	}
	
	//事件等级
	private PictureMarkerSymbol setEventDegreeColor(String in) {
		PictureMarkerSymbol symbol = new PictureMarkerSymbol(activity, 
				activity.getResources().getDrawable(R.drawable.cluster_q));
		String typeStr = in;
		try {
			int type = Integer.parseInt(typeStr);
			switch (type) {
			case 1:
				symbol = new PictureMarkerSymbol(activity, 
						activity.getResources().getDrawable(R.drawable.cluster_qw));
				break;
			case 2:
				symbol = new PictureMarkerSymbol(activity, 
						activity.getResources().getDrawable(R.drawable.cluster_q));
				break;
			case 3:
				symbol = new PictureMarkerSymbol(activity, 
						activity.getResources().getDrawable(R.drawable.cluster_zhong));
				break;
			case 4:
				symbol = new PictureMarkerSymbol(activity, 
						activity.getResources().getDrawable(R.drawable.cluster_z));
				break;
			case 5:
				symbol = new PictureMarkerSymbol(activity, 
						activity.getResources().getDrawable(R.drawable.cluster_yz));
				break;
			default:
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return symbol;
	}
	
}
