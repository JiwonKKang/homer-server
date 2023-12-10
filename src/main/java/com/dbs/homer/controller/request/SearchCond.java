package com.dbs.homer.controller.request;

public record SearchCond(
        String playerName,
        Integer clubId,
        Integer position
) {

}
