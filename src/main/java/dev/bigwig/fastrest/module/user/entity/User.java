package dev.bigwig.fastrest.module.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
    name = "user",
    indexes = {@Index(name = "unq_username", columnList = "username", unique = true)})
@EntityListeners(value = {AuditingEntityListener.class})
@DynamicInsert
@DynamicUpdate
public class User implements Serializable, UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ApiModelProperty("创建时间")
  @CreatedDate
  @Column(name = "create_time", updatable = false)
  private LocalDateTime createTime;

  @ApiModelProperty("更新时间")
  @LastModifiedDate
  @Column(name = "update_time")
  private LocalDateTime updateTime;

  @ApiModelProperty("账号")
  @Column(name = "username")
  private String username;

  @JsonProperty(access = Access.WRITE_ONLY)
  @ApiModelProperty("密码")
  @Column(name = "password", nullable = false)
  private String password;

  @ApiModelProperty("姓名")
  @Column(name = "real_name", nullable = false)
  private String realName;

  @ApiModelProperty("性别")
  @Column(name = "sex", nullable = false)
  @Enumerated(EnumType.STRING)
  private Sex sex;

  @ApiModelProperty("是否启用")
  @Column(name = "enabled", nullable = false)
  private boolean enabled;

  @ApiModelProperty("是否过期")
  @Column(name = "account_non_expired", nullable = false)
  private boolean accountNonExpired;

  @ApiModelProperty("是否锁定")
  @Column(name = "account_non_locked", nullable = false)
  private boolean accountNonLocked;

  @ApiModelProperty("密码是否过期")
  @Column(name = "credentials_non_expired", nullable = false)
  private boolean credentialsNonExpired;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Lists.newArrayList(new SimpleGrantedAuthority("ROLE_ADMIN"));
  }

  @Getter
  public enum Sex {
    MALE("男"),
    FEMALE("女");

    private final String sex;

    Sex(String sex) {
      this.sex = sex;
    }
  }
}
