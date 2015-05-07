
package com.tiger.mobile.amap.util;

import java.util.ArrayList;

import android.annotation.SuppressLint;

import com.amap.api.maps2d.model.LatLng;
import com.tiger.mobile.amap.entity.City;
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

    public static ArrayList<City> createCities() {
    	ArrayList<City> cityLists = new ArrayList<City>();
    	City object0 = new City("北京", "beijing");
    	cityLists.add(object0);
    	City object1 = new City("天津", "tianjin");
    	cityLists.add(object1);
    	City object2 = new City("上海", "shanghai");
    	cityLists.add(object2);
    	City object3 = new City("山东", "shandong");
    	cityLists.add(object3);
    	City object4 = new City("新疆", "xinjiang");
    	cityLists.add(object4);
    	City object5 = new City("河北", "hebei");
    	cityLists.add(object5);
    	City object6 = new City("河南", "henan");
    	cityLists.add(object6);
    	City object7 = new City("甘肃", "gansu");
    	cityLists.add(object7);
    	City object8 = new City("山西", "shanxi1");
    	cityLists.add(object8);
    	City object9 = new City("陕西", "shanxi3");
    	cityLists.add(object9);
    	City object10 = new City("广西", "guangxi");
    	cityLists.add(object10);
    	City object11 = new City("广东", "guangdong");
    	cityLists.add(object11);
    	City object12 = new City("四川", "sichuan");
    	cityLists.add(object12);
    	City object13 = new City("云南", "yunnan");
    	cityLists.add(object13);
    	City object14 = new City("西藏", "xizang");
    	cityLists.add(object14);
    	City object15 = new City("黑龙江", "heilongjiang");
    	cityLists.add(object15);
    	City object16 = new City("辽宁", "liaoning");
    	cityLists.add(object16);
    	City object17 = new City("内蒙古", "neimenggu");
    	cityLists.add(object17);
    	City object18 = new City("吉林", "jilin");
    	cityLists.add(object18);
    
    	return cityLists;
    
    }
}
