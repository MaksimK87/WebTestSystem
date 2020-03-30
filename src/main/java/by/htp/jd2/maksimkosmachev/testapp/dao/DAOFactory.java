package by.htp.jd2.maksimkosmachev.testapp.dao;

public final class DAOFactory {

    private final static DAOFactory instance = new DAOFactory();
    private final UserDAO sqlUserImpl = new SQLUserDAO();
    private final TestDAO sqlTestDAO = new SQLTestDAO();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {

        return instance;
    }

    public UserDAO getUserDAO() {
        return sqlUserImpl;
    }

    public TestDAO getTestDAO() {
        return sqlTestDAO;
    }


}



