package com.example.lianxi.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lianxi.R;
import com.example.lianxi.adapter.DownAdapter;
import com.example.lianxi.bean.DownBean;
import com.example.lianxi.model.DownModelImpl;
import com.example.lianxi.prosenter.DownPresenterImpl;
import com.example.lianxi.prosenter.DownProsenter;
import com.example.lianxi.view.DownView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class DownFragment extends Fragment implements DownView, XRecyclerView.LoadingListener {


    private XRecyclerView xl;
    private ArrayList<DownBean.DataBean.NewListBean> list;
    private DownAdapter my;
    private DownProsenter prosenter;

    private String userId = "d56ea66e7ee741f498ca51242c8c6394";
    private String channelId = "";
    private int cursor = 0;

    @SuppressLint("ValidFragment")
    public DownFragment(String channelId) {
        this.channelId = channelId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_down, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        prosenter = new DownPresenterImpl(new DownModelImpl(),this);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId",userId);
            jsonObject.put("channelId",channelId);
            jsonObject.put("cursor",cursor + "");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType parse = MediaType.parse("application/json");

        RequestBody requestBody = RequestBody.create(parse, jsonObject.toString());

        prosenter.getData(requestBody);
    }

    private void initView(View inflate) {
        xl = (XRecyclerView) inflate.findViewById(R.id.xl);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        xl.setLayoutManager(layoutManager);
        xl.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        list = new ArrayList<>();

        my = new DownAdapter(getActivity(), list);
        xl.setAdapter(my);

        xl.setLoadingListener(this);




    }

    @Override
    public void downSuccess(DownBean downBean) {
        if (cursor == 0){
            list.clear();
        }
        ++cursor;
        list.addAll(downBean.getData().getNewList());
        my.setList(list);
        my.notifyDataSetChanged();
        if (downBean != null){
            xl.loadMoreComplete();
            xl.refreshComplete();
        }
    }

    @Override
    public void downFail(String error) {
        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
         cursor = 0;


    }

    @Override
    public void onLoadMore() {

    }
}
