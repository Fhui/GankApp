package com.himan.himanpro.net;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.himan.himanpro.domain.ErrorResponse;
import com.himan.himanpro.utils.LogUtils;
import com.himan.himanpro.utils.MD5Utils;
import com.himan.himanpro.utils.framework.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 *
 * 自定义GsonRequestForJson
 * 默认post请求Json字符串
 * Created by Ttg on 2016/4/22 0022.
 */
public class CustomJsonRequest<T> extends JsonRequest<T> {

    private final Gson gson;

    private final Class<? extends T> clazz;


    private final Response.Listener<T> mListener;

    private boolean isCache;

    public CustomJsonRequest(int method, String url, String requestBody,
                             Class<? extends T> clazz, Response.Listener<T> listener,
                             Response.ErrorListener errorListener, boolean isCache) {
        super(method, url, requestBody, listener, errorListener);
        gson = new Gson();
        this.clazz = clazz;
        this.mListener = listener;
        this.isCache = isCache;
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            T result = null;
            try {
                    result = gson.fromJson(json, clazz);
                if (isCache) {
                    LogUtils.i("Save response to local!");
                    FileCopyUtils.copy(response.data, new File(GetContext.getContext().getCacheDir(), "" + MD5Utils.encode(getUrl())));
                }
            } catch (JsonSyntaxException e) {
                result = (T) gson.fromJson(json, ErrorResponse.class);
            }  catch (IOException e) {
                e.printStackTrace();
            }
            return Response.success(result,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
           return Response.error(new ParseError(e));
        }catch (JsonSyntaxException e){
            return Response.error(new ParseError(e));
        }
    }
}
