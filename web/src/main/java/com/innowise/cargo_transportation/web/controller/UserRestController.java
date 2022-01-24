package com.innowise.cargo_transportation.web.controller;

import com.innowise.cargo_transportation.core.dto.request.UserParamsRequest;
import com.innowise.cargo_transportation.core.dto.request.UserRequest;
import com.innowise.cargo_transportation.core.dto.response.UserListResponse;
import com.innowise.cargo_transportation.core.dto.response.UserResponse;
import com.innowise.cargo_transportation.core.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> create(@RequestBody @Valid UserRequest userRequest){
        Long userId = userService.createUser(userRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userId).toUri();

        return ResponseEntity.created(location)
                .body(location);
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable("id") Long id){
        return userService.findUserById(id);
    }

    @GetMapping
    public UserListResponse findByFilter(UserParamsRequest userParamsRequest){
        return userService.findList(userParamsRequest);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam("id") Long id){
        userService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public void updateUserById(@PathVariable("id") Long id, @RequestBody UserRequest userRequest){

         userService.updateUser(id, userRequest);
    }



}
