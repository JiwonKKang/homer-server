package com.dbs.homer.repository;

import com.dbs.homer.controller.request.PlayerDTO;
import com.dbs.homer.controller.request.PlayerRequest;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class PitcherRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;


    public PitcherRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("pitcher")
                .usingColumns("player_id", "squad_id", "position");
    }

    public void save(PlayerDTO dto) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(dto);
        jdbcInsert.execute(param);
    }

    public void update(PlayerDTO dto) {

        String sql = "UPDATE pitcher SET player_id = :playerId WHERE squad_id = :squadId AND position = :position";

        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("playerId", dto.playerId())
                .addValue("squadId", dto.squadId())
                .addValue("position", dto.position());

        template.update(sql, param);
    }

}
