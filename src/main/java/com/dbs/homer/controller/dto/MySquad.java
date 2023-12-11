package com.dbs.homer.controller.dto;

import com.dbs.homer.repository.domain.Batter;
import com.dbs.homer.repository.domain.Manager;
import com.dbs.homer.repository.domain.Pitcher;

import java.util.List;

public record MySquad(
        List<Batter> batters,
        Pitcher pitcher,
        ManagerDTO manager
) {

    public static MySquad of(List<Batter> batters, Pitcher pitcher, ManagerDTO manager) {
        return new MySquad(
                batters,
                pitcher,
                manager
        );
    }
}
