package dev.bigwig.fastrest.module.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户请求")
public class UserDTO {

  @ApiModelProperty(value = "账号", required = true)
  private String username;

  @ApiModelProperty(value = "密码", required = true)
  private String password;

  @ApiModelProperty(value = "姓名", required = true)
  private String realName;

  @ApiModelProperty(value = "性别", required = true)
  private String sex;
}
