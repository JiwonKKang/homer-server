package com.dbs.homer.repository;

import com.dbs.homer.repository.domain.Batter;
import com.dbs.homer.repository.domain.Pitcher;
import com.dbs.homer.repository.domain.Player;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PlayerRepository {

    private final JdbcTemplate template;

    public PlayerRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public List<Batter> findBatterBySquadId(Integer squadId) {
        String sql = "SELECT p.first_name, p.last_name, c.name, bs.position, bs.games_played, bs.homeruns, bs.plates,\n" +
                "CASE \n" +
                "WHEN bs.plates = 0 THEN 0\n" +
                "ELSE bs.hits / bs.plates END AS avg\n" +
                "FROM player p \n" +
                "JOIN batter bs ON p.id = bs.player_id\n" +
                "JOIN club c ON p.club_id = c.club_id\n" +
                "WHERE bs.squad_id = ?\n" +
                "AND bs.position NOT IN (0, 1);";
        return template.query(sql, batterRowMapper(), squadId);
    }

    public List<Pitcher> findPitcherBySquadId(Integer squadId) {
        String sql = "SELECT p.first_name, p.last_name, c.name, ps.position, ps.games_played, ps.innings, ps.wins, ps.losses, \n" +
                "CASE\n" +
                "WHEN ps.innings = 0 THEN 0\n" +
                "ELSE (ps.earned_runs / ps.innings * 9) END AS era\n" +
                "FROM player p \n" +
                "JOIN pitcher ps ON p.id = ps.player_id\n" +
                "JOIN club c ON p.club_id = c.club_id\n" +
                "WHERE ps.squad_id = ?\n" +
                "AND ps.position IN (0, 1);";

        return template.query(sql, pitcherRowMapper(), squadId);
    }

    private RowMapper<Pitcher> pitcherRowMapper() {
        return (rs, rowNum) -> Pitcher.builder()
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .position(rs.getInt("position"))
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
                .clubName(rs.getString("name"))
                .homeruns(rs.getInt("homeruns"))
                .plates(rs.getInt("plates"))
                .gamePlayed(rs.getInt("games_played"))
                .build();
    }

    private RowMapper<Player> playerRowMapper() {
        return (rs, rowNum) -> Player.builder()
                .id(rs.getInt("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .primaryNum(rs.getInt("primary_num"))
                .clubId(rs.getInt("club_id"))
                .playerPhoto(rs.getString("player_photo"))
                .power(rs.getInt("power"))
                .stuff(rs.getInt("stuff"))
                .control(rs.getInt("control"))
                .contact(rs.getInt("contact"))
                .discipline(rs.getInt("discipline"))
                .build();
    }

}
