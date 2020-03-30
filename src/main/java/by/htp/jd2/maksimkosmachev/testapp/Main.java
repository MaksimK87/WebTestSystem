package by.htp.jd2.maksimkosmachev.testapp;

import by.htp.jd2.maksimkosmachev.testapp.dao.DAOFactory;
import by.htp.jd2.maksimkosmachev.testapp.dao.SQLTestDAO;
import by.htp.jd2.maksimkosmachev.testapp.dao.exception.ConnectionPoolException;
import by.htp.jd2.maksimkosmachev.testapp.entity.Test;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.*;

public class Main extends Thread{
    private static final Logger logger = Logger.getLogger(Main.class);


    public static void main(String[] args) {

     /*   logger.debug("mesahe ");
        ResourceBundle resourceBundle=ResourceBundle.getBundle("db");
        System.out.println(resourceBundle.getString("db.driver"));*/
        /*DAOFactory daoFactory = DAOFactory.getInstance();
        SQLTestDAO sqlTestDAO = (SQLTestDAO) daoFactory.getTestDAO();
        try {
           Test test= sqlTestDAO.getTestById(74);
            for(Map.Entry entry:test.getAllTest().entrySet()){
                System.out.println(entry);
                for(Map.Entry entry1:((HashMap<String, Boolean>) entry.getValue()).entrySet()){

                    System.out.println(entry1);
                }


            }
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        Test test = new Test();
        test.setTestName("Threads");
        test.setTestDuration(30);
        HashMap<String, Boolean> answers = new HashMap<>();
        HashMap<String, Boolean> answers1 = new HashMap<>();
        answers.put("Answer 11",true);
        answers.put("Answer 21",false);
        answers.put("Answer 31",false);
        answers1.put("Answer 41",true);
        answers1.put("Answer 51",false);
        answers1.put("Answer 61",false);

       // System.out.println(answers.get("Answer 1"));

        test.getAllTest().put("Question 11-31",answers );
        test.getAllTest().put("Question 41-61",answers1);

        System.out.println("size: "+ test.getAllTest().size());
        System.out.println("entrySet" + test.getAllTest().entrySet());
        System.out.println("keySet"+test.getAllTest().keySet());
        System.out.println("values"+test.getAllTest().values());
        Set<String> set=test.getAllTest().keySet();


        Iterator<String> questionIterator=set.iterator();
        while (questionIterator.hasNext()){
            System.out.println("------------> "+questionIterator.next());
        }
        System.out.println("set "+set);

        String[] setStr=new String[set.size()];
        set.toArray(setStr);
        System.out.println("setStr "+setStr[0]+" "+setStr[1] + "size: "+setStr.length);
        set.remove("Question 41-61");
        System.out.println("After removing set: " + set);

        System.out.println("----->setStr "+setStr[0]+" "+setStr[1] + "size: "+setStr.length);
        System.out.println("access by key"+test.getAllTest().get("Question 11-31").keySet());
        for (String s: test.getAllTest().get("Question 11-31").keySet()){
            System.out.println("---> "+ s );

        }

/*

        try {
            sqlTestDAO.addTest(test);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/

//        ResourceBundle resourceBundle=ResourceBundle.getBundle("resources.local",new Locale("by","by"));
//        System.out.println(resourceBundle.getString("local.registration"));

       /* User user;
        String login="Ivan";
        String password="123456";
        ResultSet resultSet;
        int id=1;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jd2_test_system?verifyServerCertificate=false &useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC",
                    "root", "123456");
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setUserDetailsId(resultSet.getInt(5));
                logger.info("getting information from user table "+user.toString());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/


      /*  try {
            SQLTestDAO sqlTestDAO=new SQLTestDAO();
            Test test=new Test();
            test.setTestName("Exceptions");
            test.setTestDuration(30);
            test.setQuestionText("Which class on the top of hierarchy of exceptions?");
            test.setAnswer("Throwable");
            test.setRightAnswer(true);
            sqlTestDAO.addTest(test);*/

           /* SQLUserDAO sqlUserDAO = new SQLUserDAO();
           // User user = sqlUserDAO.signIn("Ivan", "123456");
            User user=new User();
            user.setLogin("Vlad");
            user.setPassword("77777");
            user.setEmail("reaart@gmail.com");
            user.setName("Vlad");
            user.setSurname("Petrov");
            user.setRole(Role.ADMINISTRATOR);
            boolean status=sqlUserDAO.registration(user);
            System.out.println(user);
            System.out.println(status);
        } catch (ConnectionPoolException e) {
            logger.info("Error in Connection pool");
        } catch (SQLException e) {
            logger.info("Error in SQL" +e);
        } catch (SuchUserExistException e) {
            e.printStackTrace();*/



      /*  } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
     /*   DAOFactory daoFactory = DAOFactory.getInstance();
        TestDAO testDAO = daoFactory.getTestDAO();
        List<Test> tests;
        try {
            tests = testDAO.getTest();
            System.out.println(tests.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
*/
    }

}
