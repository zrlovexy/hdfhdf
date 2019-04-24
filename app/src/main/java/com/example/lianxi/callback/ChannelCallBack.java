package com.example.lianxi.callback;

import com.example.lianxi.bean.ChannelBean;

public interface ChannelCallBack {
    void channerSuccess(ChannelBean channelBean);
    void channerFail(String error);
}
