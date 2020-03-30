package by.htp.jd2.maksimkosmachev.testapp.service;

import by.htp.jd2.maksimkosmachev.testapp.entity.Test;
import by.htp.jd2.maksimkosmachev.testapp.service.exception.ServiceException;

import java.util.List;

public interface TestService {
    void addTest(Test test) throws ServiceException;

    void editTest(Test test);

    Test getTest();

    List<Test> getAllTest() throws ServiceException;

    int addTest(String name, int duration) throws ServiceException;

    int addTestQuestion(String question, int testId) throws ServiceException;

    void addAnswer(String answer, boolean isRight, int questionId) throws ServiceException;

    Test getTestById(int idTest) throws ServiceException;


}
