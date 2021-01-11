package dev.bigwig.fastrest.common.config;

import dev.bigwig.fastrest.module.user.entity.User;
import dev.bigwig.fastrest.module.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class FAuditorAware implements AuditorAware<Long> {

  private final UserRepository userRepository;

  @Override
  public @NotNull Optional<Long> getCurrentAuditor() {
    String username =
        (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return userRepository.findByUsername(username).map(User::getId);
  }
}
