package com.example.lianxi.view;

import com.example.lianxi.bean.ChannelBean;

public interface ChannelView {
    void channerSuccess(ChannelBean channelBean);
    void channerFail(String error);
}
