package com.fernando.auth_server.controller;

import com.fernando.auth_server.dto.ErrorResponseDTO;
import com.fernando.auth_server.exceptions.EmailExistsException;
import com.fernando.auth_server.exceptions.RolNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.Collections;

import static com.fernando.auth_server.enums.ErrorCatalog.*;
import static com.fernando.auth_server.enums.ErrorType.FUNCTIONAL;
import static com.fernando.auth_server.enums.ErrorType.SYSTEM;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailExistsException.class)
    public ErrorResponseDTO handleEmailExistsException(EmailExistsException e) {
        log.warn("Warning email exists :{}",USER_EMAIL_EXISTS.getCode());
        return ErrorResponseDTO.builder()
                .code(USER_EMAIL_EXISTS.getCode())
                .type(FUNCTIONAL)
                .message(USER_EMAIL_EXISTS.getMessage())
                .timestamp(LocalDate.now().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RolNotFoundException.class)
    public ErrorResponseDTO handleRolNotFoundException(RolNotFoundException e) {
        log.warn("Warning rol not found :{}",ROL_NOT_FOUND.getCode());
        return ErrorResponseDTO.builder()
                .code(ROL_NOT_FOUND.getCode())
                .type(FUNCTIONAL)
                .message(ROL_NOT_FOUND.getMessage())
                .timestamp(LocalDate.now().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("Warning request not validate :{}",USER_BAD_PARAMETERS.getCode());
        BindingResult bindingResult = e.getBindingResult();
        return ErrorResponseDTO.builder()
                .code(USER_BAD_PARAMETERS.getCode())
                .type(FUNCTIONAL)
                .message(USER_BAD_PARAMETERS.getMessage())
                .details(bindingResult.getFieldErrors().stream().map(MessageSourceResolvable::getDefaultMessage)
                        .toList())
                .timestamp(LocalDate.now().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponseDTO handleException(Exception e) {
        log.error("An error occurred :{}",INTERNAL_SERVER_ERROR.getCode());
        return ErrorResponseDTO.builder()
                .code(INTERNAL_SERVER_ERROR.getCode())
                .type(SYSTEM)
                .message(INTERNAL_SERVER_ERROR.getMessage())
                .details(Collections.singletonList(e.getMessage()))
                .timestamp(LocalDate.now().toString())
                .build();
    }
}
