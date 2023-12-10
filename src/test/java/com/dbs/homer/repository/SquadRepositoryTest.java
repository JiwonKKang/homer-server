package com.dbs.homer.repository;

import com.dbs.homer.repository.domain.Squad;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SquadRepositoryTest {

    @Autowired
    SquadRepository squadRepository;

    @Test
    public void given_when_then() throws Exception {

        //Given

        Squad squad = new Squad(0, 1);

        Squad save = squadRepository.save(squad);
        System.out.println("save = " + save);
        //When

        //Then

    }
}