package com.himan.himanpro.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.himan.himanpro.R;
import com.himan.himanpro.domain.SortData;
import com.himan.himanpro.holder.RecycleHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HIMan on 16/7/12.
 */
public class CustomRecycleAdapter extends RecyclerView.Adapter<RecycleHolder> {


    List<SortData.ResultsBean> sortDataList;
    List<Integer> listHeights;
    private LayoutInflater layoutInflater;


    public CustomRecycleAdapter(Context context, List<SortData.ResultsBean> sortDataList) {
        this.sortDataList = sortDataList;
        layoutInflater = layoutInflater.from(context);

        listHeights = new ArrayList<>();
        for (int i = 0; i < sortDataList.size(); i++) {
            listHeights.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public RecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecycleHolder holder = new RecycleHolder(layoutInflater.inflate(R.layout.recycle_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecycleHolder holder, int position) {

        holder.textView.setText(sortDataList.get(position).getCreatedAt());

    }

    @Override
    public int getItemCount() {
        return sortDataList.size();
    }
}
