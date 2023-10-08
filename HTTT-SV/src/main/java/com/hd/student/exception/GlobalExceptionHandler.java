package com.hd.student.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionDetailResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex, WebRequest webRequest)
    {
        String message = ex.getMessage();
        ExceptionDetailResponse rp = new ExceptionDetailResponse(HttpStatus.NOT_FOUND.value(),new Date(),message,webRequest.getDescription(false));


        return new ResponseEntity<ExceptionDetailResponse>(rp, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceExistException.class)
    public ResponseEntity<ExceptionDetailResponse> resourceExistExceptionHandler(ResourceExistException ex, WebRequest webRequest){
        String message = ex.getMessage();
        ExceptionDetailResponse rp = new ExceptionDetailResponse(HttpStatus.CONFLICT.value(),new Date(),message,webRequest.getDescription(false));
        return new ResponseEntity<ExceptionDetailResponse>(rp, HttpStatus.CONFLICT);
    }
    //Chưa đăng nhập
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionDetailResponse> unauthorizedExceptionHandler(
            UnauthorizedException ex, WebRequest request){
        String message = ex.getMessage();
        ExceptionDetailResponse details = new ExceptionDetailResponse(HttpStatus.UNAUTHORIZED.value(),
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
        ExceptionDetailResponse details = new ExceptionDetailResponse(HttpStatus.FORBIDDEN.value(),
                new Date(),
                message,
                request.getDescription(false));
        return new ResponseEntity<>(details,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDetailResponse> dataIntegrityViolationException(
            DataIntegrityViolationException ex, WebRequest request){
        ExceptionDetailResponse details = new ExceptionDetailResponse(
                HttpStatus.CONFLICT.value(), new Date(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(details,HttpStatus.CONFLICT);
    }
    @ExceptionHandler(EnumNotFoundException.class)
    public ResponseEntity<ExceptionDetailResponse> enumNotFoundException(EnumNotFoundException ex,
            WebRequest request){
        ExceptionDetailResponse details = new ExceptionDetailResponse(
                HttpStatus.BAD_REQUEST.value(), new Date(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(details,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ExceptionDetailResponse> conflictException(ConflictException ex,
                                                                         WebRequest request){
        ExceptionDetailResponse details = new ExceptionDetailResponse(
                HttpStatus.CONFLICT.value(), new Date(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(details,HttpStatus.CONFLICT);
    }
}
