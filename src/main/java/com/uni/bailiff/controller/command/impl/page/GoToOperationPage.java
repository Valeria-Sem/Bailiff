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
import java.util.List;

public class GoToOperationPage implements Command {
    private final Logger LOGGER = Logger.getLogger(GoToOperationPage.class);

    private final String PATH_TO_OPERATION_PAGE = "WEB-INF/jsp/operation/operationPage.jsp";
    private final String OPERATIONS = "operations";
    private final String ERROR_ATTRIBUTE = "errorMsg";
    private final String SERVER_ERROR= "Sorry, server error.";
    private final String PATH_TO_ERROR_PAGE = "WEB-INF/jsp/error/errorPage.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<OperationEntity> operations = (List<OperationEntity>) session.getAttribute(OPERATIONS);

        if(operations == null){

            ServiceProvider provider = ServiceProvider.getInstance();
            OperationService operationService = provider.getOperationService();

            try{
                operations = operationService.getAllOperations();

                session.setAttribute(OPERATIONS, operations);

            } catch (ServiceException e) {
                LOGGER.error(SERVER_ERROR, e);

                request.setAttribute(ERROR_ATTRIBUTE, SERVER_ERROR);
                RequestDispatcher dispatcher = request.getRequestDispatcher(PATH_TO_ERROR_PAGE);
                dispatcher.forward(request, response);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(PATH_TO_OPERATION_PAGE);
        dispatcher.forward(request, response);
    }
}
