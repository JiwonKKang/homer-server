package com.dbs.homer.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SquadStatistic {

    private Double homeWinRate;
    private Double awayWinRate;
    private Double totalRate;
    private Integer totalWins;
    private Integer totalGames;

}
