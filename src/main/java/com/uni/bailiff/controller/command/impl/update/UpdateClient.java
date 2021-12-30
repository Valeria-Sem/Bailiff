package com.uni.bailiff.controller.command.impl.update;

import com.uni.bailiff.controller.command.Command;
import com.uni.bailiff.entity.ClientEntity;
import com.uni.bailiff.service.ClientService;
import com.uni.bailiff.service.ServiceException;
import com.uni.bailiff.service.ServiceProvider;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateClient implements Command {
    private final Logger LOGGER = Logger.getLogger(UpdateClient.class);

    private final String GO_TO_CLIENT_PAGE_COMMAND = "controller?command=gotoclientpage";
    private final String CLIENTS = "clients";
    private final String ORDERS = "orders";
    private final String ID = "id";
    private final String FIO = "fio";
    private final String ERROR_ATTRIBUTE = "errorMsg";
    private final String SERVER_ERROR= "Sorry, server error.";
    private final String PATH_TO_ERROR_PAGE = "WEB-INF/jsp/error/errorPage.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        long id = Long.parseLong(request.getParameter(ID));
        String fio = request.getParameter(FIO);
        ClientEntity client;

        try{
            ServiceProvider provider = ServiceProvider.getInstance();
            ClientService clientService = provider.getClientService();

            client = new ClientEntity(id, fio);
            clientService.updateClient(client);

            session.removeAttribute(CLIENTS);
            session.removeAttribute(ORDERS);

            response.sendRedirect(GO_TO_CLIENT_PAGE_COMMAND);

        } catch (ServiceException e) {
            LOGGER.error(SERVER_ERROR, e);

            request.setAttribute(ERROR_ATTRIBUTE, SERVER_ERROR);
            RequestDispatcher dispatcher = request.getRequestDispatcher(PATH_TO_ERROR_PAGE);
            dispatcher.forward(request, response);
        }
    }
}
