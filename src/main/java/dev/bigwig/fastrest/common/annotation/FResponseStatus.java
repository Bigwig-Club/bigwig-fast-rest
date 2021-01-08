package dev.bigwig.fastrest.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.http.HttpStatus;

/** 标记方法的特殊返回值，不标记则根据 Http 方法提供默认返回值 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FResponseStatus {

  HttpStatus value() default HttpStatus.OK;
}
