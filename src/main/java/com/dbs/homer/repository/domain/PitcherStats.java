package com.dbs.homer.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PitcherStats {

    private String firstName;
    private String lastName;
    private String playerPhoto;
    private int stuff;
    private int control;

}
