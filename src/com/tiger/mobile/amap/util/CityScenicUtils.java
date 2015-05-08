
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
    	object0.setCityPosition(new LatLng(39.943436, 116.323242));
    	cityLists.add(object0);
    	City object1 = new City("天津", "tianjin");
    	object1.setCityPosition(new LatLng(39.164141, 117.202148));
    	cityLists.add(object1);
    	City object2 = new City("上海", "shanghai");
    	object2.setCityPosition(new LatLng(31.353637, 121.508789));
    	cityLists.add(object2);
    	City object3 = new City("山东", "shandong");
    	object3.setCityPosition(new LatLng(36.721274, 117.053833));
    	cityLists.add(object3);
    	City object4 = new City("新疆", "xinjiang");
    	object4.setCityPosition(new LatLng(41.705729, 88.242188));
    	cityLists.add(object4);
    	City object5 = new City("河北", "hebei");
    	object5.setCityPosition(new LatLng(38.169114, 114.741211));
    	cityLists.add(object5);
    	City object6 = new City("河南", "henan");
    	object6.setCityPosition(new LatLng(34.524661, 113.598633));
    	cityLists.add(object6);
    	City object7 = new City("甘肃", "gansu");
    	object7.setCityPosition(new LatLng(36.668419, 103.886719));
    	cityLists.add(object7);
    	City object8 = new City("山西", "shanxi1");
    	object8.setCityPosition(new LatLng(37.788081, 112.456055));
    	cityLists.add(object8);
    	City object9 = new City("陕西", "shanxi3");
    	object9.setCityPosition(new LatLng(34.379713, 109.116211));
    	cityLists.add(object9);
    	City object10 = new City("广西", "guangxi");
    	object10.setCityPosition(new LatLng(23.563987, 108.369141));
    	cityLists.add(object10);
    	City object11 = new City("广东", "guangdong");
    	object11.setCityPosition(new LatLng(23.362429, 113.15918));
    	cityLists.add(object11);
    	City object12 = new City("四川", "sichuan");
    	object12.setCityPosition(new LatLng(30.562261, 103.930664));
    	cityLists.add(object12);
    	City object13 = new City("云南", "yunnan");
    	object13.setCityPosition(new LatLng(25.165173, 102.568359));
    	cityLists.add(object13);
    	City object14 = new City("西藏", "xizang");
    	object14.setCityPosition(new LatLng(29.916852, 91.625977));
    	cityLists.add(object14);
    	City object15 = new City("黑龙江", "heilongjiang");
    	object15.setCityPosition(new LatLng(45.79817, 126.5625));
    	cityLists.add(object15);
    	City object16 = new City("辽宁", "liaoning");
    	object16.setCityPosition(new LatLng(41.836828, 123.442383));
    	cityLists.add(object16);
    	City object17 = new City("内蒙古", "neimenggu");
    	object17.setCityPosition(new LatLng(40.913513, 111.796875));
    	cityLists.add(object17);
    	City object18 = new City("吉林", "jilin");
    	object18.setCityPosition(new LatLng(43.992815, 125.332031));
    	cityLists.add(object18);
    
    	return cityLists;
    
    }
}
