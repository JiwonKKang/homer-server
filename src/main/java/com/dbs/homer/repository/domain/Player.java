package com.dbs.homer.repository.domain;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Player {

    private Integer id;
    private String firstName;
    private String lastName;
    private String playerPhoto;
    private int primaryNum;
    private int clubId;
    private int stuff;
    private int control;
    private int contact;
    private int power;
    private int discipline;


}
