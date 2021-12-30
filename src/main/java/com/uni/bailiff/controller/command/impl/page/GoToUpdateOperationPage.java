package com.uni.bailiff.controller.command.impl.page;

import com.uni.bailiff.controller.command.Command;
import com.uni.bailiff.entity.OperationEntity;
import com.uni.bailiff.service.OperationService;
import com.uni.bailiff.service.ServiceException;
import com.uni.bailiff.service.ServiceProvider;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToUpdateOperationPage implements Command {
    private final Logger LOGGER = Logger.getLogger(GoToUpdateOperationPage.class);

    private final String PATH_TO_UPDATE_OPERATION_PAGE = "WEB-INF/jsp/operation/updatePage.jsp";
    private final String OPERATION = "operation";
    private final String ID = "id";
    private final String ERROR_ATTRIBUTE = "errorMsg";
    private final String SERVER_ERROR= "Sorry, server error.";
    private final String PATH_TO_ERROR_PAGE = "WEB-INF/jsp/error/errorPage.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServiceProvider provider = ServiceProvider.getInstance();
        OperationService operationService = provider.getOperationService();

        long id = Long.parseLong(request.getParameter(ID));

        try{
            OperationEntity operation = operationService.getOperationById(id);

            session.setAttribute(OPERATION, operation);
            session.setAttribute(ID, id);

            RequestDispatcher dispatcher = request.getRequestDispatcher(PATH_TO_UPDATE_OPERATION_PAGE);
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            LOGGER.error(SERVER_ERROR, e);

            request.setAttribute(ERROR_ATTRIBUTE, SERVER_ERROR);
            RequestDispatcher dispatcher = request.getRequestDispatcher(PATH_TO_ERROR_PAGE);
            dispatcher.forward(request, response);
        }
    }
}
