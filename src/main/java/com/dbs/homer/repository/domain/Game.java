package com.dbs.homer.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {
    private Integer gameId;
    private LocalDate startDate;
    private Integer homeId;
    private Integer awayId;
    private String winTeam;
    private int homeScore;
    private int awayScore;
}
