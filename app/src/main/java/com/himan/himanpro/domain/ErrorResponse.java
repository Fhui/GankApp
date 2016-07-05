package com.himan.himanpro.domain;

/**
 * 失败的错误信息
 * @time 2016/4/22 0022 14:34
 */
public class ErrorResponse extends RBResponse {

    /**
     * text : 用户名不存在
     */

    private ErrorEntity error;

    public void setError(ErrorEntity error) {
        this.error = error;
    }

    public ErrorEntity getError() {
        return error;
    }

    public static class ErrorEntity {
        private String text;

        public void setText(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
