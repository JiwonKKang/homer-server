package com.dbs.homer.controller.response;

import com.dbs.homer.controller.dto.ManagerDTO;
import com.dbs.homer.repository.domain.ManagerBoost;

import java.util.List;

public record ManagerResponse(
        Integer managerId,
        String name,
        String photo,
        List<ManagerBoost> managerBoosts
) {
    public static ManagerResponse from(ManagerDTO manager) {
        return new ManagerResponse(
                manager.managerId(),
                manager.name(),
                manager.photo(),
                manager.managerBoosts()
        );
    }

}
