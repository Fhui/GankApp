package com.himan.himanpro.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.himan.himanpro.R;

/**
 * Created by HIMan on 16/7/7.
 */
public class CustomDialog extends Dialog {


    public static CustomDialog dialog;

    public CustomDialog(Context context) {
        super(context, R.style.custom_progress);
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        CircleProgress circleProgress = (CircleProgress) findViewById(R.id.circle_prog);
        circleProgress.setRadius(3.5f);
        circleProgress.startAnim();
    }


    public void setMessage(CharSequence message) {
        if (message != null && message.length() > 0) {
            findViewById(R.id.tv_dialog_msg).setVisibility(View.VISIBLE);
            TextView tv_dialog_msg = (TextView) findViewById(R.id.tv_dialog_msg);
            tv_dialog_msg.setText(message);
            tv_dialog_msg.invalidate();
        }
    }

    public static CustomDialog show(Context context, CharSequence message, boolean cancelable, OnCancelListener cancelListener) {
        dialog = new CustomDialog(context, R.style.custom_progress);
        dialog.setTitle("");
        dialog.setContentView(R.layout.custom_dialog);
        if (message.length() == 0 || message == null){
            dialog.findViewById(R.id.tv_dialog_msg).setVisibility(View.GONE);
        }else{
            TextView tv_dialog = (TextView) dialog.findViewById(R.id.tv_dialog_msg);
            tv_dialog.setText(message);
        }
        // 按返回键是否取消
        dialog.setCancelable(cancelable);
        // 监听返回键处理
        dialog.setOnCancelListener(cancelListener);
        // 设置居中
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        // 设置背景层透明度
        lp.dimAmount = 0.2f;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
        return dialog;
    }

    public static void dismissDialog(){
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }




}
