package dev.bigwig.fastrest.module.user.entity;

import com.google.common.collect.Lists;
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
@Table(name = "user", indexes = {
  @Index(name = "unq_username", columnList = "username", unique = true)
})
@EntityListeners(value = {AuditingEntityListener.class})
public class User implements Serializable, UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreatedDate
  @Column(name = "create_time", updatable = false)
  private LocalDateTime createTime;

  @LastModifiedDate
  @Column(name = "update_time")
  private LocalDateTime updateTime;

  @Column(name = "username")
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "real_name", nullable = false)
  private String realName;

  @Column(name = "sex", nullable = false)
  @Enumerated(EnumType.STRING)
  private Sex sex;

  @Column(name = "enabled", nullable = false)
  private boolean enabled;

  @Column(name = "account_non_expired", nullable = false)
  private boolean accountNonExpired;

  @Column(name = "account_non_locked", nullable = false)
  private boolean accountNonLocked;

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
