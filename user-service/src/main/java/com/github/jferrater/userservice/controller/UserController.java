package com.github.jferrater.userservice.controller;

import com.github.jferrater.userservice.model.UserDto;
import com.github.jferrater.userservice.repository.document.User;
import com.github.jferrater.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

/**
 * @author joffryferrater
 */
@RestController
public class UserController {

    private UserService userService;
    private ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = convertToUser(userDto);
        UserDto response = convertToUserDto(userService.createUser(user));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        User user = convertToUser(userDto);
        UserDto response = convertToUserDto(userService.updateUser(user));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<UserDto> findUserByOrganizationAndUsername(@RequestParam("organization") @Nullable String organization,
          @RequestParam("username") String username) {
        User user = userService.findUserByOrganizationAndUsername(organization, username);
        UserDto userDto = convertToUserDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private UserDto convertToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private User convertToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
