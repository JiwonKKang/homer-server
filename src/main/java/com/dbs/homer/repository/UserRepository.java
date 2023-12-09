package com.dbs.homer.repository;

import com.dbs.homer.repository.domain.User;
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

    public List<User> findRivalUsers(Integer userId) {

        String sql = "SELECT u.user_id, u.owner_name, u.email, u.password\n" +
                "FROM squad s\n" +
                "JOIN user u ON s.user_id = u.user_id\n" +
                "WHERE s.user_id <> ?;";

        return template.query(sql, userRowMapper(), userId);
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
