package by.htp.jd2.maksimkosmachev.testapp.command.impl;

import by.htp.jd2.maksimkosmachev.testapp.command.Command;
import by.htp.jd2.maksimkosmachev.testapp.entity.Test;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;


public class ShowTestQuestionCommand implements Command {

    private static final Logger logger = Logger.getLogger(ShowTestQuestionCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int currentQuestionIndex;
        Set<String> answers;
        String[] questions;

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect("Controller?command=go_to_sign_in_page&errorMessage=session invalidate!");
            return;
        }

        currentQuestionIndex = (int) session.getAttribute("questionQuantity")-1;  // start from last index in massive

        logger.debug("Current question index " + currentQuestionIndex);
        logger.debug("Test: "+session.getAttribute("userTest"));

        if (currentQuestionIndex <0) {
            resp.sendRedirect("Controller?command=go_to_test_results_page");
            logger.debug("Redirecting to test result page");
            return;
        }
        Test test = (Test) session.getAttribute("userTest");
        questions= (String[]) session.getAttribute("arrTestQuestion");
        answers=test.getAllTest().get(questions[currentQuestionIndex]).keySet();
        session.setAttribute("answers",answers);
        session.setAttribute("currentQuestionIndex",currentQuestionIndex);
        session.setAttribute("goto_req", "/WEB-INF/jsp/showTestQuestion.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/showTestQuestion.jsp").forward(req,resp);
        //resp.sendRedirect("/WEB-INF/jsp/showTestQuestion.jsp");    userTest.allTest.arrTestQuestion[currentQuestionIndex].keySet

    }
}
