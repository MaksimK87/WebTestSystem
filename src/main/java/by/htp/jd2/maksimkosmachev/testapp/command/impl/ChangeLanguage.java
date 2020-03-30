package by.htp.jd2.maksimkosmachev.testapp.command.impl;

import by.htp.jd2.maksimkosmachev.testapp.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLanguage implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String local;
        String goToReq;
        HttpSession session = req.getSession(true);

        local = req.getParameter("lang");

        session.setAttribute("local", local);
        goToReq = (String) session.getAttribute("goto_req");

        if (goToReq.endsWith(".jsp")) {
            req.getRequestDispatcher(goToReq).forward(req, resp);
        } else {
            resp.sendRedirect(goToReq);
        }


    }
}
