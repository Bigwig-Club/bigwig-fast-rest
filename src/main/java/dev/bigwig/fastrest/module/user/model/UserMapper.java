package dev.bigwig.fastrest.module.user.model;

import dev.bigwig.fastrest.module.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

  @Mapping(target = "enabled", ignore = true)
  @Mapping(target = "credentialsNonExpired", ignore = true)
  @Mapping(target = "accountNonLocked", ignore = true)
  @Mapping(target = "accountNonExpired", ignore = true)
  @Mapping(target = "updateTime", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createTime", ignore = true)
  User userDTOToUser(UserDTO dto);
}
