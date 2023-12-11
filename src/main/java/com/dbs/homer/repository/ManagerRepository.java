package com.dbs.homer.repository;

import com.dbs.homer.repository.domain.Manager;
import com.dbs.homer.repository.domain.ManagerBoost;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
                SELECT m.name, m.manager_image, m.manager_id
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

    public List<ManagerBoost> findBoostByManagerId(Integer managerId) {
        String sql = "SELECT ability, ability_value FROM manager_boost where manager_id = ?";

        return template.query(sql, managerBoostRowMapper(), managerId);
    }

    private RowMapper<Manager> managerRowMapper() {
        return (rs, rowNum) -> Manager.builder()
                .managerId(rs.getInt("manager_id"))
                .name(rs.getString("name"))
                .photo(rs.getString("manager_image"))
                .build();
    }

    private RowMapper<ManagerBoost> managerBoostRowMapper() {
        return BeanPropertyRowMapper.newInstance(ManagerBoost.class);
    }


}
