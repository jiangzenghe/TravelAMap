package com.tiger.mobile.amap;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.util.Log;

public class AppApplication extends Application {

	private ArrayList<Activity> activities = new ArrayList<Activity>();
    private static AppApplication instance;

    public AppApplication() {
         instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
//        Fresco.initialize(this.getBaseContext());
        Log.d("IuuApplication", "init");
        instance = this;



    }
    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
    //单例模式中获取唯一的MyApplication实例
    public static AppApplication getInstance() {
        if (null == instance) {
            instance = new AppApplication();
        }
        return instance;
    }

    //添加Activity到容器中
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void deleteActivity(Activity activity) {
        activities.remove(activity);
    }

    //finish
    public void exit() {
        for (Activity activity : activities) {
            activity.finish();
        }
        activities.clear();

    }
	
}