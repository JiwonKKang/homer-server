package com.dbs.homer.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rival {

    private String email;
    private String ownerName;
    private Integer squadId;

}
