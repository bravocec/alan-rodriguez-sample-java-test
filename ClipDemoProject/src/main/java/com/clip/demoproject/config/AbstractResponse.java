package com.clip.demoproject.config;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class AbstractResponse<T> implements Serializable {

    private static final long serialVersionUID = -2720509744385056771L;
    private HttpStatus status;
    private T responseBody;

    public AbstractResponse() {
    }
    
    public AbstractResponse(HttpStatus status) {
        this.status = status;
    }

    public AbstractResponse(HttpStatus status, T responseBody) {
        this.status = status;
        this.responseBody = responseBody;
    }

    public int getStatus() {
        return status.value();
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public T getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(T responseBody) {
        this.responseBody = responseBody;
    }

}
