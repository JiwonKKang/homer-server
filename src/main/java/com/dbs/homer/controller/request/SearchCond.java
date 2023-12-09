package com.dbs.homer.controller.request;

public record SearchCond(
        String playerName,
        String clubName,
        Integer position
) {

}
