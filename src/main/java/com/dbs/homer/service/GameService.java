package com.dbs.homer.service;

import com.dbs.homer.repository.*;
import com.dbs.homer.repository.domain.Batter;
import com.dbs.homer.repository.domain.Game;
import com.dbs.homer.repository.domain.Pitcher;
import com.dbs.homer.repository.domain.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameService {

    private final PlayerRepository playerRepository;
    private final BatterRepository batterRepository;
    private final PitcherRepository pitcherRepository;
    private final GameRepository gameRepository;

    public HashMap<Integer, List<String>> playGame(Integer homeSquadId, Integer awaySquadId) {
        Pitcher homePitcher = playerRepository.findPitcherBySquadId(homeSquadId);
        List<Batter> homeBatters = playerRepository.findBatterBySquadId(homeSquadId);
        Pitcher awayPitcher = playerRepository.findPitcherBySquadId(awaySquadId);
        List<Batter> awayBatters = playerRepository.findBatterBySquadId(awaySquadId);
        HashMap<Integer, List<String>> results = new HashMap<Integer, List<String>>();

        int[] score = new int[2];
        int[] batterSequence = new int[2];
        Integer[] pitchers = new Integer[2];
        pitchers[0] = homePitcher.getPlayerId();
        pitchers[1] = awayPitcher.getPlayerId();

        for(int inning = 0; inning < 18; inning++) {
            int outCount = 0;
            int seq = inning % 2;

            List<Batter> batters;
            if(seq == 0) batters = awayBatters;
            else batters = homeBatters;

            Queue<Integer> base = new LinkedList<>();
            List<String> batterResult = new ArrayList<String>();

            while(outCount < 3) {
                if (batterSequence[seq] == 8)
                    batterSequence[seq] = 0;
                else batterSequence[seq]++;

                Batter batter = batters.get(batterSequence[seq]);
                Integer batterId = batter.getPlayerId();
                base.add(batterId);

                int result = pitcherVersusBatter(pitchers[seq], batterId);
                String resultDescription = null;
                switch (result) {
                    case 0 -> resultDescription = "삼진";
                    case 1 -> resultDescription = "아웃";
                    case 2 -> resultDescription = "안타";
                    case 3 -> resultDescription = "2루타";
                    case 4 -> resultDescription = "홈런";
                    case 5 -> resultDescription = "볼넷";
                }
                if (result == 0 || result == 1) {
                    batters.get(batterSequence[seq]).update(false, false);
                    outCount++;
                    base.remove(batterId);
                }
                else if(result == 2) {
                    batters.get(batterSequence[seq]).update(true, false);
                }
                else if(result == 3) {
                    base.add(0);
                    batters.get(batterSequence[seq]).update(true, false);
                }
                else if(result == 4) {
                    batters.get(batterSequence[seq]).update(true, true);
                    base.add(0);
                    base.add(0);
                    base.add(0);
                    base.add(0);
                }

                batterResult.add(batterSequence[seq] + "번 타자 " + batter.getFirstName() + " " + batter.getLastName()
                        + " " + resultDescription);
            }
            results.put(inning, batterResult);

            for(int i = base.size(); i > 3; i--) {
                if(base.remove() > 0) {
                    score[seq]++;
                }
            }

            List<String> addScore = results.get(inning);
            addScore.add(String.valueOf(score[seq]));
            results.put(inning, addScore);
            base.clear();
        }
        String winTeam;
        if(score[0] > score[1]) {
            winTeam = "away";
            homePitcher.update(score[0], false);
            awayPitcher.update(score[1], true);
        }
        else {
            winTeam = "home";
            homePitcher.update(score[0], true);
            awayPitcher.update(score[1], false);
        }

        gameRepository.save(LocalDate.now(), homeSquadId, awaySquadId, winTeam, score[1], score[0]);
        for(int i = 0; i < 9; i++) {
            batterRepository.updateRecord(homeBatters.get(i));
            batterRepository.updateRecord(awayBatters.get(i));
        }
        pitcherRepository.updateRecord(homePitcher);
        pitcherRepository.updateRecord(awayPitcher);

        return results;
    }

    public int pitcherVersusBatter(Integer pitcherId, Integer batterId) {
        Player pitcher = playerRepository.findPlayerById(pitcherId);
        Player batter = playerRepository.findPlayerById(batterId);

        int stuff = pitcher.getStuff();
        int control = pitcher.getControl();
        int contact = batter.getContact();
        int power = batter.getPower();
        int discipline = batter.getDiscipline();

        int hitRate = (contact * 10 + power * 2) / 10 - (stuff * 7 + control * 3) / 10;

        if (hitRate < 12) hitRate = 12;
        else if (hitRate > 42) hitRate = 42;

        int slugRate = power - stuff / 5;
        int bbRate = (100 - control) / 4 + (discipline / 6);

        if (bbRate > 40) bbRate = 40;

        int randBb = (int)(Math.random() * 100);
        int randHit = (int)(Math.random() * 100);

        int result;

        if (randBb < bbRate)
            result = 5;
        else if (randHit < hitRate)
            if (randHit * 4.5 < slugRate)
                result = 4;
            else if (randHit * 2 < slugRate)
                result = 3;
            else
                result = 2;
        else if (randHit < stuff / 1.4)
            result = 0;
        else
            result = 1;

        return result;
    }
}
