package com.example.lianxi.prosenter;

import com.example.lianxi.bean.ChannelBean;
import com.example.lianxi.callback.ChannelCallBack;
import com.example.lianxi.model.ChannelModel;
import com.example.lianxi.view.ChannelView;

public class ChannelProsenterImpl implements ChannelProsenter, ChannelCallBack {
    private ChannelModel channelModel;
    private ChannelView channelView;

    public ChannelProsenterImpl(ChannelModel channelModel, ChannelView channelView) {
        this.channelModel = channelModel;
        this.channelView = channelView;
    }

    @Override
    public void getData() {
        if (channelModel != null){
            channelModel.getData(this);
        }
    }

    @Override
    public void channerSuccess(ChannelBean channelBean) {
        if (channelView != null){
            channelView.channerSuccess(channelBean);
        }
    }

    @Override
    public void channerFail(String error) {
        if (channelView != null){
            channelView.channerFail(error);
        }
    }
}
