package com.dbs.homer.controller.dto;

import com.dbs.homer.repository.domain.Rival;
import org.springframework.data.relational.core.sql.In;

public record UserDTO(
        String email,
        String ownerName,
        Integer squadId,
        Integer userId
) {

    public static UserDTO of(String email, String ownerName, Integer squadId, Integer userId) {
        return new UserDTO(
                email,
                ownerName,
                squadId,
                userId
        );
    }

    public static UserDTO from(Rival rival) {
        return new UserDTO(
                rival.getEmail(),
                rival.getOwnerName(),
                rival.getSquadId(),
                rival.getUserId()
        );
    }
}
