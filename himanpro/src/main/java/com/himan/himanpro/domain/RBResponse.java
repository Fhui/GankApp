package com.himan.himanpro.domain;

/**
 * 用于封装服务器返回的json信息。RBResponse 包含公共的response字段。
 * @time 2016/4/22 0022 14:34
 */
public class RBResponse {

    protected String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setIsCache(Boolean isCache){

    }


}
