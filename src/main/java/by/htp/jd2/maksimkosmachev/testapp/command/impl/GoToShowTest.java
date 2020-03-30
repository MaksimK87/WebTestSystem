package by.htp.jd2.maksimkosmachev.testapp.command.impl;

import by.htp.jd2.maksimkosmachev.testapp.command.Command;
import by.htp.jd2.maksimkosmachev.testapp.entity.Test;
import by.htp.jd2.maksimkosmachev.testapp.service.ServiceFactory;
import by.htp.jd2.maksimkosmachev.testapp.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class GoToShowTest implements Command {

    private static final Logger logger = Logger.getLogger(GoToShowTest.class);
    private static int arrSize;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int testId;
        String testName;
        int questionQuantity;
        Set<String> testQuestion;
        String arrTestQuestion[];

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect("Controller?command=go_to_sign_in_page&errorMessage=session invalidate!");
            return;
        }

        testName = req.getParameter("testName");
        testId = Integer.parseInt((req.getParameter("testId")));

        try {
            Test test = ServiceFactory.getInstance().getTestService().getTestById(testId);
            session.setAttribute("userTest", test);
            questionQuantity = test.getAllTest().size();
            arrTestQuestion = new String[questionQuantity];
            testQuestion = test.getAllTest().keySet();

            if (testQuestion.isEmpty()) {
                resp.sendRedirect("Controller?command=go_to_main_page&errorMessage=This test is empty, try to pass it later! ");

                logger.error("Test " + testName + " is empty!");
                return;
            }
            testQuestion.toArray(arrTestQuestion);
            session.setAttribute("questionQuantity", questionQuantity);
            session.setAttribute("arrTestQuestion", arrTestQuestion);
            session.setAttribute("goto_req", "/WEB-INF/jsp/showTestInfo.jsp");

        } catch (ServiceException e) {

            resp.sendRedirect("error.jsp");

            logger.error("Exception during getting test from DB");
        }

        logger.debug("test id :" + testId + " name " + testName);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/showTestInfo.jsp");
        requestDispatcher.forward(req, resp);
    }
}
