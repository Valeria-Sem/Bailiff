package com.uni.bailiff.controller.command.impl.sort;

import com.uni.bailiff.controller.command.Command;
import com.uni.bailiff.entity.OrderEntity;
import com.uni.bailiff.service.OrderService;
import com.uni.bailiff.service.ServiceException;
import com.uni.bailiff.service.ServiceProvider;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SortByBailiff implements Command {
    private final Logger LOGGER = Logger.getLogger(SortByBailiff.class);

    private final String ORDERS = "orders";
    private final String SORT = "sort";
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

        List<OrderEntity> orders;
        String sort;

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        OrderService orderService = serviceProvider.getOrderService();

        try {
            sort = String.valueOf(request.getParameter(SORT));

            orders = orderService.sort("bailiff " + sort);

            session.setAttribute(ORDERS, orders);

            response.sendRedirect(GO_TO_HOME_PAGE_COMMAND);

        } catch (ServiceException e) {
            LOGGER.error(SERVER_ERROR_MSG, e);

            request.setAttribute(ERROR_MSG, SERVER_ERROR_MSG);
            request.getRequestDispatcher(PATH_TO_ERROR_PAGE).forward(request, response);
        }

    }
}

