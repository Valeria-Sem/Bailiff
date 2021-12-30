package com.uni.bailiff.controller.command.impl.utils;

import com.uni.bailiff.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Clean implements Command {
    private final Logger LOGGER = Logger.getLogger(Clean.class);

    private final String ORDERS = "orders";
    private final String SERVER_ERROR_MSG = "Server error in getting orders ";
    private final String ERROR_MSG = "errorMsg";
    private final String GO_TO_HOME_PAGE_COMMAND = "controller?command=gotohomepage";
    private final String PATH_TO_ERROR_PAGE = "WEB-INF/jsp/error/errorPage.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        try {
            session.removeAttribute(ORDERS);

            response.sendRedirect(GO_TO_HOME_PAGE_COMMAND);

        } catch (Exception e) {
            LOGGER.error(SERVER_ERROR_MSG + e.getLocalizedMessage(), e);

            request.setAttribute(ERROR_MSG, SERVER_ERROR_MSG);
            request.getRequestDispatcher(PATH_TO_ERROR_PAGE).forward(request, response);
        }

    }
}
