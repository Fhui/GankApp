package com.himan.recycledemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HIMan on 16/7/12.
 */
public class RecycleDemoAdapter extends RecyclerView.Adapter<RecycleViewHolder> {

    List<String> listDatas;
    List<Integer> listHeights;
    private LayoutInflater layoutInflater;


    public RecycleDemoAdapter(Context context, List<String> listDatas){
        this.listDatas = listDatas;
        layoutInflater = layoutInflater.from(context);

        listHeights = new ArrayList<>();
        for (int i = 0; i < listDatas.size(); i++)
        {
            listHeights.add( (int) (100 + Math.random() * 300));
        }
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecycleViewHolder holder = new RecycleViewHolder(layoutInflater.inflate(R.layout.recycle_holder_items, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        ViewGroup.LayoutParams lp = holder.textView.getLayoutParams();
        lp.height = listHeights.get(position);
        holder.textView.setLayoutParams(lp);
        holder.textView.setText(listDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return listDatas.size();
    }
}
