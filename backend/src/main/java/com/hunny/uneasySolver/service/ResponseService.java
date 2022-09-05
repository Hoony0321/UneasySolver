package com.hunny.uneasySolver.service;

import com.hunny.uneasySolver.domain.CommonResponse;
import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class ResponseService {

    public <T> CommonResponse<T> getSuccessResponse(String msg, T data){
        CommonResponse response = new CommonResponse();
        response.setSuccess(true);
        response.setCode(HttpStatus.OK.value());
        response.setMsg(msg);
        response.setData(data);

        return response;
    }

    public <T> CommonResponse<T> getFailureResponse(Integer code,String msg, T data){
        CommonResponse response = new CommonResponse();
        response.setSuccess(false);
        response.setMsg(msg);
        response.setData(data);

        if(code == null){response.setCode(HttpStatus.EXPECTATION_FAILED.value());}
        else{ response.setCode(code);}

        return response;
    }

    public void convertObjectToResposne(HttpServletResponse response, CommonResponse commonResponse) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        JSONObject responseJson = new JSONObject();

        responseJson.put("code", commonResponse.getCode());
        responseJson.put("success", commonResponse.getSuccess());
        responseJson.put("msg", commonResponse.getMsg());
        responseJson.put("data", commonResponse.getData());

        response.getWriter().print(responseJson);
    }
}
