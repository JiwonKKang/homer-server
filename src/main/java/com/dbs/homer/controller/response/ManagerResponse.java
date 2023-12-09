package com.dbs.homer.controller.response;

import com.dbs.homer.repository.ManagerRepository;
import com.dbs.homer.repository.domain.Manager;

public record ManagerResponse(
        String name,
        String photo,
        int pitcherBoost,
        int batterBoost
) {
    public static ManagerResponse from(Manager manager) {
        return new ManagerResponse(
                manager.getName(),
                manager.getPhoto(),
                manager.getPitcherBoost(),
                manager.getBatterBoost()
        );
    }
}
