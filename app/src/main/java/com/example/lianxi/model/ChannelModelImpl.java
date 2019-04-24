package com.example.lianxi.model;

import android.util.Log;

import com.example.lianxi.api.MyServer;
import com.example.lianxi.bean.ChannelBean;
import com.example.lianxi.callback.ChannelCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;

public class ChannelModelImpl implements ChannelModel{
    @Override
    public void getData(final ChannelCallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        MyServer myServer = retrofit.create(MyServer.class);

        Observable<ChannelBean> call = myServer.postChannelData();

        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChannelBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ChannelBean channelBean) {
                        if (channelBean == null){
                            callBack.channerFail("数据错误");
                        }else {
                            if (channelBean.getCode() == 0){
                                callBack.channerSuccess(channelBean);
                            }else {
                                callBack.channerFail("数据错误");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.channerFail("数据错误");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
