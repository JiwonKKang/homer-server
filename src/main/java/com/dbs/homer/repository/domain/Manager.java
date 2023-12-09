package com.dbs.homer.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Manager {

    private String name;
    private String photo;
    private int pitcherBoost;
    private int batterBoost;

}
