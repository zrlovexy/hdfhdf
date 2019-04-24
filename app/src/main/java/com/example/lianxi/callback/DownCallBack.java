package com.example.lianxi.callback;

import com.example.lianxi.bean.DownBean;

public interface DownCallBack {
    void downSuccess(DownBean downBean);
    void downFail(String error);
}
