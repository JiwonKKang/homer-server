package com.dbs.homer.service;

import com.dbs.homer.repository.PlayerRepository;
import com.dbs.homer.repository.domain.Batter;
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

    public List<Batter> findBattersBySquadId(Integer squadId) {

        return playerRepository.findBatterBySquadId(squadId);

    }

    public List<Pitcher> findPitchersBySquadId(Integer squadId) {

        return playerRepository.findPitcherBySquadId(squadId);

    }

}
