package com.example.demo.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ResponseEnum implements CommonResponse{
    LOGIN("200","Login successfully!"),
    ERROR("401","Invalid operation!");
    private String statusCode;
    private String message;
    @Override
    public String getStatusCode(){return statusCode;}
    @Override
    public String getMessage(){return message;}
}
