package com.dbs.homer.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Manager {

    private Integer managerId;
    private String name;
    private String photo;

}
