package com.himan.himanpro.utils;

import android.content.Context;

import com.himan.himanpro.view.CustomDialog;

/**
 * Created by HIMan on 16/7/7.
 */
public class ProgressUtils {


    public static void showNoteBackProgressDialog(Context context, String msg){
        CustomDialog.dismissDialog();
        CustomDialog.show(context, msg, false, null);
    }

    public static void showProgressDialog(Context context, String msg){
        CustomDialog.dismissDialog();
        CustomDialog.show(context, msg, true, null);
    }

    public static void dismissDialog(){
        CustomDialog.dismissDialog();
    }

}
