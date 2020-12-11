package dev.bigwig.fastrest.module.user.repository;

import dev.bigwig.fastrest.common.FRepository;
import dev.bigwig.fastrest.module.user.entity.User;
import java.util.Optional;

public interface UserRepository extends FRepository<User, Long> {

  Optional<User> findByUsername(String username);
}
