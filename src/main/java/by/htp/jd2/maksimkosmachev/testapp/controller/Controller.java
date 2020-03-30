package by.htp.jd2.maksimkosmachev.testapp.controller;

import by.htp.jd2.maksimkosmachev.testapp.command.Command;
import by.htp.jd2.maksimkosmachev.testapp.command.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private final CommandProvider provider = new CommandProvider();
    private static final String COMMAND_NAME="command";

    public Controller() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command;

        String commandName = req.getParameter(COMMAND_NAME);

        command = provider.getCommand(commandName);
        command.execute(req, resp);

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
