package com.project.superfarm.util.ExceptionList;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @apiNote 본인이 아닌 다른 사용자의 것을 삭제 하는 행위
 */

@ResponseStatus(value= HttpStatus.FORBIDDEN , reason="권한이 없습니다.")

public class UrlNoLoginAndNotMyself extends RuntimeException{
}

