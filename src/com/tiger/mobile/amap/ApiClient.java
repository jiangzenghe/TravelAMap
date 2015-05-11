package com.tiger.mobile.amap;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.mime.TypedFile;

import com.tiger.mobile.amap.entity.ScenicAreaJson;
import com.tiger.mobile.amap.entity.ScenicDetailJson;
import com.tiger.mobile.amap.entity.ScenicIntroductionJson;
import com.tiger.mobile.amap.entity.ScenicTransportJson;


public class ApiClient {
    private static IuuApiInterface sTwitchTvService;

    public static IuuApiInterface getIuuApiClient() {
        if (sTwitchTvService == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://192.168.1.104:8080/api")
                    .build();
            sTwitchTvService = restAdapter.create(IuuApiInterface.class);
        }

        return sTwitchTvService;
    }

//    public  static void uploadFile(File file,String imagename) {
//
//        String mimeType = "image/jpg";
//        TypedFile fileToSend = new TypedFile(mimeType, file);
//        IuuApiInterface fileWebService =  getIuuApiClient();
//        fileWebService.upload(fileToSend,imagename, Callback<Response> callback);
//     }

    public interface IuuApiInterface {

        /**
         * 首页
         * @param limit
         * @param offset
         * @param callback
         */
        @GET("/home/pagelist.do")
        void getScenicListbyPage(@Query("limit") int limit, @Query("offset") int offset, Callback<ArrayList<ScenicAreaJson>> callback);

        /**
         * 详情
         * @param scenicId
         * @param callback
         */
        @GET("/detail/detail.do")
        void queryScenicDetail(@Query("scenicId") String scenicId,Callback<ScenicDetailJson> callback);

        /**
         * 简介
         * @param scenicId
         * @param callback
         */
        @GET("/detail/intro.do")
        void queryScenicIntro(@Query("scenicId") String scenicId,Callback<ScenicIntroductionJson> callback);

        /**
         * 交通
         * @param scenicId
         * @param callback
         */
        @GET("/detail/transport.do")
        void queryScenicTransport(@Query("scenicId") String scenicId,Callback<ScenicTransportJson> callback);

       @Multipart
        @Headers({"Content-Type: image/jpeg"})
        @POST("/image/{imageName}/upload")
        void upload(@Part("fileContent") TypedFile file, @Path("imageName") String imageName, Callback<Response> callback);
    }
}
