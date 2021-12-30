package com.uni.bailiff.dao;

import com.uni.bailiff.dao.impl.BailiffDAOImpl;
import com.uni.bailiff.dao.impl.ClientDAOImpl;
import com.uni.bailiff.dao.impl.OperationDAOImpl;
import com.uni.bailiff.dao.impl.OrderDAOImpl;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();

    private final BailiffDAO bailiffDAO = new BailiffDAOImpl();
    private final ClientDAO clientDAO = new ClientDAOImpl();
    private final OperationDAO operationDAO = new OperationDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();


    private DAOProvider(){}

    public static DAOProvider getInstance(){
        return instance;
    }

    public BailiffDAO getBailiffDAO() {
        return bailiffDAO;
    }

    public ClientDAO getClientDAO() {
        return clientDAO;
    }

    public OperationDAO getOperationDAO() {
        return operationDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }
}
