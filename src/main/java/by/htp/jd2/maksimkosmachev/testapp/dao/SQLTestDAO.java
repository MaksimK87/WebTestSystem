package by.htp.jd2.maksimkosmachev.testapp.dao;

import by.htp.jd2.maksimkosmachev.testapp.dao.connectionpool.ConnectionPool;
import by.htp.jd2.maksimkosmachev.testapp.dao.exception.ConnectionPoolException;
import by.htp.jd2.maksimkosmachev.testapp.entity.Test;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.htp.jd2.maksimkosmachev.testapp.dao.SQLrequest.*;

public class SQLTestDAO implements TestDAO {

    private static final Logger logger = Logger.getLogger(SQLTestDAO.class);

    @Override
    public void addTest(Test test) throws ConnectionPoolException, SQLException {

        logger.debug("In method addTest");

        PreparedStatement preparedStatement = null;

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.takeConnection();

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(ADD_NEW_TEST, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, test.getTestName());
            preparedStatement.setInt(2, test.getTestDuration());
            preparedStatement.executeUpdate();
            logger.debug("Execution ADD_NEW_TEST query was successful");
            ResultSet idTest = preparedStatement.getGeneratedKeys();
            if (idTest.next()) {
                test.setId(idTest.getInt(1));
                logger.debug("id was generated for testapp " + test.getId());
            }
            for (Map.Entry entry : test.getAllTest().entrySet()) {

                preparedStatement = connection.prepareStatement(ADD_TEST_QUESTION, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, (String) entry.getKey());
                preparedStatement.setInt(2, test.getId());
                preparedStatement.executeUpdate();
                logger.debug("Inserting question text and testapp id in Test questions table completed! " + entry.getKey());
                ResultSet idTestQuestion = preparedStatement.getGeneratedKeys();
                if (idTestQuestion.next()) {
                    test.setIdTestQuestion(idTestQuestion.getInt(1));
                    logger.debug("id_test_question was generated for test_questions " + test.getIdTestQuestion());
                }


                for (Map.Entry subEntry : ((HashMap<String, Boolean>) entry.getValue()).entrySet()) {
                    preparedStatement = connection.prepareStatement(ADD_TEST_ANSWER, Statement.RETURN_GENERATED_KEYS);
                    logger.debug(" subEntry for DB " + subEntry);
                    preparedStatement.setString(1, (String) subEntry.getKey());
                    preparedStatement.setBoolean(2, (Boolean) subEntry.getValue());
                    preparedStatement.setInt(3, test.getIdTestQuestion());
                    preparedStatement.executeUpdate();
                    logger.debug("Inserting answer text, isRight and id_test_question in Test answers table completed!");
                    ResultSet idTestAnswers = preparedStatement.getGeneratedKeys();
                    if (idTestAnswers.next()) {
                        test.setIdTestAnswer(idTestAnswers.getInt(1));
                        logger.debug("id_test_answer was generated for test_answers " + test.getIdTestAnswer());
                    }
                }

            }
        } finally {
            connection.setAutoCommit(true);
            connectionPool.closeConnection(connection, preparedStatement);
            logger.debug("closing connection from SQLTestDAO (method - addTest)");
        }

    }

    @Override
    public void editTest(Test test) throws ConnectionPoolException {

        logger.debug("In method edit testapp");
        Connection connection;
        PreparedStatement preparedStatement;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();


    }

    @Override
    public void getTest(String name) {

    }

    public List<Test> getAllTest() throws SQLException, ConnectionPoolException {

        logger.debug("In get all testapp method");

        List<Test> tests = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.takeConnection();

        try {

            preparedStatement = connection.prepareStatement(SHOW_ALL_TESTS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                logger.debug("result set is processing");
                Test test = new Test();
                test.setTestName(resultSet.getString(2));
                test.setTestDuration(resultSet.getInt(3));
                test.setId(resultSet.getInt(1));
                logger.debug("Test from DB: " + test.getTestName() + " duration: " + test.getTestDuration());
                tests.add(test);

            }
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
            logger.debug("closing connection from SQLTestDAO (method - getTest)");
        }


        return tests;
    }


    public int addTest(String testName, int duration) throws ConnectionPoolException, SQLException {

        logger.debug("In method add testapp name and duration");

        int idTest = addTestDetail(testName, duration, ADD_TEST_NAME_AND_DURATION);

        return idTest;
    }

    @Override
    public int addTestQuestion(String question, int testId) throws ConnectionPoolException, SQLException {

        logger.debug("In method addTestQuestion");

        int idTestQuestion = addTestDetail(question, testId, ADD_TEST_QUESTION);

        return idTestQuestion;
    }

    @Override
    public void addAnswer(String answer, boolean isRight, int questionId) throws ConnectionPoolException, SQLException {

        logger.debug("In method addAnswer");

        PreparedStatement preparedStatement = null;

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.takeConnection();

        try {

            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(ADD_TEST_ANSWER);
            preparedStatement.setString(1, answer);
            preparedStatement.setBoolean(2, isRight);
            preparedStatement.setInt(3, questionId);
            preparedStatement.executeUpdate();

            logger.debug("Execution query was successful in method addAnswer");

        } finally {

            connection.setAutoCommit(true);
            connectionPool.closeConnection(connection, preparedStatement);

            logger.debug("closing connection from SQLTestDAO (method - addAnswer)");
        }

    }

    public Test getTestById(int idTest) throws ConnectionPoolException, SQLException {

        logger.debug("In getTest by Id method");

        Test test = new Test();
        Map<String,Boolean> answers=test.getAnswers();

        int currentQuestionId;

        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.takeConnection();
        preparedStatement = connection.prepareStatement(GET_TEST_BY_ID);

        try {

            preparedStatement.setInt(1, idTest);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                logger.debug("result set is processing");

                test.setIdTestQuestion(resultSet.getInt("id_test_questions"));

                currentQuestionId = test.getIdTestQuestion();

                logger.debug("currentQuestionId: " + currentQuestionId);

                test.setQuestionText(resultSet.getString("question_text"));
                test.setAnswer(resultSet.getString("answers"));
                test.setRightAnswer(resultSet.getBoolean("isRight"));

                logger.debug("testapp question: " + test.getQuestionText() + ", answer: " + test.getAnswer() +
                        " right answer? - " + test.isRightAnswer());

                if (resultSet.previous()) {

                    logger.debug("In previous row");

                    if (resultSet.getInt("id_test_questions") == currentQuestionId) {

                        logger.debug("id previous question and current the same: " + currentQuestionId);

                        answers.put(test.getAnswer(), test.isRightAnswer());

                        logger.debug("collecting answers: " + test.getAnswer() + " isRight: " + test.isRightAnswer());

                    } else {

                        logger.debug("id previous question and current are different");

                        test.getAllTest().put(resultSet.getString("question_text"), answers);

                        logger.debug("Add to TEST question " + resultSet.getString("question_text") + " with answers: ");

                        for (Map.Entry entry : answers.entrySet()) {
                            logger.debug("Answers -->" + entry);
                        }


                        for (Map.Entry entry : test.getAllTest().entrySet()) {
                            logger.debug(" Test -->" + entry);
                        }

                        answers=new HashMap<>();

                        logger.debug("clear answers");

                        answers.put(test.getAnswer(), test.isRightAnswer());

                        logger.debug("Create new Answers Map for question: " + test.getQuestionText()+" , collecting answer: " +
                                test.getAnswer());

                    }

                    resultSet.next();

                    logger.debug("In current row");

                    if(resultSet.isLast()){

                        logger.debug("Add to TEST LAST question " + resultSet.getString("question_text") + "with answers: ");
                        test.getAllTest().put(resultSet.getString("question_text"), answers);
                        for (Map.Entry entry : answers.entrySet()) {
                            logger.debug("Answers -->" + entry);
                        }

                        for (Map.Entry entry : test.getAllTest().entrySet()) {
                            logger.debug("Test -->" + entry);
                        }
                    }


                    continue;
                }
                resultSet.next();
                test.setTestName(resultSet.getString("test_name"));
                test.setTestDuration(resultSet.getInt("duration_time"));
                logger.debug("testapp name : " + test.getTestName() + ", testapp duration: " + test.getTestDuration());
                answers.put(test.getAnswer(), test.isRightAnswer());
                logger.debug("collecting answers: " + test.getAnswer() + " isRight: " + test.isRightAnswer());

            }

        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
            logger.debug("closing connection from SQLTestDAO (method - getTest by id)");
        }
           logger.debug("TEST : " +test.getAllTest().entrySet().size());
        return test;
    }

    private int addTestDetail(String detail, int parameter, String query) throws SQLException, ConnectionPoolException {

        PreparedStatement preparedStatement = null;
        ResultSet id;
        int idTest = 0;

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.takeConnection();

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, detail);
            preparedStatement.setInt(2, parameter);
            preparedStatement.executeUpdate();
            logger.debug("Execution query was successful");
            id = preparedStatement.getGeneratedKeys();
            if (id.next()) {
                idTest = id.getInt(1);

            }

        } finally {
            connection.setAutoCommit(true);
            connectionPool.closeConnection(connection, preparedStatement);
            logger.debug("closing connection from SQLTestDAO (method - addTestDetail)");
        }

        return idTest;
    }
}



