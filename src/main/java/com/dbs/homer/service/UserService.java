package com.dbs.homer.service;

import com.dbs.homer.controller.dto.UserDTO;
import com.dbs.homer.repository.SquadRepository;
import com.dbs.homer.repository.UserRepository;
import com.dbs.homer.repository.domain.Squad;
import com.dbs.homer.repository.domain.SquadStatistic;
import com.dbs.homer.repository.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SquadRepository squadRepository;

    public List<UserDTO> findRivalUser(Integer userId) {
        return userRepository.findRivalUsers(userId).stream().map(UserDTO::from).toList();
    }

    public SquadStatistic getStatisticByUserId(Integer userId) {
        return userRepository.getStatisticBySquadId(userId);
    }

    public UserDTO findByUserId(Integer userId) {

        User userInfo = userRepository.findByUserId(userId);

        try {
            Squad squad = squadRepository.findById(userInfo.getUserId());
            return UserDTO.of(userInfo.getEmail(), userInfo.getOwnerName(), squad.getSquadId(), userInfo.getUserId());
        } catch (Exception e) {
            return UserDTO.of(userInfo.getEmail(), userInfo.getOwnerName(),null, userInfo.getUserId());
        }
    }
}
