package com.dbs.homer.repository.domain;

import com.dbs.homer.controller.request.SquadRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Squad {

    private Integer squadId;
    private Integer UserId;
    private Integer ManagerId;

    private Squad(Integer userId, Integer managerId) {
        UserId = userId;
        ManagerId = managerId;
    }

    public static Squad of(Integer userId, Integer managerId) {
        return new Squad(
                userId,
                managerId
        );
    }
}
