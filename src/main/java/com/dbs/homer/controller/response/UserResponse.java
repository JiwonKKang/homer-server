package com.dbs.homer.controller.response;

import com.dbs.homer.repository.domain.User;

public record UserResponse(
        Integer squadId,
        String email,
        String ownerName
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getSquadId(),
                user.getEmail(),
                user.getOwnerName()
        );
    }
}
