package com.dbs.homer.controller;

import com.dbs.homer.controller.response.UserResponse;
import com.dbs.homer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<UserResponse>> rivalUsers(@PathVariable Integer userId) {
        List<UserResponse> responses = userService.findRivalUser(userId).stream().map(UserResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

}
