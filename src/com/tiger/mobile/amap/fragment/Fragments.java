package com.tiger.mobile.amap.fragment;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tiger.mobile.amap.ApiClient;
import com.tiger.mobile.amap.R;
import com.tiger.mobile.amap.activity.ScenicAreaActivity;
import com.tiger.mobile.amap.entity.Recommend;
import com.tiger.mobile.amap.entity.ScenicDetailJson;

public class Fragments extends Fragment {

    private static final  String HOST="http://192.168.1.107/";

    private ScenicSmallAdaptor adaptor;
    private ArrayList<com.tiger.mobile.amap.entity.Recommend> data;
    private RecyclerView recyclerView ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment, container, false);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.sdv_image);
        Intent intent=getActivity().getIntent();
        String url=intent.getStringExtra("URL");
        String scenicId=intent.getStringExtra("scenicId");
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_scincsmall);
        initData(scenicId);
        simpleDraweeView.setImageURI(Uri.parse(url));
        return view;
    }

    private void initData( String scenicId) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        adaptor = new ScenicSmallAdaptor(new ArrayList<Recommend>());
        recyclerView.setAdapter(adaptor);
        ApiClient.getIuuApiClient().queryScenicDetail(scenicId, new Callback<ScenicDetailJson>() {
            @Override
            public void success(ScenicDetailJson scenicDetailJson, Response response) {
              //  LogUtil.v(scenicDetailJson.toString());
                adaptor.data.addAll(scenicDetailJson.getRecommendScenicList());
                adaptor.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }


    class  ScenicSmallAdaptor extends  RecyclerView.Adapter<MyViewHolder>{
        private ArrayList<Recommend> data;

        public ScenicSmallAdaptor(ArrayList<Recommend> data) {
            this.data = data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(getActivity()).inflate(R.layout.item_scenicdetail_small,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            final Recommend recommend =  data.get(position);
            holder.tv_ecommend_name.setText(data.get(position).getName());
            holder.simpleDraweeView_recommend_pic.setImageURI(Uri.parse(HOST +recommend.getImageUrl()));
            holder.simpleDraweeView_recommend_pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //     Log.v("----tule----", scenicAreaJson.getId());
                    Intent intent = new Intent(getActivity(),ScenicAreaActivity.class);
                    intent.putExtra("URL",HOST + recommend.getImageUrl());
                    intent.putExtra("scenicId", recommend.getIntentLink());
                    intent.putExtra("scenicName",recommend.getName());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            if (data!=null)
                return data.size();
            return 0;
        }
    }

    /**
     * 推荐等ViewHolder
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_ecommend_name;
        private SimpleDraweeView simpleDraweeView_recommend_pic;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_ecommend_name = (TextView) itemView.findViewById(R.id.item_tv_scenicdetailname);
            simpleDraweeView_recommend_pic = (SimpleDraweeView) itemView.findViewById(R.id.item_smp_scenicdetail);
        }
    }

}
