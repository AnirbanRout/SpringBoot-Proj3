
package com.springboot.animeinfo.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleExcption(MethodArgumentNotValidException exp) {

        Map<String, String> errorMap = new HashMap<>();

        exp.getFieldErrors()
                .forEach(FieldError -> errorMap.put(FieldError.getField(),
                        FieldError.getDefaultMessage()));

        return errorMap;

    }

    @ExceptionHandler(AnimeNotFoundException.class)
    public Map<String, String> handleAnimeNotFound(AnimeNotFoundException exp) {
        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("message", exp.getMessage());

        return errorMap;
    }

}