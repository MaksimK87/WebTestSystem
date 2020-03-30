package by.htp.jd2.maksimkosmachev.testapp.command.impl;

import by.htp.jd2.maksimkosmachev.testapp.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToAddTestAnswerPage implements Command {

    private static final Logger logger = Logger.getLogger(GoToAddTestAnswerPage.class);

    private static int answerCounter;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        logger.debug("Answer counter before increment : "+answerCounter);

        if (session == null) {
            resp.sendRedirect("Controller?command=go_to_sign_in_page&errorMessage=session invalidate!");
            return;
        }

        answerCounter++;
        session.setAttribute("answerCounter", answerCounter);

        if ((int) session.getAttribute("answerCounter") > (int) session.getAttribute("answerQuantity")) {
            logger.debug("answer counter bigger than answer quantity: " +
                    session.getAttribute("answerCounter") + " > " + " " + session.getAttribute("answerQuantity"));

            answerCounter = 0;

            int questionCounter = (int) session.getAttribute("questionCounter");

            questionCounter++;

            logger.debug("questionCounter after increment: " + questionCounter);
            session.setAttribute("questionCounter", questionCounter);
            session.setAttribute("answerCounter",answerCounter);

            resp.sendRedirect("Controller?command=go_to_add_test_question_page");

            return;
        }

        logger.debug("Answer counter after increment : "+answerCounter);
        session.setAttribute("answerCounter",answerCounter);

        req.getRequestDispatcher("/WEB-INF/jsp/addAnswer.jsp").forward(req, resp);
    }
}

