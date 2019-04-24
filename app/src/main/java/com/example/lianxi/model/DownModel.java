package com.example.lianxi.model;

import com.example.lianxi.callback.DownCallBack;

import okhttp3.RequestBody;

public interface DownModel {
    void getData(RequestBody requestBody, DownCallBack callBack);
}
