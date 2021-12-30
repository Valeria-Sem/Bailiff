package com.uni.bailiff.controller.command.impl.page;

import com.uni.bailiff.controller.command.Command;
import com.uni.bailiff.entity.BailiffEntity;
import com.uni.bailiff.service.BailiffService;
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

public class GoToBailiffPage implements Command {
    private final Logger LOGGER = Logger.getLogger(GoToBailiffPage.class);

    private final String PATH_TO_BAILIFF_PAGE = "WEB-INF/jsp/bailiff/bailiffPage.jsp";
    private final String BAILIFFS = "bailiffs";
    private final String ERROR_ATTRIBUTE = "errorMsg";
    private final String SERVER_ERROR= "Sorry, server error.";
    private final String PATH_TO_ERROR_PAGE = "WEB-INF/jsp/error/errorPage.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<BailiffEntity> bailiffs = (List<BailiffEntity>) session.getAttribute(BAILIFFS);

        if(bailiffs == null){

            ServiceProvider provider = ServiceProvider.getInstance();
            BailiffService bailiffService = provider.getBailiffService();

            try{
                bailiffs = bailiffService.getAllBailiffs();

                session.setAttribute(BAILIFFS, bailiffs);

            } catch (ServiceException e) {
                LOGGER.error(SERVER_ERROR, e);

                request.setAttribute(ERROR_ATTRIBUTE, SERVER_ERROR);
                RequestDispatcher dispatcher = request.getRequestDispatcher(PATH_TO_ERROR_PAGE);
                dispatcher.forward(request, response);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(PATH_TO_BAILIFF_PAGE);
        dispatcher.forward(request, response);
    }
}
