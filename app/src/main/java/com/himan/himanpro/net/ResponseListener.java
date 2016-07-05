package com.himan.himanpro.net;

import com.android.volley.VolleyError;
import com.himan.himanpro.domain.RBResponse;

/**
 * 成功获取服务器响应监听
 * @time 2016/4/22 0022 14:32
 */
public interface ResponseListener {


    /**
     * 成功监听
     * @time 2016/4/22 0022 14:23
     */
    void onGetResponseSuccess(int requestCode, RBResponse response);

    /**
     * 失败监听
     * @time 2016/4/22 0022 14:23
     */
    void onGetResponseError(int requestCode, VolleyError error);


}
