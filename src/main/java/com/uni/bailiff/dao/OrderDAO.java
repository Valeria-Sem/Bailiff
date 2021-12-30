package com.uni.bailiff.dao;

import com.uni.bailiff.entity.OrderEntity;

import java.util.List;

public interface OrderDAO {
    void save(OrderEntity order) throws DAOException;

    List<OrderEntity> getAllOrders() throws DAOException;

    OrderEntity getOrderById(long id) throws DAOException;

    void updateOrder(OrderEntity order) throws DAOException;

    void delete(long id) throws DAOException;

    List<OrderEntity> search(String part) throws DAOException;

    List<OrderEntity> sort(String sort) throws DAOException;
}
