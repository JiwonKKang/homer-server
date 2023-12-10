package com.dbs.homer.repository;

import com.dbs.homer.repository.domain.Squad;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class SquadRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;


    public SquadRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("squad")
                .usingGeneratedKeyColumns("squad_id");
    }

    public Squad save(Squad squad) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(squad);
        Number key = jdbcInsert.executeAndReturnKey(param);
        squad.setSquadId(key.intValue());
        return squad;
    }

    public void update(Integer squadId, Integer managerId) {
        String sql = "UPDATE squad SET manager_id = :managerId WHERE squad_id = :squadId";

        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("managerId", managerId)
                .addValue("squadId", squadId);

        template.update(sql, param);
    }

    public Squad findById(Integer userId) {
        String sql = """
                SELECT *
                FROM squad
                WHERE user_id = :userId
                """;

        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("userId", userId);

        return template.queryForObject(sql, param, squadRowMapper());
    }

    private RowMapper<Squad> squadRowMapper() {
        return BeanPropertyRowMapper.newInstance(Squad.class);
    }
}
