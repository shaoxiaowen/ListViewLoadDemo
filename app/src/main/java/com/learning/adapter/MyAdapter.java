package com.learning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.learning.listviewloaddemo.R;
import com.learning.pojo.ListViewItem;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    List<ListViewItem> itemList;
    LayoutInflater inflater;


    public MyAdapter(Context context,List<ListViewItem> itemList) {
        this.itemList = itemList;
        this.inflater=LayoutInflater.from(context);
    }

    public void onDateChange(List<ListViewItem> items){
        this.itemList=items;
        this.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ListViewItem listViewItem = itemList.get(position);
        View view = inflater.inflate(R.layout.item_layout, null);
        TextView tv_name = view.findViewById(R.id.item_name);
        TextView tv_des = view.findViewById(R.id.item_des);
        TextView tv_info = view.findViewById(R.id.item_info);

        tv_name.setText(listViewItem.getName());
        tv_des.setText(listViewItem.getDes());
        tv_info.setText(listViewItem.getInfo());

        return view;
    }
}
