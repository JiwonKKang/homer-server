package com.dbs.homer.controller;

import com.dbs.homer.controller.request.SquadRequest;
import com.dbs.homer.controller.request.SquadUpdateRequest;
import com.dbs.homer.repository.domain.Squad;
import com.dbs.homer.service.SquadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SquadController {

    private final SquadService squadService;

    @PostMapping("/squads")
    public void makeSquad(@RequestBody SquadRequest request) {
        squadService.createSquad(request);
    }

    @PutMapping("/squads")
    public void updateSquad(@RequestBody SquadUpdateRequest request) {
        squadService.updateSquad(request);
    }
}
