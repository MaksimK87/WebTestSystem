package by.htp.jd2.maksimkosmachev.testapp.command.impl;

import by.htp.jd2.maksimkosmachev.testapp.command.Command;
import by.htp.jd2.maksimkosmachev.testapp.entity.Test;
import by.htp.jd2.maksimkosmachev.testapp.service.ServiceFactory;
import by.htp.jd2.maksimkosmachev.testapp.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToCompleteAddTestPage implements Command {

    private static final Logger logger = Logger.getLogger(GoToCompleteAddTestPage.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session=req.getSession();

        Test test = null;

        if (session == null) {
            resp.sendRedirect("Controller?command=go_to_sign_in_page&errorMessage=session invalidate!");
            return;
        }

        try {
            ServiceFactory serviceFactory=ServiceFactory.getInstance();
            test=serviceFactory.getTestService().getTestById((int)session.getAttribute("testId"));

        } catch (ServiceException e) {

            resp.sendRedirect("error.jsp");

            logger.error("Exception during getting testapp by id from DB");
    }

        session.setAttribute("test",test);
        session.setAttribute("goto_req", "Controller?command=go_to_complete_add_test_page");
        req.getRequestDispatcher("/WEB-INF/jsp/completeAddTest.jsp").forward(req,resp);

    }
}
