package org.example.controller;

import org.example.exception.ForbiddenException;
import org.example.exception.ValidationException;
import org.example.exception.ValidationResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
@Controller
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<ValidationResponse> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        ValidationResponse validationResponse = new ValidationResponse().addError("element", "do_not_exist");
        return new ResponseEntity<>(validationResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    public final ResponseEntity<ValidationResponse> handleForbiddenException(ForbiddenException ex, WebRequest request) {
        ValidationResponse validationResponse = new ValidationResponse().addError("element", "forbidden access");
        return new ResponseEntity<>(validationResponse, HttpStatus.BAD_REQUEST);
    }


//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public final ResponseEntity<ValidationResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
//        ValidationResponse validationResponse = new ValidationResponse().addError("method argument", "not valid input value");
//        return new ResponseEntity<>(validationResponse, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<ValidationResponse> handleMethodArgumentNotValidException(ValidationException ex, WebRequest request) {
        ValidationResponse validationResponse = new ValidationResponse().addError("method argument", "not valid input value");
        return new ResponseEntity<>(validationResponse, HttpStatus.BAD_REQUEST);
    }


//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException exception,
//            HttpHeaders headers,
//            HttpStatus status,
//            WebRequest request) {
//
//        ValidationResponse validationResponse = new ValidationResponse().addError("method argument", "not valid input value");
////        String bodyOfResponse = exception.getMessage();
//        return new ResponseEntity(validationResponse, headers, status);
//    }

}