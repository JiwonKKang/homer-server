package com.dbs.homer.service;

import com.dbs.homer.controller.dto.ManagerDTO;
import com.dbs.homer.controller.response.ManagerResponse;
import com.dbs.homer.repository.ManagerRepository;
import com.dbs.homer.repository.domain.Manager;
import com.dbs.homer.repository.domain.ManagerBoost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    public List<ManagerDTO> getManagerList() {
        return managerRepository.findAll().stream().map(manager -> {
            List<ManagerBoost> managerBoosts = managerRepository.findBoostByManagerId(manager.getManagerId());
            return ManagerDTO.of(manager, managerBoosts);
        }).collect(Collectors.toList());
    }

}
