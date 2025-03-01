package com.appliance_shop_app.products_service.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ErrorResponse {

    private String errorCode;
    private String errorMessage;
    private LocalDateTime timestamp;


    public ErrorResponse(){

    }

    public ErrorResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.timestamp = LocalDateTime.now();
    }

}
