package com.example.lianxi.model;

import com.example.lianxi.api.MyServer;
import com.example.lianxi.bean.ChannelBean;
import com.example.lianxi.bean.DownBean;
import com.example.lianxi.callback.ChannelCallBack;
import com.example.lianxi.callback.DownCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DownModelImpl implements DownModel{


    @Override
    public void getData(RequestBody requestBody, final DownCallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        MyServer myServer = retrofit.create(MyServer.class);

        Observable<DownBean> call = myServer.posDownData(requestBody);

        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DownBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DownBean downBean) {
                        if (downBean == null){
                            callBack.downFail("数据错误");
                        }else {
                            if (downBean.getCode() == 0){
                                callBack.downSuccess(downBean);
                            }else {
                                callBack.downFail("数据错误");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.downFail("数据错误");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
