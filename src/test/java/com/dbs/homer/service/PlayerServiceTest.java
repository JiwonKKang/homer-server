package com.dbs.homer.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class PlayerServiceTest {

    @Autowired
    PlayerService playerService;

    @Test
    public void given_when_then() throws Exception {

        //Given
        playerService.findAll();

        //When

        //Then

    }


}