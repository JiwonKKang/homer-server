package com.dbs.homer.controller.response;

import com.dbs.homer.repository.domain.Batter;
import com.dbs.homer.repository.domain.Pitcher;

public record PitcherResponse(
        String firstName,
        String lastName,
        String clubName,
        String position,
        int gamePlayed,
        int innings,
        int wins,
        int losses
) {
    public static PitcherResponse from(Pitcher pitcher) {
        return new PitcherResponse(
                pitcher.getFirstName(),
                pitcher.getLastName(),
                pitcher.getClubName(),
                pitcher.getPosition(),
                pitcher.getGamePlayed(),
                pitcher.getInnings(),
                pitcher.getWins(),
                pitcher.getLosses()
        );
    }

}
