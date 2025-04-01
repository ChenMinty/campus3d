package com.chenminty.campus3d.exception;

import com.chenminty.campus3d.dto.response.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CampusException.class)
    public Response<?> handle(CampusException e) {
        return Response.error(e.getCode(), e.getMessage());
    }
}
