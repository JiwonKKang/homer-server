package com.dbs.homer.controller;

import com.dbs.homer.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/match/{homeId}")
    public HashMap<Integer, List<String>> playGame(@PathVariable Integer homeId, Integer awayId) {
        return gameService.playGame(homeId, awayId);
    }

}
