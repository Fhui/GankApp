package com.himan.himanpro.net;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.himan.himanpro.domain.ErrorResponse;
import com.himan.himanpro.utils.LogUtils;
import com.himan.himanpro.utils.MD5Utils;
import com.himan.himanpro.utils.framework.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 自定义Request, 通过gson把json格式的response解析成bean对象，另外请求自带缓存功能
 * @time 2016/4/22 0022 14:31
 */
public class GsonRequest<T> extends Request<T> {
    private final Gson gson = new Gson();
    private final Class<? extends T> clazz;
    private final Map<String, String> params;
    private final Response.Listener<T> listener;
    private boolean isCache;


    /**
     * 初始化
     * @time 2016/4/22 0022 14:31
     */
    public GsonRequest(int method, String url, Map<String, String> params, Class<? extends T> clazz,
                       Response.Listener<T> listener, Response.ErrorListener errorListener, boolean isCache) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.params = params;
        this.listener = listener;
        this.isCache = isCache;
    }


    public Gson getGson() {
        return gson;
    }

    public Class<? extends T> getClazz() {
        return clazz;
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            Log.i("GsonRequest", "content"+json);

            T result = null;
            try {
                result = gson.fromJson(json, clazz);    //解析json
                if (isCache) {
                    //如果解析成功，并且需要缓存则将json字符串缓存到本地
                    LogUtils.i("Save response to local!");
                    FileCopyUtils.copy(response.data, new File(GetContext.getContext().getCacheDir(), "" + MD5Utils.encode(getUrl())));
                }
            } catch (JsonSyntaxException e) {
                result = (T) gson.fromJson(json, ErrorResponse.class);//解析失败，按规范错误响应解析
            }  catch (IOException e) {
                e.printStackTrace();
            }
            return Response.success(
                    result,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}