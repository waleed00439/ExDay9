package com.example.blog.advise;


import com.example.blog.dto.Api;
import com.example.blog.exception.ApiException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvise {


    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity dataIntegrityViolation(DataIntegrityViolationException dataIntegrityViolationException){
        String message="This username is already used !";
        return ResponseEntity.status(400).body(new Api(message,400));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        String message=methodArgumentNotValidException.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(new Api(message,400));
    }

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity apiException(ApiException apiException){
        return ResponseEntity.status(400).body(new Api(apiException.getMessage(),400));
    }


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception exception){
        exception.printStackTrace();
        return ResponseEntity.status(500).body(new Api("Server Error",500));
    }
}
