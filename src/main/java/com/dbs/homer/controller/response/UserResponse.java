package com.dbs.homer.controller.response;

import com.dbs.homer.controller.dto.UserDTO;

public record UserResponse(
        Integer squadId,
        String email,
        String ownerName
) {
    public static UserResponse from(UserDTO user) {
        return new UserResponse(
                user.squadId(),
                user.email(),
                user.ownerName()
        );
    }
}
