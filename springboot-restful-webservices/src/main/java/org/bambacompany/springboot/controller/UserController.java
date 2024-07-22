package org.bambacompany.springboot.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.bambacompany.springboot.dto.UserDto;
import org.bambacompany.springboot.service.IUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "Create User, Update User, Get All Users, Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private IUserService _userService;
    public static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Operation(
            summary = "Create User REST API",
            description = "CREATE User REST API is used to save user in database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    ResponseEntity<UserDto> createRequest(@RequestBody UserDto user){
        log.info("Creating user: {}", user);
      UserDto saveUser = _userService.createUser(user);
      return new ResponseEntity<>(saveUser, HttpStatus.CREATED);

    }

    @Operation(
            summary = "Fetch  All User REST API",
            description = "Fetch All user REST API is used to fetch user in database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 FETCHED"
    )
    @GetMapping
    ResponseEntity<List<UserDto>> getAllUser(){
    List<UserDto> userDto = _userService.findAllUser();
        log.info("Found {} users", userDto.size());
        log.info("Returning {} users", userDto);
    return new  ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable Long id)  {
        Optional<UserDto> user = Optional.ofNullable(_userService.getUserById(id));
        if (user.isPresent()) {
            log.info("Found user: {}", user.get());
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody @Valid UserDto user) {
        user.setId(id);
        UserDto updatedUser = _userService.updateUser(user);
        log.info("Updating user: {}", updatedUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        _userService.deleteUser(id);
        log.info("Deleting user: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
