package com.example.idus_exam.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handle(MethodArgumentNotValidException e) {
        // 어떤 변수에 값을 설정하다가 에러가 났는지
        System.out.println(e.getBindingResult().getFieldError().getField());

        // 입력값 검증할 때 작성한 message를 받아오는 코드
        System.out.println(e.getBindingResult().getFieldError().getDefaultMessage());

        // BaseResponse로 응답을 주게 하면 더 좋다.
        return ResponseEntity.badRequest().body("입력값이 이상하다");
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handle(SQLIntegrityConstraintViolationException e) {
        return ResponseEntity.badRequest().body("이메일 중복이다");
    }
}
