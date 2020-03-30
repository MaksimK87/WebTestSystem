package by.htp.jd2.maksimkosmachev.testapp.command.impl;

import by.htp.jd2.maksimkosmachev.testapp.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

public class GoToAddTestPage implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession(false);
        if(session==null){
            resp.sendRedirect("Controller?command=go_to_sign_in_page&errorMessage=session invalidate!");
            return;
        }
        session.setAttribute("goto_req","/WEB-INF/jsp/addTest.jsp");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addTest.jsp");
        requestDispatcher.forward(req, resp);
    }
}
