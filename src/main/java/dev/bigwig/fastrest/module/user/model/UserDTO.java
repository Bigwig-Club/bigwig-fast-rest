package dev.bigwig.fastrest.module.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
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
