package com.dbs.homer.repository.domain;

import com.dbs.homer.controller.dto.BaseballPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Batter {

    private Integer playerId;
    private String firstName;
    private String lastName;
    private String clubName;
    private String playerPhoto;
    private int primaryNum;
    private int position;
    private int gamePlayed;
    private int homeruns;
    private int plates;
    private int hits;
    private double avg;

    public void update(boolean hit, boolean homerun){
        this.plates += 1;
        if(hit) this.hits += 1;
        if(homerun) this.homeruns += 1;
    }
}
