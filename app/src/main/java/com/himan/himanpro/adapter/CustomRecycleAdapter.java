package com.himan.himanpro.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.himan.himanpro.R;
import com.himan.himanpro.activity.ContentActivity;
import com.himan.himanpro.activity.FuliContentActivity;
import com.himan.himanpro.domain.SortData;
import com.himan.himanpro.holder.RecycleHolder;
import com.himan.himanpro.mvp.presenter.LoadSortPresenter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HIMan on 16/7/12.
 */
public class CustomRecycleAdapter extends RecyclerView.Adapter<RecycleHolder> {

    private static final String SAVE_PIC_PATH= Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory().getAbsolutePath() : "/mnt/sdcard";//保存到SD卡
    private static final String SAVE_REAL_PATH = SAVE_PIC_PATH;//保存的确切位置
    List<SortData.ResultsBean> sortDataList;
    List<Integer> listHeights;
    private LayoutInflater layoutInflater;
    private LoadSortPresenter presenter;
    private Context context;
    private AdapterView.OnItemClickListener onItemClickListener;


    public CustomRecycleAdapter(Context context, LoadSortPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        layoutInflater = layoutInflater.from(context);
        listHeights = new ArrayList<>();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<SortData.ResultsBean> getSortDataList() {
        return sortDataList;
    }

    public void setSortDataList(List<SortData.ResultsBean> sortDataList) {
        this.sortDataList = sortDataList;
        for (int i = 0; i < sortDataList.size(); i++) {
            listHeights.add((int) (900 + Math.random() * 300));
        }
    }

    @Override
    public RecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecycleHolder holder = new RecycleHolder(layoutInflater.inflate(R.layout.recycle_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecycleHolder holder, final int position) {
        final Bitmap bitmap = presenter.loadSortImage(sortDataList.get(position).getUrl(), holder.imageView);
        holder.textView.setText(sortDataList.get(position).getCreatedAt().substring(0, 10));

        if (onItemClickListener != null) {
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, position + "---click", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, FuliContentActivity.class);
                    intent. putExtra("imgURL", sortDataList.get(position).getUrl());
                    intent. putExtra("title", "福利");
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return sortDataList.size();
    }


}
