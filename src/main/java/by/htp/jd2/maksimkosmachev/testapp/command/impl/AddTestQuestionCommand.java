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

public class AddTestQuestionCommand implements Command {

    private static final Logger logger = Logger.getLogger(AddTestQuestionCommand.class);


    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int answerQuantity;
        int testQuestionId = 0;
        String questionText;
        int testId;
        HttpSession session = req.getSession();


        logger.debug("question counter: " + session.getAttribute("questionCounter"));


        answerQuantity = Integer.parseInt(req.getParameter("answerQuantity"));
        questionText = req.getParameter("questionText");
        testId = (Integer) session.getAttribute("testId");

        logger.debug("questionText: " + questionText + " testId: " + testId);


        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        try {

            testQuestionId = serviceFactory.getTestService().addTestQuestion(questionText, testId);

        } catch (ServiceException e) {

            resp.sendRedirect("error.jsp");

            logger.error("Exception during writing testapp's question in DB");

        }

        session.setAttribute("answerQuantity", answerQuantity);
        session.setAttribute("testQuestionId", testQuestionId);

        session.setAttribute("questionText", questionText);


       resp.sendRedirect("Controller?command=go_to_add_test_answer_page");

    }
}
