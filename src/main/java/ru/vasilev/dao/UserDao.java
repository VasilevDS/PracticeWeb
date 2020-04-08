package ru.vasilev.dao;

import ru.vasilev.model.User;

import java.sql.SQLException;

public interface UserDao {
    User findByLogin(String login) throws SQLException;
    Boolean save(User user) throws SQLException;
}
