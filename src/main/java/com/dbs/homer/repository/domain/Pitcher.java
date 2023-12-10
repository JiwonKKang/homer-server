package com.dbs.homer.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pitcher {

    private Integer playerId;
    private String firstName;
    private String lastName;
    private String clubName;
    private String playerPhoto;
    private int primaryNum;
    private int position;
    private int gamePlayed;
    private int innings;
    private int wins;
    private int losses;
    private int earnedRuns;

    public void update(int earnedRuns, boolean win) {
        this.gamePlayed += 1;
        this.innings += 9;
        if(win) this.wins += 1;
        else this.losses += 1;
        this.earnedRuns = earnedRuns;
    }

}
