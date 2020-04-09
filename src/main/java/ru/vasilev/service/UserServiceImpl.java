package ru.vasilev.service;

import org.springframework.util.DigestUtils;
import ru.vasilev.dao.UserDaoImpl;
import ru.vasilev.model.User;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public String login(User user) {
        try {
            User findUser = userDao.findByLogin(user.getLogin());
            if (findUser != null) {
                if(DigestUtils.md5DigestAsHex((user.getPassword()).getBytes()).equals(findUser.getPassword()))
                    return "Logged in as user: id"+ findUser.getId() + " " + findUser.getName() + " " + findUser.getSurname();
                else return "Incorrect password";
            }
            else return "invalid login";

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String registration(User user) {
        try {
            User findUser = userDao.findByLogin(user.getLogin());
            if(findUser==null) {
                userDao.save(user);
                return "User "+ user.getLogin() +" is registered";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "this login is not available";
    }
}
