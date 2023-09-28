package com.hd.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDetailResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex, WebRequest webRequest)
    {
        String message = ex.getMessage();
        ExceptionDetailResponse rp = new ExceptionDetailResponse(new Date(),message,webRequest.getDescription(false));


        return new ResponseEntity<ExceptionDetailResponse>(rp, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceExistException.class)
    public ResponseEntity<ExceptionDetailResponse> resourceExistExceptionHandler(ResourceExistException ex, WebRequest webRequest){
        String message = ex.getMessage();
        ExceptionDetailResponse rp = new ExceptionDetailResponse(new Date(),message,webRequest.getDescription(false));
        return new ResponseEntity<ExceptionDetailResponse>(rp, HttpStatus.CONFLICT);
    }
    //Chưa đăng nhập
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionDetailResponse> unauthorizedExceptionHandler(
            UnauthorizedException ex, WebRequest request){
        String message = ex.getMessage();
        ExceptionDetailResponse details = new ExceptionDetailResponse(
                new Date(),
                message,
                request.getDescription(false));
        return new ResponseEntity<>(details,HttpStatus.UNAUTHORIZED);
    }

//    Đã đăng nhập nhưng không có quyền
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionDetailResponse> accessDeniedExceptionHandle(
            AccessDeniedException ex, WebRequest request){
        String message = ex.getMessage();
        ExceptionDetailResponse details = new ExceptionDetailResponse(
                new Date(),
                message,
                request.getDescription(false));
        return new ResponseEntity<>(details,HttpStatus.FORBIDDEN);
    }
}
