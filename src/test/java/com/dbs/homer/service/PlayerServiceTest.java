package com.dbs.homer.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class PlayerServiceTest {

    @Autowired
    PlayerService playerService;

    @Test
    public void findBatters() throws Exception {

        //Given
        playerService.findBattersBySquadId(0);

        //When

        //Then

    }

    @Test
    public void findPitcher() throws Exception {

        //Given
        playerService.findPitchersBySquadId(0);
        //When

        //Then

    }

}