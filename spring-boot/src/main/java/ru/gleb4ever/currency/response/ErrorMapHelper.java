package ru.gleb4ever.currency.response;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ErrorMapHelper {

  public static ResponseEntity<?> createBindingErrorResponse(BindingResult bindingResult, HttpStatus httpStatus) {
    Map<String, List<String>> validationErrors = bindingResult.getFieldErrors().stream()
        .collect(Collectors.groupingBy(FieldError::getField,
            Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));
    return ResponseEntity.status(httpStatus).body(ErrorResponse.builder()
        .result(false)
        .message("validation failed")
        .errors(ErrorResponse.ValidationErrors.builder().errorMap(validationErrors).build())
        .build()
    );
  }

}
