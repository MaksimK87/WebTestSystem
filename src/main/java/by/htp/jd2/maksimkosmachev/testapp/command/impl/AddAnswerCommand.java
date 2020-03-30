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

public class AddAnswerCommand implements Command {

    private static final Logger logger = Logger.getLogger(AddAnswerCommand.class);

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int testQuestionId;
        String answerText;
        boolean rightAnswer = false;

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect("Controller?command=go_to_sign_in_page&errorMessage=session invalidate!");
            return;
        }

        answerText = req.getParameter("answerText");
        testQuestionId = (Integer) session.getAttribute("testQuestionId");

        logger.debug("answerText: " +answerText+ "questionId:" +testQuestionId);

        switch (req.getParameter("isCorrectAnswer")) {

            case "correct":
                rightAnswer = true;
                break;
            case "incorrect":
                rightAnswer = false;
                break;
        }


        try {

            ServiceFactory serviceFactory = ServiceFactory.getInstance();

            serviceFactory.getTestService().addAnswer(answerText, rightAnswer, testQuestionId);

        } catch (ServiceException e) {

            resp.sendRedirect("error.jsp");

            logger.error("Exception during writing testapp's question in DB");

        }

       resp.sendRedirect("Controller?command=go_to_add_test_answer_page");

    }
}
