package com.himan.recycledemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by HIMan on 16/7/12.
 */
public class RecycleViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;
    public ImageView imageView;


    public RecycleViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.iv_pic);
        textView = (TextView) itemView.findViewById(R.id.tv_title);
    }
}
