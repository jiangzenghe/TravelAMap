
package com.tiger.mobile.amap.util;

import java.util.ArrayList;

import android.annotation.SuppressLint;

import com.amap.api.maps2d.model.LatLng;
import com.tiger.mobile.amap.entity.ScenicModel;

@SuppressLint("NewApi")
public class CityScenicUtils
{

    public CityScenicUtils()
    {
    }
    
    public static ArrayList<ScenicModel> createCityScenics() {
    	ArrayList<ScenicModel> scenicLists = new ArrayList<ScenicModel>();
    	ScenicModel object0 = new ScenicModel();
    	object0.setScenicId("0");
    	object0.setScenicName("烟台东炮台");
    	object0.setLatLng(new LatLng(37.532599, 121.350861));
    	scenicLists.add(object0);
    	ScenicModel object1 = new ScenicModel();
    	object1.setScenicId("1");
    	object1.setScenicName("烟台海水浴场");
    	object1.setLatLng(new LatLng(37.481942, 121.38588));
    	scenicLists.add(object1);
    	ScenicModel object2 = new ScenicModel();
    	object2.setScenicId("2");
    	object2.setScenicName("威海刘公岛");
    	object2.setLatLng(new LatLng(37.499921, 122.080078));
    	scenicLists.add(object2);
    	ScenicModel object3 = new ScenicModel();
    	object3.setScenicId("3");
    	object3.setScenicName("威海成山头");
    	object3.setLatLng(new LatLng(37.367974, 122.563477));
    	scenicLists.add(object3);
    	ScenicModel object4 = new ScenicModel();
    	object4.setScenicId("4");
    	object4.setScenicName("威海交通学院");
    	object4.setLatLng(new LatLng(37.474858, 122.107544));
    	scenicLists.add(object4);
    	
    	return scenicLists;
    }

}
