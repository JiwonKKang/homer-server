package com.dbs.homer.service;

import com.dbs.homer.controller.dto.MySquad;
import com.dbs.homer.controller.request.SearchCond;
import com.dbs.homer.repository.ManagerRepository;
import com.dbs.homer.repository.PlayerRepository;
import com.dbs.homer.repository.domain.Batter;
import com.dbs.homer.repository.domain.Manager;
import com.dbs.homer.repository.domain.Pitcher;
import com.dbs.homer.repository.domain.Player;
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

        return MySquad.of(batters, pitcher, manager);

    }

    public List<Player> searchPlayer(SearchCond cond) {
        return playerRepository.searchPlayer(cond);
    }

}
