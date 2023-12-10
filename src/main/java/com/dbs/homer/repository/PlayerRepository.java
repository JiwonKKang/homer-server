package com.dbs.homer.repository;

import com.dbs.homer.controller.request.SearchCond;
import com.dbs.homer.repository.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Slf4j
public class PlayerRepository {

    private final JdbcTemplate template;

    public PlayerRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public List<Batter> findBatterBySquadId(Integer squadId) {
        String sql = """
                SELECT p.first_name, p.last_name, c.name, bs.position, bs.games_played, bs.homeruns, bs.plates, p.primary_num,
                CASE
                WHEN bs.plates = 0 THEN 0
                ELSE bs.hits / bs.plates END AS avg
                FROM player p\s
                JOIN batter bs ON p.id = bs.player_id
                JOIN club c ON p.club_id = c.club_id 
                WHERE bs.squad_id = ?
                AND bs.position NOT IN (0, 1);""";
        return template.query(sql, batterRowMapper(), squadId);
    }

    public Pitcher findPitcherBySquadId(Integer squadId) {
        String sql = """
                SELECT p.first_name, p.last_name, c.name, ps.position, ps.games_played, ps.innings, ps.wins, ps.losses, p.primary_num,
                CASE
                WHEN ps.innings = 0 THEN 0
                ELSE (ps.earned_runs / ps.innings * 9) END AS era
                FROM player p\s
                JOIN pitcher ps ON p.id = ps.player_id
                JOIN club c ON p.club_id = c.club_id
                WHERE ps.squad_id = ?
                AND ps.position IN (0, 1);
                """;

        return template.queryForObject(sql, pitcherRowMapper(), squadId);
    }

    private RowMapper<Pitcher> pitcherRowMapper() {
        return (rs, rowNum) -> Pitcher.builder()
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .position(rs.getInt("position"))
                .primaryNum(rs.getInt("primary_num"))
                .clubName(rs.getString("name"))
                .innings(rs.getInt("innings"))
                .gamePlayed(rs.getInt("games_played"))
                .losses(rs.getInt("losses"))
                .wins(rs.getInt("wins"))
                .build();
    }

    private RowMapper<Batter> batterRowMapper() {
        return (rs, rowNum) -> Batter.builder()
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .position(rs.getInt("position"))
                .primaryNum(rs.getInt("primary_num"))
                .clubName(rs.getString("name"))
                .homeruns(rs.getInt("homeruns"))
                .plates(rs.getInt("plates"))
                .gamePlayed(rs.getInt("games_played"))
                .build();
    }

    public List<Player> searchPlayer(SearchCond cond) {

        String playerName = cond.playerName();
        String clubName = cond.clubName();
        Integer position = cond.position();

        if (position == 1) {
            String sql = """
                SELECT p.first_name, p.last_name, p.player_photo, p.contact, p.power, p.discipline, p.control, p.stuff, p.primary_num
                FROM player p
                JOIN club c ON p.club_id = c.club_id
                JOIN player_position pp ON p.id = pp.player_id
                WHERE pp.position IN (0, 1)
                AND c.name = ?
                """;

            if (StringUtils.hasText(playerName)) {
                sql += " AND (p.first_name LIKE CONCAT('%', ?, '%') OR p.last_name LIKE CONCAT('%', ?, '%'))";
                log.info("sql - {}", sql);
                return template.query(sql, playerRowMapper(), clubName, playerName, playerName);
            }

            log.info("sql - {}", sql);
            return template.query(sql, playerRowMapper(), clubName);
        }

        String sql = """
                SELECT p.first_name, p.last_name, p.player_photo, p.contact, p.power, p.discipline, p.control, p.stuff, p.primary_num
                FROM player p
                JOIN club c ON p.club_id = c.club_id
                JOIN player_position pp ON p.id = pp.player_id
                WHERE pp.position = ?
                AND c.name = ?
                """;

        if (StringUtils.hasText(playerName)) {
            sql += " AND (p.first_name LIKE CONCAT('%', ?, '%') OR p.last_name LIKE CONCAT('%', ?, '%'))";
            log.info("sql - {}", sql);
            return template.query(sql, playerRowMapper(), position, clubName, playerName, playerName);
        }
        log.info("sql - {}", sql);
        return template.query(sql, playerRowMapper(), position, clubName);
    }


    private RowMapper<Player> playerRowMapper() {
        return BeanPropertyRowMapper.newInstance(Player.class);
    }

}
