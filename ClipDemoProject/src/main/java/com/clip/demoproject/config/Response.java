package com.clip.demoproject.config;

import com.clip.demoproject.config.AbstractResponse;
import org.springframework.http.HttpStatus;

public class Response<T> extends AbstractResponse<T> {

    private static final long serialVersionUID = -411789513841317291L;
    private String message;

    public Response() {
        super(HttpStatus.OK);
    }

    public Response(T responseBody) {
        super(HttpStatus.OK, responseBody);
    }
    
    public Response(HttpStatus status, String message) {
        super(status, null);
        this.message = message;
    }

    public Response(T responseBody, String message) {
        super(HttpStatus.OK, responseBody);
        this.message = message;
    }

    public Response(HttpStatus status, T responseBody, String message) {
        super(status, responseBody);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
