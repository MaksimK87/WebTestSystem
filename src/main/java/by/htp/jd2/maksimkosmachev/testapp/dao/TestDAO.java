package by.htp.jd2.maksimkosmachev.testapp.dao;

import by.htp.jd2.maksimkosmachev.testapp.dao.exception.ConnectionPoolException;
import by.htp.jd2.maksimkosmachev.testapp.entity.Test;

import java.sql.SQLException;
import java.util.List;

public interface TestDAO {

    void addTest(Test test) throws ConnectionPoolException, SQLException;

    int addTest(String name, int duration) throws ConnectionPoolException, SQLException;

    void editTest(Test test) throws ConnectionPoolException;

    void getTest(String name);

    List<Test> getAllTest() throws SQLException, ConnectionPoolException;

    int addTestQuestion(String question, int testId) throws ConnectionPoolException, SQLException;

    void addAnswer(String answer, boolean isRight, int questionId) throws ConnectionPoolException, SQLException;

    Test getTestById(int idTest) throws ConnectionPoolException, SQLException;

}

