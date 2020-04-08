package ru.vasilev.dao;

import org.springframework.util.DigestUtils;
import ru.vasilev.connection.ConnectionManager;
import ru.vasilev.model.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    ConnectionManager cm = new ConnectionManager();
    Connection connection;

    @Override
    public User findByLogin(String login) throws SQLException {
        connection = cm.getConnection();
        String query = "SELECT * FROM USER_WEB where LOGIN=" + login;
        User user = null;
        if(connection != null) {
            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if(resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("ID"));
                    user.setName(resultSet.getString("NAME"));
                    user.setSurname(resultSet.getString("SURNAME"));
                    user.setLogin(login);
                    user.setPassword(resultSet.getString("PASSWORD"));
                    return user;
                }
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                statement.close();
                connection.close();
                return null;
            }

        }
        return null;
    }

    @Override
    public Boolean save(User user) throws SQLException {
        connection = cm.getConnection();
        String queryINSERT = "insert into USER_WEB values (?, ?, ?, ?, ?)";
        if(connection != null) {
            PreparedStatement pr = connection.prepareStatement(queryINSERT);
            pr.setInt(1,user.getId());
            pr.setString(2,user.getName());
            pr.setString(3,user.getSurname());
            pr.setString(4,user.getLogin());
            pr.setString(5, DigestUtils.md5DigestAsHex((user.getPassword()).getBytes()));
            pr.executeUpdate();
            pr.close();
            connection.close();
        }
        return false;
    }
}
