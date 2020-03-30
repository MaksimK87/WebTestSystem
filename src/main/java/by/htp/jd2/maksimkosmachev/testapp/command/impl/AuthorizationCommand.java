package by.htp.jd2.maksimkosmachev.testapp.command.impl;

import by.htp.jd2.maksimkosmachev.testapp.command.Command;
import by.htp.jd2.maksimkosmachev.testapp.entity.Test;
import by.htp.jd2.maksimkosmachev.testapp.entity.User;
import by.htp.jd2.maksimkosmachev.testapp.service.ClientService;
import by.htp.jd2.maksimkosmachev.testapp.service.ServiceFactory;
import by.htp.jd2.maksimkosmachev.testapp.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import static by.htp.jd2.maksimkosmachev.testapp.command.RequestParameter.*;

public class AuthorizationCommand implements Command {

    private static final Logger logger = Logger.getLogger(AuthorizationCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceFactory serviceFactory = null;
        ClientService clientService = null;
        User user = null;
        HttpSession session;
        List<Test> tests = null;


        String login = req.getParameter(LOGIN_PARAM_FROM_LOGIN_PAGE);
        String password = req.getParameter(PASSWORD_PARAM_FROM_LOGIN_PAGE);
        serviceFactory = ServiceFactory.getInstance();
        clientService = serviceFactory.getClientService();
        try {
            user = clientService.signIn(login, password);

            if (user == null) {
                resp.sendRedirect("Controller?command=go_to_sign_in_page&errorMessage=Incorrect login or password! ");
                return;
            }
            session = req.getSession(true);

            session.setAttribute("user", user);

            //session.setAttribute("goto_req","Controller?command=go_to_main_page");

            logger.info("get user from DB: " + user);

            session.setAttribute("goto_req","/WEB-INF/jsp/main.jsp");

            tests = serviceFactory.getTestService().getAllTest();

            session.setAttribute("tests", tests);

        } catch (ServiceException e) {

            logger.error("Exception during signing in " + e);

            resp.sendRedirect("Controller?command=go_to_main_page&errorMessage= There no tests in database! ");
            return;
        }

        System.out.println("Выполняю авторизацию");

        resp.sendRedirect("Controller?command=go_to_main_page");

    }
}
