package com.shj.shjtest.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shj.shjtest.R;

public class PullToRefreshActivity extends AppCompatActivity {

    private PullToRefreshListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pull_listview);
        initComponent();
        initListener();
    }

    private void initListener() {
        //上拉刷新
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        //到底的监听
        listview.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {

            }
        });
    }

    private void initComponent() {
        listview = ((PullToRefreshListView) findViewById(R.id.pull_refresh_list));
    }
}
