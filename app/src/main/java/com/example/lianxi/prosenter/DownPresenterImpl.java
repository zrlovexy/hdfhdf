package com.example.lianxi.prosenter;

import com.example.lianxi.bean.DownBean;
import com.example.lianxi.callback.DownCallBack;
import com.example.lianxi.model.DownModel;
import com.example.lianxi.view.DownView;

import okhttp3.RequestBody;

public class DownPresenterImpl implements DownProsenter, DownCallBack {
    private DownModel downModel;
    private DownView downView;

    public DownPresenterImpl(DownModel downModel, DownView downView) {
        this.downModel = downModel;
        this.downView = downView;
    }

    @Override
    public void getData(RequestBody requestBody) {
        if (downModel != null){
            downModel.getData(requestBody,this);
        }
    }

    @Override
    public void downSuccess(DownBean downBean) {
        if (downView != null){
            downView.downSuccess(downBean);
        }
    }

    @Override
    public void downFail(String error) {
        if (downView != null){
            downView.downFail(error);
        }
    }

}
