package by.htp.jd2.maksimkosmachev.testapp.dao;

import by.htp.jd2.maksimkosmachev.testapp.dao.exception.ConnectionPoolException;
import by.htp.jd2.maksimkosmachev.testapp.dao.exception.SuchUserExistException;
import by.htp.jd2.maksimkosmachev.testapp.dao.exception.SuchUserNotExistException;
import by.htp.jd2.maksimkosmachev.testapp.entity.User;

import java.sql.SQLException;

public interface UserDAO {
    User signIn(String login, String password) throws ConnectionPoolException, SQLException, SuchUserNotExistException;
    boolean registration(User user) throws ConnectionPoolException, SQLException, SuchUserExistException;

}
