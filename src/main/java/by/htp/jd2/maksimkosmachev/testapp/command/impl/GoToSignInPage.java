package by.htp.jd2.maksimkosmachev.testapp.command.impl;

import by.htp.jd2.maksimkosmachev.testapp.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToSignInPage implements Command {

    private static final Logger logger = Logger.getLogger(GoToSignInPage.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session=req.getSession(false);
        session.setAttribute("goto_req","/WEB-INF/jsp/signIn.jsp");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/signIn.jsp");
        requestDispatcher.forward(req, resp);
    }
}
