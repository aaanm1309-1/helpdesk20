package br.com.adrianomenezes.orderserviceapi.controllers.exceptions;


import br.com.adrianomenezes.models.exceptions.ResourceNotFoundException;
import br.com.adrianomenezes.models.exceptions.StandardError;
import br.com.adrianomenezes.models.exceptions.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;


@ControllerAdvice
public class ControllerExceptionHandler {

//    @ExceptionHandler(UsernameNotFoundException.class)
//    ResponseEntity<StandardError> handleUsernameNotFoundException(
//            final UsernameNotFoundException ex, final HttpServletRequest request) {
//        return ResponseEntity.status(
//                NOT_FOUND).body(
//                StandardError.builder()
//                        .timestamp(now())
//                        .status(NOT_FOUND.value())
//                        .error(NOT_FOUND.getReasonPhrase())
//                        .message(ex.getMessage())
//                        .path(request.getRequestURI())
//                        .build());
//
//
//    }


    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<StandardError> handleTokenNotFoundException(
            final ResourceNotFoundException ex, final HttpServletRequest request) {
        return ResponseEntity.status(
                NOT_FOUND).body(
                StandardError.builder()
                        .timestamp(now())
                        .status(NOT_FOUND.value())
                        .error(NOT_FOUND.getReasonPhrase())
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .build());

    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<StandardError> handleIllegalArgumentException(
            final IllegalArgumentException ex, final HttpServletRequest request) {
        return ResponseEntity.status(
                BAD_REQUEST).body(
                StandardError.builder()
                        .timestamp(now())
                        .status(BAD_REQUEST.value())
                        .error(BAD_REQUEST.getReasonPhrase())
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .build());

    }
//
//    @ExceptionHandler(RefreshTokenExpired.class)
//    ResponseEntity<StandardError> handleTokenNotFoundException(
//            final RefreshTokenExpired ex, final HttpServletRequest request) {
//        return ResponseEntity.status(
//                BAD_REQUEST).body(
//                StandardError.builder()
//                        .timestamp(now())
//                        .status(BAD_REQUEST.value())
//                        .error(BAD_REQUEST.getReasonPhrase())
//                        .message(ex.getMessage())
//                        .path(request.getRequestURI())
//                        .build());
//
//    }

//    @ExceptionHandler({BadCredentialsException.class,RefreshTokenExpired.class})
//    ResponseEntity<StandardError> handleBadCredentialsException(
//            final RuntimeException ex, final HttpServletRequest request) {
//        return ResponseEntity.status(
//                UNAUTHORIZED).body(
//                StandardError.builder()
//                        .timestamp(now())
//                        .status(UNAUTHORIZED.value())
//                        .error(UNAUTHORIZED.getReasonPhrase())
//                        .message(ex.getMessage())
//                        .path(request.getRequestURI())
//                        .build());
//
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<StandardError> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException ex, final HttpServletRequest request) {

        var error = ValidationException.builder()
                .timestamp(now())
                .status(BAD_REQUEST.value())
                .error("Validation Exception")
                .message("Exception in validation attributes")
                .path(request.getRequestURI())
                .errors(new ArrayList<>())
                .build();

        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(error);
    }




}

