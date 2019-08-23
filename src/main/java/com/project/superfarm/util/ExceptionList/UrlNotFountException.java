package com.project.superfarm.util.ExceptionList;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @apiNote  강제 error 발생
 *           아이디나 검색을 찾을수 없을 경우 발생
 */


/**
 * @apiNote Front의 요청중 자료가 없을 경우 발생 예) ID 중복체크
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="요청 하신 값을 찾을수 없습니다.")
public class UrlNotFountException extends RuntimeException{

}

