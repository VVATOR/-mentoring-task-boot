package com.epam.mentoring.springboot.services.impl;

import com.epam.mentoring.springboot.beans.FriendShips;
import com.epam.mentoring.springboot.beans.User;
import com.epam.mentoring.springboot.services.SocialNetworkService;
import java.sql.Types;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class DefaultSocialNetworkServiceImpl implements SocialNetworkService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private SimpleJdbcInsert simpleJdbcInsert;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final RowMapper usersRowMapper = (rs, rowNumber) -> new User(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("surname"),
            rs.getDate("birthdate")
    );

    private static final RowMapper friendshipsRowMapper = (rs, rowNumber) -> new FriendShips(
            rs.getInt("userid1"),
            rs.getInt("userid2"),
            rs.getTimestamp("timestamp")
    );

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query("Select * FROM users", usersRowMapper);
    }

    @Override
    public User getUser(final int id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        List<User> users =
                namedParameterJdbcTemplate.query("Select * FROM users WHERE id=:id", namedParameters, usersRowMapper);
        System.out.println(users.get(0));
        return users.isEmpty() ? new User() : users.get(0);
    }

    @Override
    public void editUser(final User user) {
        Object[] params =
                {user.getName(), user.getSurname(), new java.sql.Date(user.getBirth().getTime()), user.getId()};
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.BIGINT};
        int rows = jdbcTemplate
                .update("UPDATE users SET name = ?, surname = ?, birthDate = ? WHERE id = ?", params, types);
        System.out.println(rows + " row(s) updated.");
    }

    @Override
    public List<FriendShips> getUserFriendShips(int userId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", userId);
        return namedParameterJdbcTemplate
                .query("Select * FROM friendships WHERE userid1=:id", namedParameters, friendshipsRowMapper);
    }

    @Override
    public List<FriendShips> getAllFriendShips() {
        return jdbcTemplate.query("Select * FROM friendships", friendshipsRowMapper);
    }

    @Override
    public void removeUser(int userId) {
        Object[] params = {userId};
        int[] types = {Types.BIGINT};
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=0;");
        jdbcTemplate.update("Delete From friendships Where userId1=?", params, types);
        jdbcTemplate.update("Delete From users Where id=?", params, types);
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=1;");
    }

    @Override
    public void addUser(User user) {
        Object[] params = {user.getName(), user.getSurname(), new java.sql.Date(user.getBirth().getTime())};
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.DATE};
        jdbcTemplate.update("Insert Into users (name, surname, birthdate) Values (?,?,?)", params, types);
    }

    @Override
    public void generateUsers(int count) {
        Random r = new Random();
        int Low = 1;
        int High = 10;
        DataFactory dataFactory = new DataFactory();
        for (int i = 0; i < count; i++) {
          Object[] paramsUsers = {dataFactory.getFirstName(), dataFactory.getLastName(), new java.sql.Date(dataFactory.getBirthDate().getTime())};
          int[] typesUsers = {Types.VARCHAR, Types.VARCHAR, Types.DATE};
          jdbcTemplate.update("Insert Into users (name, surname, birthdate) Values (?,?,?)", paramsUsers, typesUsers);

          int maxCountFriendRandom = r.nextInt(High-Low) + Low;
          for (int j = 0; j < maxCountFriendRandom ; j++) {
            Object[] params = {i, dataFactory.getNumberBetween(1,count), new java.sql.Date(dataFactory.getBirthDate().getTime())};
            int[] types = {Types.INTEGER, Types.INTEGER, Types.TIMESTAMP};
            jdbcTemplate.update("Insert Into friendships (userid1, userid2, timestamp) Values (?,?,?)", params, types);
          }
        }

    }
}
