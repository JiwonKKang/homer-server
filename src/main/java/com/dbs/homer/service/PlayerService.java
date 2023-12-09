package com.dbs.homer.service;

import com.dbs.homer.repository.PlayerRepository;
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

    public void findAll() {
        List<Player> players = playerRepository.findAll();

        log.info("players - {}", players);

    }

}
