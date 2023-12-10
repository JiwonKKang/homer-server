package com.dbs.homer.controller.response;

import com.dbs.homer.repository.domain.Pitcher;

public record PitcherResponse(
        Integer playerId,
        String firstName,
        String lastName,
        String clubName,
        String playerPhoto,
        int position,
        int primaryNum,
        int gamePlayed,
        int innings,
        int wins,
        int losses,
        double era
) {
    public static PitcherResponse from(Pitcher pitcher) {
        return new PitcherResponse(
                pitcher.getPlayerId(),
                pitcher.getFirstName(),
                pitcher.getLastName(),
                pitcher.getClubName(),
                pitcher.getPlayerPhoto(),
                pitcher.getPosition(),
                pitcher.getPrimaryNum(),
                pitcher.getGamePlayed(),
                pitcher.getInnings(),
                pitcher.getWins(),
                pitcher.getLosses(),
                pitcher.getEra()
        );
    }

}
