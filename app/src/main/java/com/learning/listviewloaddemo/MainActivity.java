package com.learning.listviewloaddemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.learning.adapter.MyAdapter;
import com.learning.pojo.ListViewItem;
import com.learning.view.LoadListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements LoadListView.ILoadListener{

    List<ListViewItem> itemList = new ArrayList<>();
    MyAdapter adapter;
    LoadListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
        showListView();

    }

    private void showListView() {
        if (adapter == null) {
            adapter = new MyAdapter(this, itemList);
            listView = findViewById(R.id.mylistview);
            listView.setInterface(this);
            listView.setAdapter(adapter);
        }else {
            adapter.onDateChange(itemList);
        }
    }

    private void getData() {
        for (int i = 0; i < 10; i++) {
            ListViewItem item = new ListViewItem();
            item.setName("item" + i);
            item.setDes("listview学习");
            item.setInfo("listview学习");
            itemList.add(item);

        }
    }

    private void getLoadData() {
        for (int i = 0; i < 2; i++) {
            ListViewItem item = new ListViewItem();
            item.setName("更多程序");
            item.setDes("listview学习");
            item.setInfo("listview学习");
            itemList.add(item);

        }
    }

    @Override
    public void onLoad() {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //获取更多数据
                getLoadData();
                //通知listview，更新数据显示
                showListView();
                //通知listview  加载完毕
                listView.loadComplete();
            }
        },2000);

    }
}
