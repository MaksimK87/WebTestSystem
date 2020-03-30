package by.htp.jd2.maksimkosmachev.testapp.service;

import by.htp.jd2.maksimkosmachev.testapp.dao.DAOFactory;
import by.htp.jd2.maksimkosmachev.testapp.dao.UserDAO;
import by.htp.jd2.maksimkosmachev.testapp.dao.exception.ConnectionPoolException;
import by.htp.jd2.maksimkosmachev.testapp.dao.exception.SuchUserExistException;
import by.htp.jd2.maksimkosmachev.testapp.dao.exception.SuchUserNotExistException;
import by.htp.jd2.maksimkosmachev.testapp.entity.User;
import by.htp.jd2.maksimkosmachev.testapp.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class ClientServiceImpl implements ClientService {

    private static final Logger logger = Logger.getLogger(ClientServiceImpl.class);

    @Override
    public User signIn(String login, String password) throws ServiceException {
        User user=null;
        if (login == null || login.isEmpty()) {
            logger.error("Incorrect or empty login");
            throw new ServiceException("Incorrect or empty login");
        }
        if (password == null || password.isEmpty()) {
            logger.error("password wasn't entered");
            throw new ServiceException("password wasn't entered");
        }
        try {
            DAOFactory daoObjFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoObjFactory.getUserDAO();
            user=userDAO.signIn(login, password);
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPoolException" +e);
        } catch (SQLException e) {
            logger.error("SQLException" +e);
        } catch (SuchUserNotExistException e) {
            logger.error("SuchUserNotExistException "+e);
        }

        return user;
    }

    @Override
    public boolean registration(User user) throws ServiceException {
        boolean isRegist=false;
        if(user==null){
            logger.error("User doesn't exist! Fill data for user!");
            throw new ServiceException("User doesn't exist! Fill data for user!");
        }
        DAOFactory daoFactory=DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();


        try {
            isRegist= userDAO.registration(user);
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPoolException "+e);
        } catch (SQLException e) {
            logger.error("SQL exception "+e);
        } catch (SuchUserExistException e) {
            logger.error("SuchUserExistException "+ e);
        }
        return isRegist;
    }

    @Override
    public boolean signOut(String login) throws ServiceException {
        return false;
    }
}
