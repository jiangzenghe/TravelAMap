package com.tiger.mobile.amap.activity;


import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;

import com.astuetz.PagerSlidingTabStrip;
import com.tiger.mobile.amap.R;
import com.tiger.mobile.amap.fragment.Fragments;
import com.tiger.mobile.amap.fragment.ScenicAreaFragment;
import com.tiger.mobile.amap.fragment.ScenicSummaryFragment;
import com.tiger.mobile.amap.fragment.ScenicTransportFragment;
import com.tiger.mobile.amap.util.LogUtil;

/**
 * Created by tule on 2015/4/28.
 */
public class ScenicAreaActivity extends Activity {

    private  ViewPager viewPager;
    private  MyViewPagerAdaptor adaptor;
    private  ArrayList<Fragment> views;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenicarea);

        initView();

        LogUtil.v("ok");
    }

    private void initView() {
        final Intent intent =getIntent();
        String scenicId=intent.getStringExtra("scenicId");
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mToolbar.setNavigationIcon(R.drawable.ic_back);
//        mToolbar.setTitle(intent.getStringExtra("scenicName"));
//        setSupportActionBar(mToolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        views = new ArrayList<Fragment>();

        Fragments fragment=new Fragments();

        views.add(fragment);
        views.add(new ScenicSummaryFragment());
        views.add(new ScenicTransportFragment());
        views.add(new ScenicAreaFragment());
        views.add(new Fragments());
        views.add(new ScenicAreaFragment());
        adaptor = new MyViewPagerAdaptor(getSupportFragmentManager());
        viewPager.setAdapter(adaptor);

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        viewPager.setPageMargin(pageMargin);
        tabs.setViewPager(viewPager);
    }

    /**
     * ViewPagerIndex adaptor
     */
    class MyViewPagerAdaptor extends FragmentPagerAdapter{
        private final String[] TITLES = { "详情", "简介", "交通",  "小贴士", "美食",
                "酒店" };

        public MyViewPagerAdaptor(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int position) {
            if(views!=null)
                return views.get(position);
            return null;
        }

        @Override
        public int getCount() {
            if(views!=null)
                return views.size();
            return 0;
        }
    }
}
