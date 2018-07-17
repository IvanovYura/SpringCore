package com.epam.spring.core.dao.jdbc;

import com.epam.spring.core.dao.UserDAO;
import com.epam.spring.core.domain.User;
import com.epam.spring.core.service.impl.BaseDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service("userDAO")
public class UserDAOImpl implements UserDAO {

    @Autowired
    @Qualifier("jdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public User getUserByEmail(String email) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        return namedParameterJdbcTemplate.queryForObject(BaseDbService.SELECT_USERS + " WHERE EMAIL = :email",
                params,
                rowMapper()
        );
    }

    @Override
    public User getUserById(Long id) {
        Map<String, Long> params = new HashMap<>();
        params.put("user_id", id);
        return namedParameterJdbcTemplate.queryForObject(BaseDbService.SELECT_USERS + " WHERE USER_ID = :user_id",
                params,
                rowMapper()
        );
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
