package by.htp.jd2.maksimkosmachev.testapp.service;

import by.htp.jd2.maksimkosmachev.testapp.dao.DAOFactory;
import by.htp.jd2.maksimkosmachev.testapp.dao.exception.ConnectionPoolException;
import by.htp.jd2.maksimkosmachev.testapp.entity.Test;
import by.htp.jd2.maksimkosmachev.testapp.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class TestServiceImpl implements TestService {

    private static final Logger logger = Logger.getLogger(TestServiceImpl.class);

    @Override
    public void addTest(Test test) throws ServiceException {

        if (test != null) {

            DAOFactory daoFactory = DAOFactory.getInstance();

            try {

                daoFactory.getTestDAO().addTest(test);

            } catch (ConnectionPoolException e) {

                logger.error("Connection pool exception " + e);

                throw new ServiceException("Exception in connection pool " + e);

            } catch (SQLException e) {

                logger.error("SQLException" + e);

                throw new ServiceException("SQL exception " + e);
            }
        }
    }

    @Override
    public void editTest(Test test) {

    }

    @Override
    public Test getTest() {
        return null;
    }

    @Override
    public List<Test> getAllTest() throws ServiceException {

        List<Test> tests;

        DAOFactory daoFactory = DAOFactory.getInstance();

        try {

            tests = daoFactory.getTestDAO().getAllTest();

        } catch (SQLException e) {

            logger.error("SQLException" + e);

            throw new ServiceException("SQL exception " + e);

        } catch (ConnectionPoolException e) {

            logger.error("Connection pool exception " + e);

            throw new ServiceException("Exception in connection pool " + e);
        }

        if (tests == null || tests.isEmpty()) {

            logger.error("Tests doesn't exist");

            throw new ServiceException("Tests doesn't exist!");

        }
        return tests;
    }

    @Override
    public int addTest(String name, int duration) throws ServiceException {

        int testId=0;

        if (name != null && !name.isEmpty() && duration > 0) {

            DAOFactory daoFactory=DAOFactory.getInstance();

            try {

                testId=daoFactory.getTestDAO().addTest(name,duration);

            } catch (ConnectionPoolException e) {

                logger.error("Exception in method addTest by name and duration" +e);
                throw new ServiceException("Exception in method addTest by name and duration" +e);

            } catch (SQLException e) {

               logger.error("SQLException in method addTest by name and duration " + e);
               throw new ServiceException("SQL exception in method addTest by name and duration " + e);

            }

        }
        return testId;
    }

    @Override
    public int addTestQuestion(String question, int testId) throws ServiceException {
        
        int idTestQuestion = 0;

        if(question!=null&&!question.isEmpty() && testId>0){
            
            DAOFactory daoFactory=DAOFactory.getInstance();

            try {

                idTestQuestion=daoFactory.getTestDAO().addTestQuestion(question,testId);

            } catch (ConnectionPoolException e) {

                logger.error("Exception in method addTestQuestion " +e);

                throw new ServiceException("Exception in method addTestQuestion " +e);

            } catch (SQLException e) {

                logger.error("SQLException in method addTestQuestion " + e);

                throw new ServiceException("SQLException in method addTestQuestion " + e);
            }
        }
        
        return idTestQuestion;
    }

    @Override
    public void addAnswer(String answer, boolean isRight, int questionId) throws ServiceException {

        if(answer!=null&&!answer.isEmpty()&& questionId>0){

            DAOFactory daoFactory=DAOFactory.getInstance();

            try {

               daoFactory.getTestDAO().addAnswer(answer,isRight,questionId);

            } catch (ConnectionPoolException e) {

                logger.error("Exception in method addAnswer " +e);

                throw new ServiceException("Exception in method addAnswer " +e);

            } catch (SQLException e) {

                logger.error("SQLException in method addAnswer " + e);

                throw new ServiceException("SQLException in method addAnswer " + e);
            }
        }
    }

    @Override
    public Test getTestById(int idTest) throws ServiceException {

        Test test;

        if(idTest>0){

            DAOFactory daoFactory=DAOFactory.getInstance();

            try {

                test=daoFactory.getTestDAO().getTestById(idTest);

            } catch (ConnectionPoolException e) {

                logger.error("Exception in method getTestById ",e);

                throw new ServiceException("Exception in method getTestById ", e);

            } catch (SQLException e) {

                logger.error("Exception in method getTestById ",e);

                throw new ServiceException("Exception in method getTestById ", e);
            }
        }
        else throw new ServiceException("testId less than (or equals)  zero");

        return test;
    }


}
