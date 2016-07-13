package com.himan.himanpro.utils;


import com.google.gson.Gson;
import com.himan.himanpro.domain.RBResponse;

import org.json.JSONObject;

/**
 * Created by Ttg on 2016/4/21 0021.
 */
public class ParseJsonUtils {

    public static RBResponse parse(JSONObject jsonObject, Class< ? extends RBResponse> clazz) {
        Gson gson = new Gson();
        String json = jsonObject.toString();
        RBResponse response = gson.fromJson(json, clazz);
        return response;
    }

}
