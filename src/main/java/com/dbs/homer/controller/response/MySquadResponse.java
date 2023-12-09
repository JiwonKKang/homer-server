package com.dbs.homer.controller.response;

import com.dbs.homer.controller.dto.MySquad;

import java.util.List;
import java.util.stream.Collectors;

public record MySquadResponse(
        List<BatterResponse> batterResponses,
        PitcherResponse pitcherResponse,
        ManagerResponse managerResponse
) {

    public static MySquadResponse from(MySquad mySquad) {
        return new MySquadResponse(
                mySquad.batters().stream().map(BatterResponse::from).collect(Collectors.toList()),
                PitcherResponse.from(mySquad.pitcher()),
                ManagerResponse.from(mySquad.manager())
        );
    }

}
