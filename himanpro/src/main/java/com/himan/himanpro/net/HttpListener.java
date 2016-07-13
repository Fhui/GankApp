package com.himan.himanpro.net;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.himan.himanpro.domain.ErrorResponse;
import com.himan.himanpro.domain.RBResponse;
import com.himan.himanpro.utils.LogUtils;

import java.util.HashMap;

/**
 * 对Volley请求的两种监听的封装，并执行一些默认操作，结果抛给供UI层注册的ResponseListener
 * @time 2016/4/22 0022 14:30
 */
public class HttpListener implements Response.Listener<RBResponse>, Response.ErrorListener{

    private ResponseListener listener;
    private int requestCode;
    private HashMap<Integer, Request> mInFlightRequests;

    public HttpListener(ResponseListener listener, int requestCode, HashMap<Integer, Request> mInFlightRequests) {
        this.listener = listener;
        this.requestCode = requestCode;
        this.mInFlightRequests = mInFlightRequests;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        LogUtils.w("----Request error from network!");
        error.printStackTrace();
        mInFlightRequests.remove(requestCode);
        if (listener != null) {
            listener.onGetResponseError(requestCode, error);
        }
    }

    @Override
    public void onResponse(RBResponse response) {
        LogUtils.w("----onResponse from network!");
        mInFlightRequests.remove(requestCode);
        if (response != null) {
            //执行通用处理，如果是服务器返回的ErrorResponse，直接提示错误信息并返回
            if ("error".equals(response.getResponse()) && response instanceof ErrorResponse) {
                ErrorResponse errorResponse = (ErrorResponse) response;
                return;
            }
            if (listener != null) {
                listener.onGetResponseSuccess(requestCode, response);
            }
        }
    }

}
