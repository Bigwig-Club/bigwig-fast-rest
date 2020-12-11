package dev.bigwig.fastrest.module.user.controller;

import dev.bigwig.fastrest.common.FController;
import dev.bigwig.fastrest.common.annotation.FPageable;
import dev.bigwig.fastrest.module.user.entity.User;
import dev.bigwig.fastrest.module.user.model.UserDTO;
import dev.bigwig.fastrest.module.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/users")
public class UserController implements FController<User, UserDTO, Long> {

  @Resource
  private UserService userService;

  @ApiOperation("列表")
  @GetMapping
  @Override
  public List<User> list() {
    return userService.list();
  }

  @FPageable
  @GetMapping("/paged")
  @Override
  public Page<User> list(
    @PageableDefault Pageable pageable) {
    return userService.list(pageable);
  }

  @GetMapping("/{id}")
  @Override
  public User get(@PathVariable Long id) {
    return userService.get(id);
  }

  @PostMapping
  @Override
  public User create(UserDTO userDTO) {
    return userService.create(userDTO);
  }

  @PostMapping("/batch")
  @Override
  public List<User> createAll(List<UserDTO> userDTOS) {
    return userService.createAll(userDTOS);
  }

  @PutMapping("/{id}")
  @Override
  public User update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
    return userService.update(id, userDTO);
  }

  @DeleteMapping("/{id}")
  @Override
  public void delete(@PathVariable Long id) {
    userService.delete(id);
  }

  @PostMapping("/delete")
  @Override
  public void deleteAll(@RequestBody List<Long> ids) {
    userService.deleteAll(ids);
  }
}
