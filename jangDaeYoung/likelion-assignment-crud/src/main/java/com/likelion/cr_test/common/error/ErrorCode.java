package com.likelion.cr_test.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    //404 notfound
    MEMBER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 사용자가 없습니다. memberId = ", "NOT_FOUND_404"),
    TAG_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 태그가 없습니다. tagId = ", "NOT_FOUND_404"),
    POST_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당사용자가 없습니다. postId = ", "NOT_FOUND_404"),

    //400 bad request
    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "유효성 검사에 실패하였습니다", "BAD_REQUEST_400"),


    //500 internal sever error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류가 발생했습니다. ", "INTERNAL_SERVER_ERROR_505");



    private final HttpStatus httpStatus;
    private final String message;
    private final String code;

    public int getHttpStatusCode(){
        return httpStatus.value();
    }


}
