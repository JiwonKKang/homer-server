package com.dbs.homer.service;

import com.dbs.homer.repository.UserRepository;
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

    public List<User> findRivalUser(Integer userId) {
        return userRepository.findRivalUsers(userId);
    }

}
