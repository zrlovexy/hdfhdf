package com.example.lianxi;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.lianxi.adapter.MyAdapter;
import com.example.lianxi.bean.ChannelBean;
import com.example.lianxi.fragment.DownFragment;
import com.example.lianxi.model.ChannelModelImpl;
import com.example.lianxi.prosenter.ChannelProsenter;
import com.example.lianxi.prosenter.ChannelProsenterImpl;
import com.example.lianxi.view.ChannelView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ChannelView {

    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<Fragment> list;
    private ChannelProsenter prosenter;
    private MyAdapter my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();


    }

    private void initData() {
        prosenter = new ChannelProsenterImpl(new ChannelModelImpl(),this);
        prosenter.getData();
    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);

        list = new ArrayList<>();
    }

    @Override
    public void channerSuccess(ChannelBean channelBean) {
        List<ChannelBean.DataBean.NewsChannelListBean> newsChannelList = channelBean.getData().getNewsChannelList();
        for (int i = 0;i <newsChannelList.size();i++){
            tab.addTab(tab.newTab().setText(newsChannelList.get(i).getChannelName()));
            list.add(new DownFragment(newsChannelList.get(i).getChannelId()));
        }
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

        my = new MyAdapter(getSupportFragmentManager(), list);
        vp.setAdapter(my);
    }

    @Override
    public void channerFail(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }
}
