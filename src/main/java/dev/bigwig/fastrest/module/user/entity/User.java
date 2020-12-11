package dev.bigwig.fastrest.module.user.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", indexes = {
  @Index(name = "unq_username", columnList = "username", unique = true)
})
@EntityListeners(value = {AuditingEntityListener.class})
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreatedDate
  @Column(name = "create_time", updatable = false, columnDefinition = "timestamp")
  private LocalDateTime createTime;

  @LastModifiedDate
  @Column(name = "update_time", columnDefinition = "timestamp")
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
