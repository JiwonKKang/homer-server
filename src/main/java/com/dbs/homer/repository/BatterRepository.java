package com.dbs.homer.repository;

import com.dbs.homer.controller.request.PlayerDTO;
import com.dbs.homer.repository.domain.Batter;
import com.dbs.homer.repository.domain.Player;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Set;

@Repository
public class BatterRepository {

    private final JdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;


    public BatterRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("batter")
                .usingColumns("player_id", "squad_id", "position");
    }

    public void save(PlayerDTO dto) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(dto);
        jdbcInsert.execute(param);
    }

    public void saveAll(Set<PlayerDTO> batters) {
        String sql = "INSERT INTO batter (player_id, squad_id, position) VALUES (?, ?, ?)";

        template.batchUpdate(sql,
                batters,
                batters.size(),
                (PreparedStatement ps, PlayerDTO batter) -> {
            ps.setInt(1, batter.playerId());
            ps.setInt(2, batter.squadId());
            ps.setInt(3, batter.position());
                });
    }

    public void updateAll(Set<PlayerDTO> batters) {
        String sql = "UPDATE batter SET player_id = ? where squad_id = ? AND position = ?";
        template.batchUpdate(sql,
                batters,
                batters.size(),
                (PreparedStatement ps, PlayerDTO batter) -> {
            ps.setInt(1, batter.playerId());
            ps.setInt(2, batter.squadId());
            ps.setInt(3, batter.position());
        });
    }

}
