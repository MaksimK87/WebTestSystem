package by.htp.jd2.maksimkosmachev.testapp.command.impl;

import by.htp.jd2.maksimkosmachev.testapp.command.Command;
import by.htp.jd2.maksimkosmachev.testapp.entity.User;
import by.htp.jd2.maksimkosmachev.testapp.service.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToMainPage implements Command {

    private static final Logger logger = Logger.getLogger(GoToMainPage.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServiceFactory serviceFactory;

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect("Controller?command=go_to_sign_in_page&errorMessage=session invalidate!");
            return;
        }
        User user = (User) session.getAttribute("user");

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        System.out.println("Перенаправление на главную страницу");
        requestDispatcher.forward(req, resp);
    }
}
