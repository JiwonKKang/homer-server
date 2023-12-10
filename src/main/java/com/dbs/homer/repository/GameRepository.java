package com.dbs.homer.repository;

import com.dbs.homer.repository.domain.Game;
import com.dbs.homer.repository.domain.Manager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;

@Repository
public class GameRepository {
    private final JdbcTemplate template;

    public GameRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public void save(LocalDate startDate, Integer homeId, Integer awayId, String winTeam, int homeScore, int awayScore) {
        String sql = """
                    insert into game (start_date, home_id, away_id, win_team, home_score, away_score)
                    values (?, ?, ?, ?, ?, ?)""";
        template.update(sql, startDate, homeId, awayId, winTeam, homeScore, awayScore);
    }

}
