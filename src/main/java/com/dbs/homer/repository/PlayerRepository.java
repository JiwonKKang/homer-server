package com.dbs.homer.repository;

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


    public List<Player> findAll() {
        String sql = "select * from player";
        return template.query(sql, memberRowMapper());
    }

    private RowMapper<Player> memberRowMapper() {
        return (rs, rowNum) -> Player.builder()
                .id(rs.getInt("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .playerNum(rs.getInt("player_num"))
                .clubId(rs.getInt("club_id"))
                .playerPhoto(rs.getString("player_photo"))
                .power(rs.getInt("power"))
                .stuff(rs.getInt("stuff"))
                .control(rs.getInt("control"))
                .contract(rs.getInt("contract"))
                .discipline(rs.getInt("discipline"))
                .build();
    }

}
