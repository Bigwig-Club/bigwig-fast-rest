package dev.bigwig.fastrest.common.filter;

import dev.bigwig.fastrest.common.annotation.FRawResponse;
import dev.bigwig.fastrest.common.annotation.FResponseStatus;
import dev.bigwig.fastrest.common.exception.FError;
import java.lang.reflect.Method;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/** 响应处理器 */
@RestControllerAdvice
public class FResponseHandler implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(
      MethodParameter returnType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
    return !returnType.hasMethodAnnotation(FRawResponse.class);
  }

  @Override
  public Object beforeBodyWrite(
      Object body,
      @NotNull MethodParameter returnType,
      @NotNull MediaType selectedContentType,
      @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
      @NotNull ServerHttpRequest request,
      @NotNull ServerHttpResponse response) {
    HttpStatus status = HttpStatus.OK;

    if (request.getMethod() != null) {
      switch (request.getMethod()) {
        case OPTIONS:
        case DELETE:
          status = HttpStatus.NO_CONTENT;
          break;
        case POST:
          status = HttpStatus.CREATED;
          break;
        default:
          break;
      }
    }

    if (body instanceof FError) {
      status = ((FError) body).getStatus();
    } else {
      Method method = returnType.getMethod();
      if (method != null && method.isAnnotationPresent(FResponseStatus.class)) {
        FResponseStatus annotation = method.getAnnotation(FResponseStatus.class);
        status = annotation.value();
      }
    }

    response.setStatusCode(status);
    return body;
  }
}
