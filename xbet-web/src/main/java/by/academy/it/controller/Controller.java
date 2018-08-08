package by.academy.it.controller;

import by.academy.it.command.Command;
import by.academy.it.command.CommandFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main controller for all pages
 */
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);
    private static final CommandFactory FACTORY = CommandFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException if an I/O error occurs
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Command command = FACTORY.getCommand(request);
        try {
            logger.info("command for this request is " + command.getClass().getSimpleName());
            command.execute(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Command execution is failed", e);
            response.sendRedirect(request.getContextPath() + "/main/error");
        }
    }

}
