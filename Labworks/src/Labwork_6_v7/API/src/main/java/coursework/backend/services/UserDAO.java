package coursework.backend.services;

import coursework.backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
public class UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public User getUserById(Integer id) {
        try {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public User createUser(User user) {
        String hashedPassword = org.apache.commons.codec.digest.DigestUtils.sha256Hex(user.getPassword());
        String sql = "INSERT INTO users (first_name, last_name, email, phone_number, password) VALUES (?, ?, ?, ?, ?) RETURNING user_id";
        Integer newId = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                hashedPassword
        );
        return getUserById(newId);
    }

    public User updateUser(Integer id, Map<String, Object> updates) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder sql = new StringBuilder("UPDATE users SET ");

        updates.forEach((key, value) -> {
            if (key.equals("password")) {
                value = org.apache.commons.codec.digest.DigestUtils.sha256Hex((String) value);
            }
            sql.append(key).append(" = :").append(key).append(", ");
            params.addValue(key, value);
        });

        sql.delete(sql.length() - 2, sql.length());
        sql.append(" WHERE user_id = :id");
        params.addValue("id", id);

        namedParameterJdbcTemplate.update(sql.toString(), params);
        return getUserById(id);
    }

    public void deleteUser(Integer id) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
