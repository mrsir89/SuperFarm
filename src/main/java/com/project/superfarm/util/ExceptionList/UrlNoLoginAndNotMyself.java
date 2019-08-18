package com.project.superfarm.util.ExceptionList;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @apiNote 본인이 아닌 다른 유저의것을 수정 또는 삭제 예) 남의 글 삭제
 */
@ResponseStatus(value= HttpStatus.FORBIDDEN , reason="권한이 없습니다.")
public class UrlNoLoginAndNotMyself extends RuntimeException{
}

