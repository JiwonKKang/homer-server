package com.dbs.homer.controller.dto;

import com.dbs.homer.repository.domain.Manager;
import com.dbs.homer.repository.domain.ManagerBoost;

import java.util.List;

public record ManagerDTO(
        Integer managerId,
        String photo,
        String name,
        List<ManagerBoost> managerBoosts
) {
    public static ManagerDTO of(Manager manager, List<ManagerBoost> managerBoosts) {
        return new ManagerDTO(
                manager.getManagerId(),
                manager.getPhoto(),
                manager.getName(),
                managerBoosts
        );
    }

}
