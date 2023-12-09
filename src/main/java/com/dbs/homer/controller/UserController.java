package com.dbs.homer.controller;

import com.dbs.homer.controller.response.SquadStatisticResponse;
import com.dbs.homer.controller.response.UserResponse;
import com.dbs.homer.repository.domain.SquadStatistic;
import com.dbs.homer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.In;
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

    @GetMapping("/rivals/{userId}")
    public ResponseEntity<List<UserResponse>> rivalUsers(@PathVariable Integer userId) {
        List<UserResponse> responses = userService.findRivalUser(userId).stream().map(UserResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUserInfo(@PathVariable Integer userId) {
        UserResponse response = UserResponse.from(userService.findByUserId(userId));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/statistics/{userId}")
    public ResponseEntity<SquadStatisticResponse> squadStatistic(@PathVariable Integer userId) {
        SquadStatisticResponse response = SquadStatisticResponse.from(userService.getStatisticByUserId(userId));

        return ResponseEntity.ok(response);
    }
}
