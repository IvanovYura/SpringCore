package com.epam.spring.core.dao.jdbc;

import com.epam.spring.core.dao.UserDAO;
import com.epam.spring.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service("userDAO")
public class UserDAOImpl implements UserDAO {

    private static final String SELECT_USERS =
            "SELECT * FROM USERS";

    private static final String INSERT_USER =
            "INSERT INTO USERS (FIRST_NAME, LAST_NAME, EMAIL, BIRTHDAY) VALUES (:first_name, :last_name, :email, :birthday)";

    private static final String DELETE_USER =
            "DELETE FROM USERS WHERE USER_ID = :user_id";

    @Autowired
    @Qualifier("jdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public User getUserByEmail(String email) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        return namedParameterJdbcTemplate.queryForObject(SELECT_USERS + " WHERE EMAIL = :email",
                params,
                rowMapper()
        );
    }

    @Override
    public User getUserById(Long id) {
        Map<String, Long> params = new HashMap<>();
        params.put("user_id", id);
        return namedParameterJdbcTemplate.queryForObject(SELECT_USERS + " WHERE USER_ID = :user_id",
                params,
                rowMapper()
        );
    }

    @Override
    public User add(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("first_name", user.getFirstName());
        params.put("last_name", user.getLastName());
        params.put("email", user.getEmail());
        params.put("birthday", user.getBirthday().toString());
        namedParameterJdbcTemplate.update(INSERT_USER, params);
        return getUserByEmail(user.getEmail());
    }

    @Override
    public Collection<User> getAll() {
        return namedParameterJdbcTemplate.query(SELECT_USERS, new HashMap<>(), rowMapper());
    }

    @Override
    public void remove(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", id);
        namedParameterJdbcTemplate.update(DELETE_USER, params);
    }

    private RowMapper<User> rowMapper() {
        return new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getLong("USER_ID"));
                user.setFirstName(resultSet.getString("FIRST_NAME"));
                user.setLastName(resultSet.getString("LAST_NAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setBirthday(LocalDate.parse(resultSet.getString("BIRTHDAY")));
                return user;
            }
        };
    }
}
