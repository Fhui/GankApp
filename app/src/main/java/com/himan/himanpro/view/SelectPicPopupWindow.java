package com.himan.himanpro.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.himan.himanpro.R;

/**
 * Created by HIMan on 16/7/28.
 */
public class SelectPicPopupWindow extends PopupWindow {

    private Button btn_cancle;
    private Button btn_take;
    private Button btn_photos;
    private View popView;
    private LinearLayout pop_layout;
    private View.OnClickListener clickListener;

    public SelectPicPopupWindow(Activity context, View.OnClickListener clickListener) {
        super(context);
        this.clickListener = clickListener;
        LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        popView = inflate.inflate(R.layout.pop_layout, null);
        initView();
        this.setContentView(popView);
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
//        this.setAnimationStyle(R.style.AnimBottom);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);
        popView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = popView.findViewById(R.id.pop_layout).getTop();
                int y=(int) event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    public void initView(){
        pop_layout = (LinearLayout) popView.findViewById(R.id.pop_layout);
        btn_cancle = (Button) popView.findViewById(R.id.btn_cancle);
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        btn_take = (Button) popView.findViewById(R.id.btn_take);
        btn_take.setOnClickListener(clickListener);
        btn_photos = (Button) popView.findViewById(R.id.btn_photos);
        btn_photos.setOnClickListener(clickListener);
    }
}
