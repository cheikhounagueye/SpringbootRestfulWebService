package org.bambacompany.springboot.service;

import org.bambacompany.springboot.dto.UserDto;
import org.bambacompany.springboot.entity.User;

import java.util.List;

public interface IUserService {
    UserDto createUser(UserDto user);
    UserDto getUserById(Long id);
    List<UserDto> findAllUser();
    void deleteUser(Long id);
    UserDto updateUser(UserDto user);
}
