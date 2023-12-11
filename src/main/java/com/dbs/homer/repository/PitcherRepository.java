package com.dbs.homer.repository;

import com.dbs.homer.controller.dto.PlayerDTO;
import com.dbs.homer.repository.domain.Pitcher;
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

        String sql = "UPDATE pitcher p SET p.player_id = :playerId, p.earned_runs = 0, p.games_played= 0, p.innings= 0, p.losses= 0, p.wins= 0 WHERE squad_id = :squadId AND position = :position";

        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("playerId", dto.playerId())
                .addValue("squadId", dto.squadId())
                .addValue("position", dto.position());

        template.update(sql, param);
    }

    public void updateRecord(Pitcher pitcher) {
        String sql = "update pitcher set games_played = :gamesPlayed, innings = :innings, wins = :wins, losses = :losses, earned_runs = :earnedRuns\n"
                + "where player_id = :playerId and squad_id = :squadId";

        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("gamesPlayed", pitcher.getGamePlayed())
                .addValue("innings", pitcher.getInnings())
                .addValue("wins", pitcher.getWins())
                .addValue("losses", pitcher.getLosses())
                .addValue("earnedRuns", pitcher.getEarnedRuns())
                .addValue("playerId", pitcher.getPlayerId())
                .addValue("squadId", pitcher.getSquadId());

        template.update(sql,param);
    }


}
