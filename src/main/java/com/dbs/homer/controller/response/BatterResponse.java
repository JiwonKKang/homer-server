package com.dbs.homer.controller.response;

import com.dbs.homer.repository.domain.Batter;

public record BatterResponse(
        Integer playerId,
        String firstName,
        String lastName,
        String clubName,
        String playerPhoto,
        int position,
        int primaryNum,
        int gamePlayed,
        int homeruns,
        int plates
) {

    public static BatterResponse from(Batter batter) {
        return new BatterResponse(
                batter.getPlayerId(),
                batter.getFirstName(),
                batter.getLastName(),
                batter.getClubName(),
                batter.getPlayerPhoto(),
                batter.getPosition(),
                batter.getPrimaryNum(),
                batter.getGamePlayed(),
                batter.getHomeruns(),
                batter.getPlates()
        );
    }

}
