package com.dbs.homer.service;

import com.dbs.homer.controller.request.SearchCond;
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

        SearchCond cond = new SearchCond("david", "los angeles Angels", 6);
        playerService.searchPlayer(cond);
        //When

        //Then

    }

}