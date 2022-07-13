package com.lsd.logement.model;

import com.lsd.logement.exception.GeneralBaseException;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<T> {
    private int code;
    private String message;
    private T result;

    public ApiResponse(T data){
        this.result = data;
        this.message = "Success";
        this.code = HttpStatus.OK.value();
    }

    public ApiResponse(int code, String message){
        this.message = message;
        this.code = code;
        this.result = null;
    }

    public static ApiResponse<Exception> from(Exception e) {
        e.printStackTrace();
        ApiResponse<Exception> apiResponse = new ApiResponse<>();
        if (e instanceof GeneralBaseException) {
            apiResponse.message = e.getMessage();
            apiResponse.code = ((GeneralBaseException) e).getCode();
        }else{
            apiResponse.code = 500;
            apiResponse.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return apiResponse;
    }
}
