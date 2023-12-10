package com.dbs.homer.controller.dto;

import com.dbs.homer.controller.request.PlayerRequest;

public record PlayerDTO(
        Integer playerId,
        Integer position,
        Integer squadId
) {
    public static PlayerDTO of(PlayerRequest req, Integer squadId) {
        return new PlayerDTO(
                req.playerId(),
                req.position(),
                squadId
        );
    }
}
