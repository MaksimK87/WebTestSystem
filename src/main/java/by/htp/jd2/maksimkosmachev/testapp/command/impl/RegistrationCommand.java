package by.htp.jd2.maksimkosmachev.testapp.command.impl;

import by.htp.jd2.maksimkosmachev.testapp.command.Command;
import by.htp.jd2.maksimkosmachev.testapp.entity.User;
import by.htp.jd2.maksimkosmachev.testapp.entity.enumpackage.Role;
import by.htp.jd2.maksimkosmachev.testapp.service.ServiceFactory;
import by.htp.jd2.maksimkosmachev.testapp.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationCommand implements Command {

    private static final Logger logger = Logger.getLogger(RegistrationCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServiceFactory serviceFactory = null;
        boolean isRegistration = false;
        User user = new User();

        user.setName(req.getParameter("name"));
        user.setSurname(req.getParameter("surname"));
        user.setEmail(req.getParameter("email"));
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setRole(Role.valueOf(req.getParameter("role")));
        serviceFactory = ServiceFactory.getInstance();
        try {
            isRegistration = serviceFactory.getClientService().registration(user);
            logger.info("User's registration is: " + isRegistration);
        } catch (ServiceException e) {
            logger.error("exception during registration " + e);
        }
        HttpSession session = req.getSession(true);
        if (isRegistration) {
            session.setAttribute("user", user);
            resp.sendRedirect("Controller?command=go_to_main_page");
        }
       /* req.setAttribute("isRegistration", isRegistration);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/successReg.jsp");
        requestDispatcher.forward(req, resp);*/
    }
}
