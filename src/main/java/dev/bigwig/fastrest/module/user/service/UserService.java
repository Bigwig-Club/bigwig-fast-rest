package dev.bigwig.fastrest.module.user.service;

import dev.bigwig.fastrest.common.FService;
import dev.bigwig.fastrest.module.user.entity.User;
import dev.bigwig.fastrest.module.user.model.UserDTO;
import dev.bigwig.fastrest.module.user.model.UserMapper;
import dev.bigwig.fastrest.module.user.repository.UserRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService implements FService<User, UserDTO, Long> {

  @Resource
  private UserRepository userRepository;

  @Resource
  private UserMapper userMapper;

  @Override
  public List<User> list() {
    return userRepository.findAll();
  }

  @Override
  public Page<User> list(
    Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  @Override
  public User get(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("用户不存在：" + id));
  }

  @Override
  public boolean existsById(Long id) {
    return userRepository.existsById(id);
  }

  @Override
  public User create(UserDTO userDTO) {
    User user = userMapper.userDTOToUser(userDTO);
    return userRepository.save(user);
  }

  @Override
  public List<User> createAll(
    List<UserDTO> userDTOS) {
    List<User> users = userDTOS.stream().map(userMapper::userDTOToUser)
      .collect(Collectors.toList());
    return userRepository.saveAll(users);
  }

  @Override
  public User update(Long id, UserDTO userDTO) {
    User user = userMapper.userDTOToUser(userDTO);
    user.setId(id);
    return userRepository.save(user);
  }

  @Override
  public void delete(Long id) {
    userRepository.deleteById(id);
  }

  @Override
  public void deleteAll(List<Long> ids) {
    userRepository.deleteByIdIn(ids);
  }
}
