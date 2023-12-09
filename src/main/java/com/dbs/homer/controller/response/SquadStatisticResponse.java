package com.dbs.homer.controller.response;

import com.dbs.homer.repository.domain.SquadStatistic;

public record SquadStatisticResponse(
        Double homeWinRate,
        Double awayWinRate,
        Double totalRate,
        Integer totalWins,
        Integer totalLoses
) {
    public static SquadStatisticResponse from(SquadStatistic statistic) {
        return new SquadStatisticResponse(
                statistic.getHomeWinRate(),
                statistic.getAwayWinRate(),
                statistic.getTotalRate(),
                statistic.getTotalWins(),
                statistic.getTotalGames() - statistic.getTotalWins()
        );
    }
}
