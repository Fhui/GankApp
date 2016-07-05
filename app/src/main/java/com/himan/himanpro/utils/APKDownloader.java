package com.himan.himanpro.utils;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;


import com.himan.himanpro.net.GetContext;
import com.himan.himanpro.utils.framework.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * APK下载器，调用系统DownloadManager发起下载任务。下载成功自动发起安装请求。
 */
public class APKDownloader {


    /**
     * 下载完成的广播接收者
     */
    protected static DownloadCompleteReceiver receiver = new DownloadCompleteReceiver();
    private static APKDownloader mInstance;
    /**
     * 保存已下载的任务
     */
    private static List<Long> ids = new ArrayList<Long>();
    /**
     * 系统下载服务
     */
    private static DownloadManager DM = (DownloadManager) GetContext.getContext().getSystemService(Context.DOWNLOAD_SERVICE);


    private APKDownloader() {
    }

    /**
     * 获取APKDownloader实例，同时注册DOWNLOAD_COMPLETE，APKDownloader不再使用的时候记得调用{@link #release()}.
     *
     * @return APKDownloader对象
     */
    public static APKDownloader getInstance() {
        if (mInstance == null) {
            mInstance = new APKDownloader();
            IntentFilter intentfilter = new IntentFilter();
            intentfilter.addAction("android.intent.action.DOWNLOAD_COMPLETE");
            GetContext.getContext().registerReceiver(receiver, intentfilter);
        }
        return mInstance;
    }

    /**
     * 执行下载任务
     *
     * @param apkUri  apk的下载地址
     * @param apkName 要保存的apk名字
     * @return 下载任务的ID
     */
    public long downloadAPK(String apkUri, String apkName) {
        Assert.notNull(apkUri);
        Assert.notNull(apkName);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(apkUri));
        request.setMimeType("application/vnd.android.package-archive");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, apkName);
        long id = DM.enqueue(request);
        ids.add(id);
        return id;
    }

    /**
     * 释放资源，注销广播
     */
    public void release() {
        GetContext.getContext().unregisterReceiver(receiver);
        mInstance = null;

    }

    /*
     * 接受下载完成后的intent
     */
    static class DownloadCompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (ids.contains(id)) {//如果本应用发起的下载请求完成了才发起安装请求
                ids.remove(id);//删除已保存的ID
                LogUtils.d(intent.toString());
                Intent install = new Intent(Intent.ACTION_VIEW);
                Uri downloadFileUri = DM.getUriForDownloadedFile(id);
                install.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
                install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(install);
            }
        }
    }
}
