package com.dbs.homer.controller;

import com.dbs.homer.controller.response.BatterResponse;
import com.dbs.homer.controller.response.PitcherResponse;
import com.dbs.homer.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/squad/{squadId}/batters")
    public ResponseEntity<List<BatterResponse>> getBattersInfo(@PathVariable Integer squadId) {
        List<BatterResponse> responses = playerService.findBattersBySquadId(squadId).stream()
                .map(BatterResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/squad/{squadId}/pitchers")
    public ResponseEntity<List<PitcherResponse>> getPitcherInfo(@PathVariable Integer squadId) {
        List<PitcherResponse> responses = playerService.findPitchersBySquadId(squadId).stream()
                .map(PitcherResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }
}
