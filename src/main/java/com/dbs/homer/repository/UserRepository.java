package com.dbs.homer.repository;

import com.dbs.homer.repository.domain.Rival;
import com.dbs.homer.repository.domain.SquadStatistic;
import com.dbs.homer.repository.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate template;

    public UserRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public List<Rival> findRivalUsers(Integer userId) {

        String sql = """
                SELECT u.owner_name, u.email, s.squad_id, u.user_id
                FROM squad s
                JOIN user u ON s.user_id = u.user_id
                WHERE s.user_id <> ?;
                """;

        return template.query(sql, rivalRowMapper(), userId);
    }

    public SquadStatistic getStatisticBySquadId(Integer userId) {

        String sql = """
                SELECT user_id, home_win_rate, away_win_rate, total_games, total_wins, total_rate
                FROM squad_statistics ss
                JOIN squad s ON s.squad_id = ss.squad_id
                WHERE s.user_id = ?;
                """;

        return template.queryForObject(sql, squadStatisticRowMapper(), userId);
    }

    public User findByUserId(Integer userId) {

        String sql = """
                SELECT u.user_id, u.owner_name, u.email, u.password
                FROM user u
                WHERE u.user_id = ?
                """;

        return template.queryForObject(sql, userRowMapper(), userId);
    }


    private RowMapper<SquadStatistic> squadStatisticRowMapper() {
        return BeanPropertyRowMapper.newInstance(SquadStatistic.class);
    }

    private RowMapper<Rival> rivalRowMapper() {
        return BeanPropertyRowMapper.newInstance(Rival.class);
    }


    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> User.builder()
                .userId(rs.getInt("user_id"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .ownerName(rs.getString("owner_name"))
                .build();
    }

}
