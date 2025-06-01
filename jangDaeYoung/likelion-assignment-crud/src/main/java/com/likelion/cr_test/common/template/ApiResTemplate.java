package com.likelion.cr_test.common.template;

import com.likelion.cr_test.common.error.ErrorCode;
import com.likelion.cr_test.common.error.SuccessCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResTemplate<T> {

    private final int code; //응답 코드
    private final String message; // 응답 메세지
    private T data; //응답 데이터

    // 데이터 없는 성공 응답
    public static ApiResTemplate successWithNoContent(SuccessCode successCode){
        return new ApiResTemplate<>(successCode.getHttpStatusCode(), successCode.getMessage());
    }

    //데이터 포함한 성공 응답
    public static <T> ApiResTemplate<T> successResponse(SuccessCode successCode, T data){
        return new ApiResTemplate<>(successCode.getHttpStatusCode(), successCode.getMessage(), data);
    }

    //에러 응답(메세지 포함)
    public static ApiResTemplate errorResponse(ErrorCode errorCode, String customMessage){
        return  new ApiResTemplate<>(errorCode.getHttpStatusCode(), customMessage);
    }


}
