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

    private String firstName;
    private String lastName;
    private String clubName;
    private int position;
    private int gamePlayed;
    private int innings;
    private int wins;
    private int losses;

    public String getPosition() {
        return BaseballPosition.getByCode(position).getDescription();
    }

}
