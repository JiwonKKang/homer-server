package com.dbs.homer.service;

import com.dbs.homer.controller.response.ManagerResponse;
import com.dbs.homer.repository.ManagerRepository;
import com.dbs.homer.repository.domain.Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    public List<Manager> getManagerList() {
        return managerRepository.findAll();
    }

}
