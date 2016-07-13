package com.himan.himanpro.domain;

/**
 * Created by HIMan on 16/7/11.
 */
public class SetImgInfo {

    private int img;
    private String time;

    public int getImg() {
        return img;
    }

    public String getTime() {
        return time;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public SetImgInfo(int img, String time) {
        this.img = img;
        this.time = time;
    }

    public SetImgInfo(){

    }
}
