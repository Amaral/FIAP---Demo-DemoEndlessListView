package br.com.felipeamaral.demoendlesslistview.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.List;

import br.com.felipeamaral.demoendlesslistview.adapter.EndlessAdapter;
import br.com.felipeamaral.demoendlesslistview.listener.EndlessListener;

public class EndlessListView extends ListView implements AbsListView.OnScrollListener {

    private View footer;
    private boolean isLoading;
    private EndlessListener endlessListener;
    private EndlessAdapter endlessAdapter;


    public EndlessListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setOnScrollListener(this);
    }
    public EndlessListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnScrollListener(this);
    }
    public EndlessListView(Context context) {
        super(context);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(getAdapter() == null) {
            return;
        }
        if(getAdapter().getCount() == 0) {
            return;
        }

        int valor = visibleItemCount + firstVisibleItem;
        if(valor >= totalItemCount && !isLoading) {
            this.addFooterView(footer);
            isLoading = true;
            endlessListener.loadData();
        }
    }

    public void setEndlessListener(EndlessListener endlessListener) {
        this.endlessListener = endlessListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {}

    public void setLoadingView(int resId) {
        LayoutInflater inflater = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        footer = inflater.inflate(resId, null);
        this.addFooterView(footer);
    }
    public void setAdapter(EndlessAdapter adapter) {
        super.setAdapter(adapter);
        this.endlessAdapter = adapter;
        this.removeFooterView(footer);
    }

    public void addNewData(List<String> data) {
        this.removeFooterView(footer);
        endlessAdapter.addAll(data);
        endlessAdapter.notifyDataSetChanged();
        isLoading = false;
    }

}
