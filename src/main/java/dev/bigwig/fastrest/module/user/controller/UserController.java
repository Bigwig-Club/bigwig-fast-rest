package dev.bigwig.fastrest.module.user.controller;

import dev.bigwig.fastrest.common.FController;
import dev.bigwig.fastrest.common.annotation.FPageable;
import dev.bigwig.fastrest.common.annotation.FResponseStatus;
import dev.bigwig.fastrest.module.user.entity.User;
import dev.bigwig.fastrest.module.user.model.UserDTO;
import dev.bigwig.fastrest.module.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UserController implements FController<User, UserDTO, Long> {

  private final UserService userService;

  @ApiOperation("列表")
  @GetMapping
  @Override
  public List<User> list() {
    return userService.list();
  }

  @ApiOperation("分页列表")
  @FPageable
  @GetMapping("/paged")
  @Override
  public Page<User> list(
    @PageableDefault Pageable pageable) {
    return userService.list(pageable);
  }

  @ApiOperation("根据 ID 获取")
  @GetMapping("/{id}")
  @Override
  public User get(@PathVariable Long id) {
    return userService.get(id);
  }

  @ApiOperation("创建")
  @PostMapping
  @Override
  public User create(@RequestBody UserDTO userDTO) {
    return userService.create(userDTO);
  }

  @ApiOperation("批量创建")
  @PostMapping("/batch")
  @Override
  public List<User> createAll(@RequestBody List<UserDTO> userDTOS) {
    return userService.createAll(userDTOS);
  }

  @ApiOperation("根据 ID 更新")
  @PutMapping("/{id}")
  @Override
  public User update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
    return userService.update(id, userDTO);
  }

  @ApiOperation("根据 ID 删除")
  @DeleteMapping("/{id}")
  @Override
  public void delete(@PathVariable Long id) {
    userService.delete(id);
  }

  @ApiOperation("批量删除")
  @FResponseStatus
  @PostMapping("/delete")
  @Override
  public void deleteAll(@RequestBody List<Long> ids) {
    userService.deleteAll(ids);
  }

  @ApiOperation("切换封禁状态")
  @PostMapping("/{id}/enabled")
  public void toggleEnabled(@PathVariable Long id) {
    userService.toggleEnabled(id);
  }
}
