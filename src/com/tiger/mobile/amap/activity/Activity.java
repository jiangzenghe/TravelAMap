package com.tiger.mobile.amap.activity;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.tiger.mobile.amap.util.Utils;


/**
 * Activity基类.
 *
 * @author
 * 
 * <p>Date            Author       Description</p>
 * <p>------------------------------------------------------------------</p>
 * <p>   </p>
 * <p>  </p>
 */
public class Activity extends FragmentActivity {
	
	/** The bundle. */
//	private Bundle bundle;
//	
//	/**
//	 * Gets the meta data bundle.
//	 *
//	 * @return the meta data bundle
//	 */
//	public Bundle getMetaDataBundle() {
//		if (bundle == null) {
//			bundle = Utils.getActivityMetaDataBundle(getPackageManager(), getComponentName());
//		}
//		return bundle;
//	}
//	
//	/**
//	 * Gets the meta data string.
//	 *
//	 * @param key the key
//	 * @param defValue the def value
//	 * @return the meta data string
//	 */
//	public String getMetaDataString(String key, String defValue) {
//		if (getMetaDataBundle() != null && getMetaDataBundle().containsKey(key)) {
//			return getMetaDataBundle().getString(key);
//		}
//		return defValue;
//	}
//	
//	/**
//	 * Gets the meta data int.
//	 *
//	 * @param key the key
//	 * @param defValue the def value
//	 * @return the meta data int
//	 */
//	public int getMetaDataInt(String key, int defValue) {
//		if (getMetaDataBundle() != null && getMetaDataBundle().containsKey(key)) {
//			return getMetaDataBundle().getInt(key);
//		}
//		return defValue;
//	}
//	
//	/**
//	 * Gets the meta data long.
//	 *
//	 * @param key the key
//	 * @param defValue the def value
//	 * @return the meta data long
//	 */
//	public long getMetaDataLong(String key, long defValue) {
//		if (getMetaDataBundle() != null && getMetaDataBundle().containsKey(key)) {
//			return getMetaDataBundle().getLong(key);
//		}
//		return defValue;
//	}
//	
//	/**
//	 * Gets the meta data boolean.
//	 *
//	 * @param key the key
//	 * @param defValue the def value
//	 * @return the meta data boolean
//	 */
//	public boolean getMetaDataBoolean(String key, boolean defValue) {
//		if (getMetaDataBundle() != null && getMetaDataBundle().containsKey(key)) {
//			return getMetaDataBundle().getBoolean(key);
//		}
//		return defValue;
//	}
//	
//	/**
//	 * Gets the meta data float.
//	 *
//	 * @param key the key
//	 * @param defValue the def value
//	 * @return the meta data float
//	 */
//	public float getMetaDataFloat(String key, float defValue) {
//		if (getMetaDataBundle() != null && getMetaDataBundle().containsKey(key)) {
//			return getMetaDataBundle().getFloat(key);
//		}
//		return defValue;
//	}
//    /** 
//     * 
//     *  对打开过的activty进行一系列的操作
//     * 
//     */ 
//    public static class ActivityCpllector{
//    	public static  List<Activity> activities =new ArrayList<Activity>();
//    	public static void addActivity(Activity activity){
//    		activities.add(activity);
//    	}
//    	public static void removeActivity(Activity activity){
//    		activities.remove(activity);
//    	}
//    	public static void finishAll(){
//    		for (Activity activity:activities) {
//				if (!activity.isFinishing()) {
//					activity.finish();
//				}
//			}
//    	}
//    }
    
}
