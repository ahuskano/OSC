package co.ahuskano.something.api;

import com.google.gson.annotations.Expose;

/**
 * Created by ahuskano on 10.10.2015..
 */
public class BaseResponse {

    @Expose
    private boolean success;

    @Expose
    private String[] errorInfo;

    @Expose
    private String[] messages;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String[] getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String[] errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String[] getMessages() {
        return messages;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
    }
}