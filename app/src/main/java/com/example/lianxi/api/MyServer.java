package com.example.lianxi.api;

import com.example.lianxi.bean.ChannelBean;
import com.example.lianxi.bean.DownBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MyServer {

    //https://www.firstgainfo.com/firstga/app/news/listNewsChannel

    //https://www.firstgainfo.com/firstga/app/news/downListNews

    public String Url = "https://www.firstgainfo.com/firstga/app/news/";

    @POST("listNewsChannel")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Observable<ChannelBean> postChannelData();

    @POST("downListNews")
    @Headers("Content-Type:application/json")
    Observable<DownBean> posDownData(@Body RequestBody requestBody);
}
