package com.dbs.homer.controller.request;

public record PlayerRequest(
        Integer playerId,
        Integer position
) {
}
