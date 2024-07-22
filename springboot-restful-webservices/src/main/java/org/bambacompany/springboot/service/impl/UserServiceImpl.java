package org.bambacompany.springboot.service.impl;

import lombok.AllArgsConstructor;
import org.bambacompany.springboot.dto.UserDto;
import org.bambacompany.springboot.entity.User;
import org.bambacompany.springboot.exceptions.EmailAlreadyExistException;
import org.bambacompany.springboot.exceptions.ResourceNotFoundException;
import org.bambacompany.springboot.mapper.AutoUserMapper;
import org.bambacompany.springboot.mapper.UserMapper;
import org.bambacompany.springboot.repository.IUserRepository;
import org.bambacompany.springboot.service.IUserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private ModelMapper modelMapper;
    private IUserRepository _userRepository;
    public static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDto createUser(UserDto userDto) {
        Optional<User> emailUser = _userRepository.findByEmail(userDto.getEmail());
        if (emailUser.isPresent()) {
            log.error("Email already exist");
            throw new EmailAlreadyExistException("email Already Exists for User");
        }

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = _userRepository.save(user);

        return AutoUserMapper.MAPPER.mapToUserDto(savedUser);
        //Convert userDto into User JPA
        //User user = UserMapper.mapUserToUserDto(userDto);
        //deuxième façon de faire la conversione avec les mappers
       // modelMapper.map(userDto, User.class);

        //Convert User to UserDto
        //User user = UserMapper.mapUserToUserDto(userDto);


        //User savedUser = _userRepository.save(user);
       // log.info("User created: " + savedUser);

        //Convert DTO to User
        //UserMapper.mapDtoToUser(savedUser);
       // return modelMapper.map(savedUser, UserDto.class);

    }

    @Override
    public UserDto getUserById(Long id) {
        User optionaUser = _userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("User with id " + id + " not found", "id", id, "\"User with id\" +id+ \" not found\"")
        );
       // User user = optionaUser.get();
       // var user = Optional.of(_userRepository.findById(id).get());
       // return UserMapper.mapDtoToUser( user.orElseThrow());
        log.info("User found: " + optionaUser);
       // return modelMapper.map(user, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(optionaUser);
    }

    @Override
    public List<UserDto> findAllUser() {
        List<User> users = _userRepository.findAll();
//        return users.stream().map(UserMapper::mapDtoToUser)
//                .collect(Collectors.toList());
        log.info("Users found: " + users);
        //return users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return users.stream().map(AutoUserMapper.MAPPER::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        User existingUser = _userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("User with id " + id + " not found", "id", id, "Inexistant user" +id+ " not found")
        );
    _userRepository.deleteById(id);
       log.info("User deleted: " + id);
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = _userRepository.findById(user.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("User with id " + user.getId() + " not found", "id", user.getId(), "User with id" +user.getId() + " not found")
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        User updatedUser = _userRepository.save(existingUser);
        log.info("User updated: " + updatedUser);
       // return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }


}
