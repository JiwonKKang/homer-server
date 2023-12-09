package com.dbs.homer.controller.response;

import com.dbs.homer.repository.domain.User;

public record UserResponse(
        String ownerName
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getOwnerName()
        );
    }
}
