package com.dbs.homer.controller.request;

import java.util.Set;

public record SquadUpdateRequest(
        Integer squadId,
        Integer managerId,
        Set<PlayerRequest> players
) {
}
