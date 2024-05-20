package com.dxcassessment.bookstoreapi.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMetodArgumentNotValid(MethodArgumentNotValidException exception) {
        Map<String, String> map =  new HashMap<>();
        StringBuilder errorMessage = new StringBuilder();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMessage.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append("; ");
        });

        // Remove the trailing semicolon and space
        if (errorMessage.length() > 0) {
            errorMessage.setLength(errorMessage.length() - 2);
        }
        map.put("Exception: ", exception.getClass().getSimpleName());
        map.put("Status Code: ", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        map.put("Error Message: ", errorMessage.toString());
        return map;
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidArgumentException.class)
    public Map<String, String> handleInvalidArgument(InvalidArgumentException exception) {
        Map<String, String> map =  new HashMap<>();
        map.put("Exception: ", exception.getClass().getSimpleName());
        map.put("Status Code: ", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        map.put("Error Message: ", exception.getMessage());
        return map;
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public Map<String, String> handleBadRequestException(BadRequestException exception) {
        Map<String, String> map =  new HashMap<>();
        map.put("Exception: ", exception.getClass().getSimpleName());
        map.put("Status Code: ", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        map.put("Error Message: ", exception.getMessage());
        return map;
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EntityNotFoundException.class)
    public Map<String, String> handleEntityNotFoundException(EntityNotFoundException exception) {
        Map<String, String> map =  new HashMap<>();
        map.put("Exception: ", exception.getClass().getSimpleName());
        map.put("Status Code: ", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        map.put("Error Message: ", exception.getMessage());
        return map;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleGenericException(Exception exception) {
        Map<String, String> map =  new HashMap<>();
        map.put("Exception: ", exception.getClass().getSimpleName());
        map.put("Status Code: ", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        map.put("Error Message: ", exception.getMessage());
        return map;
    }
}
