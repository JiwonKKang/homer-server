package com.dbs.homer.repository;

import com.dbs.homer.repository.domain.Manager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ManagerRepository {

    private final JdbcTemplate template;

    public ManagerRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public Manager findManagerBySquadId(Integer squadId) {
        String sql = """
                SELECT m.name, m.manager_image, m.batter_boost, m.pitcher_boost, m.manager_id
                FROM manager m JOIN squad s ON m.manager_id = s.manager_id
                WHERE s.squad_id = ?;
                """;
        return template.queryForObject(sql, managerRowMapper(), squadId);
    }

    public List<Manager> findAll() {
        String sql = """
                SELECT *
                FROM manager
                """;

        return template.query(sql, managerRowMapper());
    }

    private RowMapper<Manager> managerRowMapper() {
        return (rs, rowNum) -> Manager.builder()
                .managerId(rs.getInt("manager_id"))
                .name(rs.getString("name"))
                .photo(rs.getString("manager_image"))
                .batterBoost(rs.getInt("batter_boost"))
                .pitcherBoost(rs.getInt("pitcher_boost"))
                .build();
    }


}
