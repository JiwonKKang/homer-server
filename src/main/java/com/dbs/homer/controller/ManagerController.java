package com.dbs.homer.controller;

import com.dbs.homer.controller.response.ManagerResponse;
import com.dbs.homer.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping("/managers")
    public ResponseEntity<List<ManagerResponse>> managerList() {
        List<ManagerResponse> responses = managerService.getManagerList().stream()
                .map(ManagerResponse::from)
                .toList();

        return ResponseEntity.ok(responses);
    }
}
