package com.project.superfarm.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="잘못된 접근 입니다.")
public class UrlNotFountException extends RuntimeException{
}

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="잘못된 값이 전달 되었습니다.")
class UrlNullPoinException extends RuntimeException{
}

