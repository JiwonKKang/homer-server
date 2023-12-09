package com.dbs.homer.controller.response;

import com.dbs.homer.repository.domain.Player;

public record PlayerResponse(
        String firstName,
        String lastName,
        String playerPhoto,
        int primaryNum,
        int stuff,
        int control,
        int contact,
        int power,
        int discipline
) {

    public static PlayerResponse from(Player player) {
        return new PlayerResponse(
                player.getFirstName(),
                player.getLastName(),
                player.getPlayerPhoto(),
                player.getPrimaryNum(),
                player.getStuff(),
                player.getControl(),
                player.getContact(),
                player.getPower(),
                player.getDiscipline()
        );
    }

}
