package com.learning.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.learning.listviewloaddemo.R;

public class LoadListView extends ListView implements AbsListView.OnScrollListener {

    View footer;//底部布局
    int totalItemCount;//当前总的数量
    int lastVisibleItem;//最后一个可见的Item
    boolean isLoading;//正在加载
    ILoadListener iLoadListener;

    public LoadListView(Context context) {
        super(context);
        initView(context);
    }

    public LoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 添加底部加载提示布局到listview
     *
     * @param context
     */
    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        footer = inflater.inflate(R.layout.footer_layout, null);
        footer.setVisibility(View.GONE);
        this.addFooterView(footer);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //如果滚动到当前页面最低端，并且滚动停止
        if (totalItemCount == lastVisibleItem && scrollState == SCROLL_STATE_IDLE) {
            //加载更多数据
            if (!isLoading) {
                isLoading = true;
                footer.setVisibility(View.VISIBLE);
                iLoadListener.onLoad();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisibleItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

    /**
     * 加载完毕
     */
    public void loadComplete(){
        isLoading=false;
        footer.setVisibility(View.GONE);
    }

    public void setInterface(ILoadListener iLoadListener){
        this.iLoadListener=iLoadListener;

    }

    //加载更多数据的回调接口
    public interface ILoadListener{
        void onLoad();
    }
}
