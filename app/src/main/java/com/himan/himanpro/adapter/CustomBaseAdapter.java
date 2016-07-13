package com.himan.himanpro.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by HIMan on 16/7/6.
 */
public abstract class CustomBaseAdapter<T> extends BaseAdapter {


    private List<T> list;

    protected Context context;

    public CustomBaseAdapter(Context context) {
        init(context, new ArrayList());
    }

    public CustomBaseAdapter(Context context, List list) {
        init(context, list);
    }

    private void init(Context context, List list) {
        this.list = list;
        this.context = context;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public void clear() {
        this.list.clear();
        notifyDataSetChanged();
    }

    public void addAll(List list) {
        if (list != null) {
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return setConvertView(position, convertView, parent);
    }


    public abstract View setConvertView(int position, View convertView, ViewGroup parent);


}
