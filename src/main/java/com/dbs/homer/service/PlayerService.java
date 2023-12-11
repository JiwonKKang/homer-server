package com.dbs.homer.service;

import com.dbs.homer.controller.dto.ManagerDTO;
import com.dbs.homer.controller.dto.MySquad;
import com.dbs.homer.controller.request.SearchCond;
import com.dbs.homer.repository.ManagerRepository;
import com.dbs.homer.repository.PlayerRepository;
import com.dbs.homer.repository.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final ManagerRepository managerRepository;

    public MySquad findPlayersBySquadId(Integer squadId) {
        List<Batter> batters = playerRepository.findBatterBySquadId(squadId);
        Pitcher pitcher = playerRepository.findPitcherBySquadId(squadId);
        Manager manager = managerRepository.findManagerBySquadId(squadId);

        List<ManagerBoost> managerBoosts = managerRepository.findBoostByManagerId(manager.getManagerId());


        return MySquad.of(batters, pitcher, ManagerDTO.of(manager,managerBoosts));

    }

    public List<Player> searchPlayer(SearchCond cond) {
        return playerRepository.searchPlayer(cond);
    }

}
