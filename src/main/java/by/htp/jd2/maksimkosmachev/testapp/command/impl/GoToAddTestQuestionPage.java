package by.htp.jd2.maksimkosmachev.testapp.command.impl;

import by.htp.jd2.maksimkosmachev.testapp.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class GoToAddTestQuestionPage implements Command {

    private static final Logger logger = Logger.getLogger(GoToAddTestQuestionPage.class);

    private static int questionCounter;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect("Controller?command=go_to_sign_in_page&errorMessage=session invalidate!");
            return;
        }

        if(session.getAttribute("questionCounter")==null||(int)session.getAttribute("questionCounter")==0){
            questionCounter=1;
            session.setAttribute("questionCounter",questionCounter);
        }

        questionCounter = (int) session.getAttribute("questionCounter");
        logger.debug("questionCounter: " + questionCounter);


        if ((int) session.getAttribute("questionCounter") > (int) session.getAttribute("questionQuantity")) {

            logger.debug("questionCounter bigger than questionQuantity: " +
                    session.getAttribute("questionCounter") + " > " + " " + session.getAttribute("questionQuantity"));

            questionCounter = 0;

            session.setAttribute("questionCounter", questionCounter);

            resp.sendRedirect("Controller?command=go_to_complete_add_test_page");

            return;

        }

       req.getRequestDispatcher("/WEB-INF/jsp/addQuestion.jsp").forward(req,resp);

    }
}
