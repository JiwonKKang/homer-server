package com.dbs.homer.controller.request;


import java.util.Set;

public record SquadRequest(
        Integer managerId,
        Integer UserId,
        Set<PlayerRequest> players
) {
}
