package com.spring.handler.datahandlerspring11.utils.exceptions;

import lombok.Getter;
import lombok.Setter;

public class ReqExceptions extends RuntimeException {
    public static final String DEFAULT_ERROR_CODE = "-100";
    public static final String DEFAULT_ERROR_MSG = "Internal error";

    @Getter
    @Setter
    private String code;

    public ReqExceptions(String code) {
        super(DEFAULT_ERROR_MSG);
        this.code = code;
    }

    public ReqExceptions(String code, String message) {
        super(message);
        this.code = code;
    }

    public ReqExceptions(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }


}
