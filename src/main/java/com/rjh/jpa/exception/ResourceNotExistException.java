package com.rjh.jpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Null
 * @date 2020-01-13
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND,reason="资源不存在")
public class ResourceNotExistException extends RuntimeException{

}
