package com.example.ymeng.tomorrowhelper.model.bean;

import java.io.Serializable;

/**
 * Author:YMeng
 * Time:2018/10/25  9:42
 * This is Bean
 */
public class Bean implements Serializable {

    private MessageBean message;
    private boolean success;

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class MessageBean {
        /**
         * em : 您已经实名认证过了
         */

        private String em;

        public String getEm() {
            return em;
        }

        public void setEm(String em) {
            this.em = em;
        }
    }
}
