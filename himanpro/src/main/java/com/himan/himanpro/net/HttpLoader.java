package com.himan.himanpro.net;

import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.himan.himanpro.domain.RBResponse;
import com.himan.himanpro.utils.LogUtils;
import com.himan.himanpro.utils.MD5Utils;
import com.himan.himanpro.utils.framework.FileCopyUtils;

import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 网络请求核心类
 * @time 2016/4/21 0021 14:58
 */
public class HttpLoader {

    //保存请求队列中的请求, key请求码
    private static final HashMap<Integer, Request> mInFlightRequests = new HashMap<>();
    public static RequestQueue mRequestQueue = Volley.newRequestQueue(GetContext.getContext());
    //图片缓存ImageLoader
    private static ImageLoader mImageLoader = new ImageLoader(mRequestQueue, new LevelTwoLruBitmapCache(GetContext.getContext()));

   /**
    * 以Get的方式发送请求
    * @time 2016/4/22 0022 14:10
    */
    public static Request get(String url, Class<? extends RBResponse> clazz, final int requestCode, final ResponseListener listener, boolean isCache) {
        return request(Request.Method.GET, url, null, clazz, requestCode, listener, isCache);
    }


    /**
     * 以post发送请求(Map参数)
     * @time 2016/4/22 0022 14:12
     */
    public static Request post(String url, Map<String, String> params, Class<? extends RBResponse> clazz, final int requestCode, final ResponseListener listener, boolean isCache) {
        return request(Request.Method.POST, url, params, clazz, requestCode, listener, isCache);
    }


    /**
     * 发送GsonRequest请求
     * @time 2016/4/22 0022 14:15
     */
    private static Request request(int method, String url, Map<String, String> params , Class<? extends RBResponse> clazz, int requestCode, ResponseListener listener, boolean isCache) {
        Request request = mInFlightRequests.get(requestCode);
        if (request == null) {
                request = makeGsonRequest(method, url + buildParams(params), null, clazz, requestCode, listener, isCache);
            //首先尝试解析本地缓存供界面显示，然后再发起网络请求
            tryLoadCacheResponse(request, requestCode, listener);
            LogUtils.d("Handle request by network!");
            return addRequest(request, requestCode);
        } else {
            LogUtils.i("Hi guy,the request (RequestCode is " + requestCode + ")  is already in-flight , So Ignore!");
            return request;
        }
    }

    /**
     * JsonRequest post请求 请求体 json
     * @time 2016/4/23 0023 19:06
     */
    public static Request postJson(String url, JSONObject params, Class<? extends RBResponse> clazz, int requestCode, ResponseListener listener, boolean isCache){
        return requestForJson(Request.Method.POST, url, params, clazz, requestCode, listener, isCache);
    }

    /**
     * Gson封装JsonRequest
     * @time 2016/4/23 0023 19:03
     */
    private static Request requestForJson(int method, String url, JSONObject params, Class<? extends RBResponse> clazz, int requestCode, ResponseListener listener, boolean isCache){
        Request request = mInFlightRequests.get(requestCode);
        if(request == null){
            request = makeCustomJsonRequest(method, url, params, clazz, requestCode, listener, isCache);
            tryLoadCacheResponse(request, requestCode, listener);
            return addRequest(request, requestCode);
        }
        return request;
    }


    /**
     * 创建一个JsonRequest
     * @time 2016/4/23 0023 19:02
     */
    private static Request makeCustomJsonRequest(int method, String url, JSONObject jsonObject, Class<? extends RBResponse> clazz, int requestCode, ResponseListener listener, boolean isCache){
        HttpListener httpListener = new HttpListener(listener, requestCode, mInFlightRequests);
        String json = jsonObject.toString();
        CustomJsonRequest jsonRequest = new CustomJsonRequest<RBResponse>(method, url, json, clazz, httpListener, httpListener, isCache){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getRequestHeaders();
            }
        };
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy());
        return jsonRequest;
    }


    /**
     * (预留)生成公共头信息
     *
     * @return
     */
    private static Map<String, String> getRequestHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        return headers;
    }




    /**
     * 将请求添加到请求队列中
     * @time 2016/4/22 0022 14:16
     */
    private static Request addRequest(Request<?> request, int requestCode) {
        if (mRequestQueue != null && request != null) {
            mRequestQueue.add(request);
        }
        mInFlightRequests.put(requestCode, request);
        mRequestQueue.start();
        return request;
    }

    /**
     * 取消请求
     * @time 2016/4/22 0022 14:17
     */
    public static void cancelRequest(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);//从请求队列中取消对应的任务
        }
        //同时在mInFlightRequests删除保存所有TAG匹配的Request
        Iterator<Map.Entry<Integer, Request>> it = mInFlightRequests.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Request> entry = it.next();
            Object rTag = entry.getValue().getTag();
            if (rTag != null && rTag.equals(tag)) {
                it.remove();
            }
        }
    }

    /**
     * 获取图片缓存
     * @time 2016/4/22 0022 14:17
     */
    public static ImageLoader getImageLoader() {
        return mImageLoader;
    }
    

    /**
     * 读取缓存文件
     * @time 2016/4/22 0022 14:19
     */
    private static void tryLoadCacheResponse(Request request, int requestCode, ResponseListener listener) {
        LogUtils.d("Try to  load cache response first !");
        if (listener != null && request != null) {
            try {
                //获取缓存文件
                File cacheFile = new File(GetContext.getContext().getCacheDir(), "" + MD5Utils.encode(request.getUrl()));
                StringWriter sw = new StringWriter();
                //读取缓存文件
                FileCopyUtils.copy(new FileReader(cacheFile), sw);
                if (request instanceof GsonRequest) {
                    //如果是GsonRequest，那么解析出本地缓存的json数据为GsonRequest
                    GsonRequest gr = (GsonRequest) request;
                    RBResponse response = (RBResponse) gr.getGson().fromJson(sw.toString(), gr.getClazz());
                    //传给onResponse，让前面的人用缓存数据
                    listener.onGetResponseSuccess(requestCode, response);
                    LogUtils.d("Load cache response success !");
                }
            } catch (Exception e) {
                LogUtils.w("No cache response ! " + e.getMessage());
            }
        }
    }

    
    /**
     * 遍历map，拼接网络请求url
     * @time 2016/4/22 0022 14:20
     */
    private static String buildParams(Map<String, String> params) {
        StringBuilder buffer = new StringBuilder();
        if (params != null) {
            buffer.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
                    continue;
                }
                try {
                    buffer.append(URLEncoder.encode(key, "UTF-8"));
                    buffer.append("=");
                    buffer.append(URLEncoder.encode(value, "UTF-8"));
                    buffer.append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

        }
        String str = buffer.toString();
        if (str.length() > 1 && str.endsWith("&")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    /**
     * 构建一个GsonRequest
     * @time 2016/4/21 0021 14:33
     */
    private static Request makeGsonRequest(int method, String url, Map<String, String> params, Class<? extends RBResponse> clazz, int requestCode, ResponseListener listener, boolean isCache) {
        HttpListener httpListener = new HttpListener(listener, requestCode, mInFlightRequests);
        GsonRequest gsonRequest = new GsonRequest<RBResponse>(method, url, params, clazz, httpListener, httpListener, isCache);
        gsonRequest.setRetryPolicy(new DefaultRetryPolicy());//设置默认的重试机制，超时时间，重试次数，重试因子等
        return gsonRequest;
    }
}
