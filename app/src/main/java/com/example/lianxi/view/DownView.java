package com.example.lianxi.view;

import com.example.lianxi.bean.DownBean;

public interface DownView {
    void downSuccess(DownBean downBean);
    void downFail(String error);
}
