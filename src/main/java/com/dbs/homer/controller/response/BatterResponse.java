package com.dbs.homer.controller.response;

import com.dbs.homer.repository.domain.Batter;

public record BatterResponse(
        String firstName,
        String lastName,
        String clubName,
        String position,
        int primaryNum,
        int gamePlayed,
        int homeruns,
        int plates
) {

    public static BatterResponse from(Batter batter) {
        return new BatterResponse(
                batter.getFirstName(),
                batter.getLastName(),
                batter.getClubName(),
                batter.getPosition(),
                batter.getPrimaryNum(),
                batter.getGamePlayed(),
                batter.getHomeruns(),
                batter.getPlates()
        );
    }

}
