package com.dbs.homer.service;

import com.dbs.homer.controller.request.PlayerDTO;
import com.dbs.homer.controller.request.PlayerRequest;
import com.dbs.homer.controller.request.SquadRequest;
import com.dbs.homer.controller.request.SquadUpdateRequest;
import com.dbs.homer.repository.BatterRepository;
import com.dbs.homer.repository.PitcherRepository;
import com.dbs.homer.repository.SquadRepository;
import com.dbs.homer.repository.domain.Squad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SquadService {

    private final BatterRepository batterRepository;
    private final PitcherRepository pitcherRepository;
    private final SquadRepository squadRepository;

    @Transactional
    public void createSquad(SquadRequest req) {
        Squad squad = Squad.of(req.UserId(), req.managerId());
        Integer squadId = squadRepository.save(squad).getSquadId();

        batterRepository.saveAll(extractBatters(req.players(), squadId));
        pitcherRepository.save(extractPitcher(req.players(), squadId));
    }

    @Transactional
    public void updateSquad(SquadUpdateRequest req) {
        Integer squadId = req.squadId();

        squadRepository.update(req.squadId(), req.managerId());
        batterRepository.updateAll(extractBatters(req.players(), squadId));
        pitcherRepository.update(extractPitcher(req.players(), squadId));
    }

    private static PlayerDTO extractPitcher(Set<PlayerRequest> req, Integer squadId) {
        PlayerRequest pitcherReq = req.stream()
                .filter(player -> player.position() < 2)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("스쿼드에서 투수를 찾을 수 없습니다."));

        return PlayerDTO.of(pitcherReq, squadId);
    }

    private static Set<PlayerDTO> extractBatters(Set<PlayerRequest> req, Integer squadId) {
        return req.stream()
                .filter(player -> player.position() > 1)
                .map(batter -> PlayerDTO.of(batter, squadId))
                .collect(Collectors.toSet());
    }
}
