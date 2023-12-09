package com.dbs.homer.controller;

import com.dbs.homer.controller.dto.MySquad;
import com.dbs.homer.controller.request.SearchCond;
import com.dbs.homer.controller.response.BatterResponse;
import com.dbs.homer.controller.response.MySquadResponse;
import com.dbs.homer.controller.response.PitcherResponse;
import com.dbs.homer.controller.response.PlayerResponse;
import com.dbs.homer.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/squads/{squadId}")
    public ResponseEntity<MySquadResponse> squad(@PathVariable Integer squadId) {

        MySquadResponse response = MySquadResponse.from(playerService.findPlayersBySquadId(squadId));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/players")
    public ResponseEntity<List<PlayerResponse>> searchPlayer(@RequestParam SearchCond cond) {

        if (cond.clubName().isEmpty() && cond.position().describeConstable().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        List<PlayerResponse> responses = playerService.searchPlayer(cond).stream()
                .map(PlayerResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

}
