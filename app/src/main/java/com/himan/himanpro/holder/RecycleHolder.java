package com.himan.himanpro.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.himan.himanpro.R;

/**
 * Created by HIMan on 16/7/11.
 */
public class RecycleHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView textView;
    public RelativeLayout rl_show_img;

    public RecycleHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.recycle_img);
        textView = (TextView) itemView.findViewById(R.id.tv_show_time_recycle);
        rl_show_img = (RelativeLayout) itemView.findViewById(R.id.rl_show_img);
    }


}
