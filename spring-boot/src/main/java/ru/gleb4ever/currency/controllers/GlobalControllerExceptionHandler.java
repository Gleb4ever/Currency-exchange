package ru.gleb4ever.currency.controllers;

import ru.gleb4ever.currency.response.ErrorResponse;
import ru.gleb4ever.currency.service.exception.AmountSelectMethodNotFoundException;
import ru.gleb4ever.currency.service.exception.EmptyResultException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@Slf4j
@RestControllerAdvice
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GlobalControllerExceptionHandler {


  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> handleNotReadableException(
      HttpMessageNotReadableException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ErrorResponse.builder().message("Request body fields has wrong format").build());
  }


  @ExceptionHandler(AmountSelectMethodNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleMissingParameter(
      AmountSelectMethodNotFoundException exception) {
    return ResponseEntity.badRequest()
        .body(ErrorResponse.builder().message("Wrong path parameter").build());
  }

  @ExceptionHandler(EmptyResultException.class)
  public ResponseEntity<?> handleEmptyResult(EmptyResultException exception) {
    log.warn("request has no results");
    return ResponseEntity.ok().body(ErrorResponse.builder().message("No results").build());
  }


  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<?> handleArgumentTypeMismatch(
      MethodArgumentTypeMismatchException exception) {
    return ResponseEntity.ok()
        .body(ErrorResponse.builder().message("Wrong type of path arguments").build());
  }

}