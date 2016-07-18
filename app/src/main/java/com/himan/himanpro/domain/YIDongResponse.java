package com.himan.himanpro.domain;

import java.util.List;

/**
 * Created by HIMan on 16/7/18.
 */
public class YIDongResponse extends RBResponse {


    /**
     * error : false
     * results : [{"_id":"56cc6d1c421aa95caa707527","createdAt":"2015-11-05T15:01:26.494Z","desc":"iOS 处理图片的一些小 Tip","publishedAt":"2015-11-06T04:11:25.965Z","type":"iOS","url":"http://blog.ibireme.com/","used":true,"who":"Andrew  Liu"},{"_id":"56cc6d1c421aa95caa707509","createdAt":"2015-11-03T13:49:35.344Z","desc":"关于 @synchronized，这儿比你想知道的还要多","publishedAt":"2015-11-04T04:01:55.589Z","type":"iOS","url":"http://yulingtianxia.com/blog/2015/11/01/More-than-you-want-to-know-about-synchronized/","used":true,"who":"CallMeWhy"},{"_id":"56cc6d1c421aa95caa707508","createdAt":"2015-11-03T03:03:46.901Z","desc":"深入剖析Auto Layout，分析iOS各版本新增特性","publishedAt":"2015-11-03T06:04:59.462Z","type":"iOS","url":"http://www.starming.com/index.php?v=index&view=84","used":true,"who":"Dear宅学长"},{"_id":"56cc6d1c421aa95caa707527","createdAt":"2015-11-05T15:01:26.494Z","desc":"iOS 处理图片的一些小 Tip","publishedAt":"2015-11-06T04:11:25.965Z","type":"iOS","url":"http://blog.ibireme.com/","used":true,"who":"Andrew  Liu"},{"_id":"56cc6d1c421aa95caa707508","createdAt":"2015-11-03T03:03:46.901Z","desc":"深入剖析Auto Layout，分析iOS各版本新增特性","publishedAt":"2015-11-03T06:04:59.462Z","type":"iOS","url":"http://www.starming.com/index.php?v=index&view=84","used":true,"who":"Dear宅学长"},{"_id":"56cc6d1c421aa95caa707527","createdAt":"2015-11-05T15:01:26.494Z","desc":"iOS 处理图片的一些小 Tip","publishedAt":"2015-11-06T04:11:25.965Z","type":"iOS","url":"http://blog.ibireme.com/","used":true,"who":"Andrew  Liu"},{"_id":"56cc6d1c421aa95caa707505","createdAt":"2015-11-02T02:57:51.786Z","desc":"图片加载库 （支持 APNG、WebP、GIF 播放，支持渐进式图片加载，更高性能的缓存等）","publishedAt":"2015-11-02T04:14:30.324Z","type":"iOS","url":"https://github.com/ibireme/YYWebImage/","used":true,"who":"Dear宅学长"},{"_id":"56cc6d1c421aa95caa707524","createdAt":"2015-11-05T05:31:36.185Z","desc":"iOS 仿微信群组封面拼接控件","publishedAt":"2015-11-11T03:47:41.109Z","type":"iOS","url":"https://github.com/zhengjinghua/StitchingImage","used":true,"who":"Dear宅学长"},{"_id":"56cc6d1c421aa95caa70750d","createdAt":"2015-11-04T03:48:23.836Z","desc":"NSTimer alternative that doesn't retain the target （请叫我汪二 出品）","publishedAt":"2015-11-04T04:01:55.603Z","type":"iOS","url":"https://github.com/ChatGame/HWWeakTimer","used":true,"who":"__weak_Point"},{"_id":"56cc6d1c421aa95caa707508","createdAt":"2015-11-03T03:03:46.901Z","desc":"深入剖析Auto Layout，分析iOS各版本新增特性","publishedAt":"2015-11-03T06:04:59.462Z","type":"iOS","url":"http://www.starming.com/index.php?v=index&view=84","used":true,"who":"Dear宅学长"}]
     */

    private boolean error;
    /**
     * _id : 56cc6d1c421aa95caa707527
     * createdAt : 2015-11-05T15:01:26.494Z
     * desc : iOS 处理图片的一些小 Tip
     * type : iOS
     * url : http://blog.ibireme.com/
     * used : true
     * who : Andrew  Liu
     */

    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
