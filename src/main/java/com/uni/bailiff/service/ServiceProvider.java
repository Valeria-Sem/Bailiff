package com.uni.bailiff.service;

import com.uni.bailiff.service.impl.BailiffServiceImpl;
import com.uni.bailiff.service.impl.ClientServiceImpl;
import com.uni.bailiff.service.impl.OperationServiceImpl;
import com.uni.bailiff.service.impl.OrderServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    private final BailiffService bailiffService = new BailiffServiceImpl();
    private final ClientService clientService = new ClientServiceImpl();
    private final OperationService operationService = new OperationServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    private ServiceProvider() {}

    public static ServiceProvider getInstance() {
        return instance;
    }

    public BailiffService getBailiffService() {
        return bailiffService;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public OperationService getOperationService() {
        return operationService;
    }

    public OrderService getOrderService() {
        return orderService;
    }
}
