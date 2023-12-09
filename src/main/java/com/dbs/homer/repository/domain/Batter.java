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

    private String firstName;
    private String lastName;
    private String clubName;
    private int position;
    private int gamePlayed;
    private int homeruns;
    private int plates;

    public String getPosition() {
        return BaseballPosition.getByCode(position).getDescription();
    }

}
