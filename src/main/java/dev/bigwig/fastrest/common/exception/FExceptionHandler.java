package dev.bigwig.fastrest.common.exception;

import io.jsonwebtoken.JwtException;
import java.util.NoSuchElementException;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class FExceptionHandler {

  @ExceptionHandler
  public FError handleException(Exception e) {
    return new FError()
        .setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        .setMessage("未知异常：" + e.getMessage());
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public FError handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException e) {
    return new FError()
        .setStatus(HttpStatus.METHOD_NOT_ALLOWED)
        .setMessage(String.format("HTTP 请求方法不支持: %s", e.getMethod()));
  }

  @ExceptionHandler(NoSuchElementException.class)
  public FError handleNoSuchElementException(NoSuchElementException e) {
    return new FError().setStatus(HttpStatus.NOT_FOUND).setMessage(e.getMessage());
  }

  @ExceptionHandler(FException.class)
  public FError handleFException(FException e) {
    return new FError().setStatus(e.getHttpStatus()).setMessage(e.getMessage());
  }

  @ExceptionHandler(BadCredentialsException.class)
  public FError handleBadCredentialsException(BadCredentialsException e) {
    return new FError().setStatus(HttpStatus.UNAUTHORIZED).setMessage("用户名或密码错误");
  }

  @ExceptionHandler(DisabledException.class)
  public FError handleDisabledException(DisabledException e) {
    return new FError().setStatus(HttpStatus.BAD_REQUEST).setMessage("账号已被禁用");
  }

  @ExceptionHandler({
    ConstraintViolationException.class,
    org.hibernate.exception.ConstraintViolationException.class
  })
  public FError handleConstraintViolationException(ConstraintViolationException e) {
    return new FError().setStatus(HttpStatus.BAD_REQUEST).setMessage("数据库主键校验错误");
  }

  @ExceptionHandler(AuthenticationException.class)
  public FError handleAuthenticationException(AuthenticationException e) {
    return new FError().setStatus(HttpStatus.UNAUTHORIZED).setMessage(e.getMessage());
  }

  @ExceptionHandler(GenericJDBCException.class)
  public FError handleGenericJDBCException(GenericJDBCException e) {
    return new FError().setStatus(HttpStatus.BAD_REQUEST).setMessage("传入字段错误：" + e.getMessage());
  }

  @ExceptionHandler(JwtException.class)
  public FError handleJwtException(JwtException e) {
    return new FError().setStatus(HttpStatus.UNAUTHORIZED).setMessage("token 错误或已过期");
  }
}
