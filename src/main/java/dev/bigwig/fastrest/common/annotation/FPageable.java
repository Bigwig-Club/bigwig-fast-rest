package dev.bigwig.fastrest.common.annotation;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记 Api 含有分页参数
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
  @ApiImplicitParam(name = "page", dataType = "int", paramType = "query", value = "页数"),
  @ApiImplicitParam(name = "size", dataType = "int", paramType = "query", value = "每页条数"),
  @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query", allowMultiple = true, value = "排序")
})
public @interface FPageable {

}
