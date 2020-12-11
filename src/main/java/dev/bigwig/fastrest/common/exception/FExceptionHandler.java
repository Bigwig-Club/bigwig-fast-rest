package dev.bigwig.fastrest.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class FExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected @NotNull ResponseEntity<Object> handleHttpRequestMethodNotSupported(
    @NotNull HttpRequestMethodNotSupportedException ex,
    @NotNull HttpHeaders headers,
    @NotNull HttpStatus status,
    @NotNull WebRequest request
  ) {
    return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
  }
}
