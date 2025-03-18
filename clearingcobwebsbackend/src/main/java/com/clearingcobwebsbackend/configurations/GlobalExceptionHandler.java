package com.clearingcobwebsbackend.configurations;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NoHandlerFoundException.class)
  public String handleNotFoundError(Exception ex) {
    return "redirect:/yourCustom404page";
  }

  @ExceptionHandler({ NotFoundException.class })
  public final ResponseEntity<ErrorResponse> handleNotFoundException(final NotFoundException exception) {
    ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
        "Resource Not Found", exception.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({ UnauthorizedException.class })
  public final ResponseEntity<ErrorResponse> UnauthorizedException(final UnauthorizedException exception) {
    ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(),
        "Unauthorized", exception.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler({ ForbiddenException.class })
  public final ResponseEntity<ErrorResponse> ForbiddenException(final ForbiddenException exception) {
    ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.FORBIDDEN.value(), "Forbidden",
        exception.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
  }

}