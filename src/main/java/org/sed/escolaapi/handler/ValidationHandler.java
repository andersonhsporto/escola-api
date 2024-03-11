package org.sed.escolaapi.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class ValidationHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, List<String>>> handleValidationErrors(
      MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(FieldError::getDefaultMessage).collect(Collectors.toList());

    log.info("Erros de validação: {}", errors);

    return new ResponseEntity<>(getErrorsMap(errors), HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<Object> handleConstraintViolation(
      ConstraintViolationException ex, WebRequest request) {
    List<String> errors = new ArrayList<>();

    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
      errors.add(violation.getMessage());
    }

    log.info("Erros de validação: {}", errors);
    return new ResponseEntity<>(getErrorsMap(errors), HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({HttpMessageNotReadableException.class})
  public ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex, WebRequest request) {
    List<String> errors = new ArrayList<>();

    errors.add("Requisição inválida");
    log.info("Requisição inválida {}", ex.getMessage());

    return new ResponseEntity<>(getErrorsMap(errors), HttpStatus.BAD_REQUEST);
  }

  private Map<String, List<String>> getErrorsMap(List<String> errors) {
    Map<String, List<String>> errorsMap = new HashMap<>();

    errorsMap.put("Erros", errors);
    return errorsMap;
  }

}
