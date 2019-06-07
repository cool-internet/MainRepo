package com.example.demo.response;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.constant.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NormalResponse {
    String statusCode;
    String message;
    JSONObject body;
    public static NormalResponse error(){
        return new NormalResponse(ResponseEnum.ERROR.getStatusCode(),ResponseEnum.ERROR.getMessage(),null);
    }
    public static NormalResponse login(JSONObject body){
        return new NormalResponse(ResponseEnum.LOGIN.getStatusCode(),ResponseEnum.LOGIN.getMessage(),body);
    }
}
