package com.tiger.mobile.amap.activity;

import java.util.ArrayList;
import java.util.Locale;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.tiger.mobile.amap.R;
import com.tiger.mobile.amap.entity.City;

/**
 * 搜索功能
 *
 * @author 
 * 
 * <p>Modification History:</p>
 * <p>Date         Author      Description</p>
 * <p>      New  </p>
 * <p>  </p>
 */
public class QueryCityActivity extends Activity{
	//要查询的省份、城市、区县，从Intent中传入，发送至服务请求查询对应车站Station
	public static final String INTENT_EXT_PROVINCE = "province";
	public static final String INTENT_EXT_CITY = "city";
	public static final String INTENT_EXT_DISTRICT = "district";
		
		
	//从服务获取的StationList数据
	private ArrayList<City> mdata;
	
	/** The list view. */
	private ListView listView;
	 
 	/** The madapter. */
// 	private OrganizationChildListAdapter madapter;//listview适配器
	 
 	/** The items. */
// 	private List<Contact> items;//组成员的条目
 	private SearchView sView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_node_list);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);// 显示返回箭头
		actionBar.setDisplayShowHomeEnabled(false);
		listView=(ListView) findViewById(R.id.query_city_listview);
		sView=(SearchView) findViewById(R.id.query_city_searchview);
//		items = new ArrayList<Contact>();
//		 app = (HaiYiEIMApplacition)this.getApplication();
//		 sView.setOnQueryTextListener(new OnQueryTextListener() {
//				
//				@Override
//				public boolean onQueryTextSubmit(String query) {
//					return true;
//				}
//				
//				@Override
//				public boolean onQueryTextChange(String querytext) {//输入查询的条件变化
//					if (querytext==null||"".equals(querytext)) {
//						
//					}else {
//						items.clear();
//						//模糊查询人员
//					ContactQuery<EmployeeContactImple> queryEQuery= new ContactQuery<EmployeeContactImple>(app.getImClient(),EmployeeContactImple.class,querytext);
//					List<Object> eImple = queryEQuery.queryList();
//					for (int i = 0; i < eImple.size(); i++) { 
//						items.add((EmployeeContactImple)eImple.get(i));
//					}
//					//模糊查询组群
//					ContactQuery<GroupContactImple> queryGroupQuery= new ContactQuery<GroupContactImple>(app.getImClient(),GroupContactImple.class,querytext);
//					List<Object> groupList = queryGroupQuery.queryList();
//					for (int i = 0; i < groupList.size(); i++) { 
//						items.add((GroupContactImple)groupList.get(i));
//					}
//					removeRepetition(items);
//					madapter = new OrganizationChildListAdapter(items, QueryCityActivity.this);
//					listView.setAdapter(madapter);
//					madapter.notifyDataSetChanged();
//					}
//					listView.setOnItemClickListener(new OnItemClickListener() {
//
//						@Override
//						public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//								long arg3) {
//							if (groupId==null) {//直接搜索查询
//								
//							} else {//组群添加模糊查询的人员
//								 if (contact instanceof GroupContactImple) {
//									 return;
//								}
//							}
//																									
//						}
//					});				
//					return true;
//				}
//			});
		
	}
	
	//Filter
	public ArrayList<City> arrayListFilter(int filterType , String filterStr) {
        
        final int count = mdata.size();  

        final ArrayList<City> results = new ArrayList<City>();  
                  
        for (int i = 0; i < count; i++) {  
        	final String nameValueText = "";//原始字符串
            final String codeValue = "";//原始字符串  
//            final String nameValueText = mdata.get(i).getSationName();//原始字符串
//            final String codeValue = mdata.get(i).getSationNamePinYin();//原始字符串  
            final String codeValueText = codeValue.toString().toLowerCase(Locale.getDefault());  

            // 匹配核心代码，需要自己实现，这里只提供了基本的过滤类型  
            switch (filterType) {  
            case 0:  
                results.add(mdata.get(i));  //ALL
 
                break;  
            case 1:  
                if (nameValueText.startsWith(filterStr) || codeValueText.startsWith(filterStr)) {//过滤掉不需要的项  
                	results.add(mdata.get(i));  
                }  
                break;   

            default:  
                break;  
            }  

        }  
           
		return results;
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
}
