package by.htp.jd2.maksimkosmachev.testapp.command.impl;

import by.htp.jd2.maksimkosmachev.testapp.command.Command;
import by.htp.jd2.maksimkosmachev.testapp.service.ServiceFactory;
import by.htp.jd2.maksimkosmachev.testapp.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddTest implements Command {

    private static final Logger logger = Logger.getLogger(AddTest.class);


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int questionQuantity = 0;
        int testId=0;
        String testName;
        int testDuration;

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect("Controller?command=go_to_sign_in_page&errorMessage=session invalidate!");
            return;
        }
        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        testName = req.getParameter("testName");
        testDuration = Integer.parseInt(req.getParameter("duration"));

        logger.debug("test's name is: " + testName + ", duration: " + testDuration + ", questions quantity: " +
                req.getParameter("questionQuantity"));

        try {

            testId = serviceFactory.getTestService().addTest(testName, testDuration);
            questionQuantity = Integer.parseInt(req.getParameter("questionQuantity"));
            logger.debug("Write in DB parameters about testapp: " + testName + ", duration: " + testDuration +
                    ", testID: " + testId);

        } catch (ServiceException e) {

            resp.sendRedirect("error.jsp");

            logger.error("Exception during writing testapp's name and duration in DB");
        }


        session.setAttribute("goto_req", "Controller?command=go_to_add_test");
        session.setAttribute("testId", testId);
        session.setAttribute("questionQuantity", questionQuantity);
        session.setAttribute("testName", testName);
        session.setAttribute("duration", testDuration);



        resp.sendRedirect("Controller?command=go_to_add_test_question_page");

    }
}
