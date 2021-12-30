package com.uni.bailiff.dao.impl;

import com.uni.bailiff.dao.DAOException;
import com.uni.bailiff.dao.OrderDAO;
import com.uni.bailiff.dao.pool.ConnectionPool;
import com.uni.bailiff.dao.pool.ConnectionPoolException;
import com.uni.bailiff.entity.OrderEntity;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private final Logger LOGGER = Logger.getLogger(OrderDAOImpl.class);

    private final String GET_ALL_QUERY = "select * from `order`";
    private final String SORT_QUERY = "select * from `order` order by ";
    private final String GET_BY_ID_QUERY = "select * from `order` where id = ";
    private final String SEARCH_QUERY  = "select * from `order` where client like CONCAT( '%',?,'%') " +
            "or `order`.bailiff like CONCAT( '%',?,'%') or operation like CONCAT( '%',?,'%')";
    private final String INSERT_QUERY = "insert into `order` (client, operation, bailiff) values(?, ?, ?) ";
    private final String DELETE_QUERY = "delete from `order` where id = ";
    private final String UPDATE_QUERY ="UPDATE `order` SET client = ?, operation = ?, bailiff = ? WHERE id = ?";

    private final String ID ="id";
    private final String CLIENT ="client";
    private final String OPERATION ="operation";
    private final String BAILIFF ="bailiff";

    @Override
    public void save(OrderEntity order) throws DAOException {
        Connection connection = null;
        ConnectionPool pool = null;
        PreparedStatement statement = null;

        try{
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();

            statement = connection.prepareStatement(INSERT_QUERY);

            statement.setString(1, order.getClient());
            statement.setString(2, order.getOperation());
            statement.setString(3, order.getBailiff());

            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e){
            LOGGER.error("Some problems with saving new order: " + e.getLocalizedMessage());
            throw new DAOException(e.getLocalizedMessage(), e);

        } finally {
            if(connection != null){
                pool.closeConnection(connection, statement);
            }
        }
    }

    @Override
    public List<OrderEntity> getAllOrders() throws DAOException {
        List<OrderEntity> orders = new ArrayList<>();

        Connection connection = null;
        ConnectionPool pool = null;
        PreparedStatement ps = null;
        ResultSet res = null;

        try{
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(GET_ALL_QUERY);
            res = ps.executeQuery();

            while (res.next()){
                OrderEntity order = new OrderEntity();
                order.setId(Long.parseLong(res.getString(ID)));
                order.setClient(res.getString(CLIENT));
                order.setOperation(res.getString(OPERATION));
                order.setBailiff(res.getString(BAILIFF));

                orders.add(order);
            }
        } catch (ConnectionPoolException | SQLException e){
            LOGGER.error("Some problems with extracting orders: " + e.getLocalizedMessage());
            throw new DAOException(e.getLocalizedMessage(), e);

        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps, res);
            }
        }

        return orders;
    }

    @Override
    public OrderEntity getOrderById(long id) throws DAOException {
        OrderEntity order = null;
        Connection connection = null;
        ConnectionPool pool = null;
        PreparedStatement statement = null;
        ResultSet res = null;

        try{
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();

            statement = connection.prepareStatement(GET_BY_ID_QUERY + id);
            res = statement.executeQuery();

            if (res.next()){
                long orderId = res.getLong(ID);
                String client = res.getString(CLIENT);
                String operation = res.getString(OPERATION);
                String bailiff = res.getString(BAILIFF);

                order = new OrderEntity(orderId, client, operation, bailiff);
            }

        } catch (SQLException | ConnectionPoolException e){
            LOGGER.error("Some problems with extracting order: " + e.getLocalizedMessage());
            throw new DAOException(e.getLocalizedMessage(), e);

        } finally {
            if(connection != null){
                pool.closeConnection(connection, statement, res);
            }
        }

        return order;
    }

    @Override
    public void updateOrder(OrderEntity order) throws DAOException {
        Connection connection = null;
        ConnectionPool pool = null;
        PreparedStatement statement = null;

        try{
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();

            statement = connection.prepareStatement(UPDATE_QUERY);

            statement.setString(1, order.getClient());
            statement.setString(2, order.getOperation());
            statement.setString(3, order.getBailiff());
            statement.setLong(4, order.getId());

            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e){
            LOGGER.error("Some problems with updating order: " + e.getLocalizedMessage());
            throw new DAOException(e.getLocalizedMessage(), e);

        } finally {
            if(connection != null){
                pool.closeConnection(connection, statement);
            }
        }
    }

    @Override
    public void delete(long id) throws DAOException {
        Connection connection = null;
        ConnectionPool pool = null;
        PreparedStatement statement = null;

        try{
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            statement = connection.prepareStatement(DELETE_QUERY + id);
            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e){
            LOGGER.error("Some problems with deleting order: " + e.getLocalizedMessage());
            throw new DAOException(e.getLocalizedMessage(), e);

        } finally {
            if(connection != null){
                pool.closeConnection(connection, statement);
            }
        }
    }

    @Override
    public List<OrderEntity> search(String part) throws DAOException {
        List<OrderEntity> orders = new ArrayList<>();

        Connection connection = null;
        ConnectionPool pool = null;
        PreparedStatement ps = null;
        ResultSet res = null;

        try{
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SEARCH_QUERY);

            ps.setString(1, part);
            ps.setString(2, part);
            ps.setString(3, part);

            res = ps.executeQuery();

            while (res.next()){
                OrderEntity order = new OrderEntity();
                order.setId(Long.parseLong(res.getString(ID)));
                order.setClient(res.getString(CLIENT));
                order.setOperation(res.getString(OPERATION));
                order.setBailiff(res.getString(BAILIFF));

                orders.add(order);
            }
        } catch (ConnectionPoolException | SQLException e){
            LOGGER.error("Some problems with extracting orders: " + e.getLocalizedMessage());
            throw new DAOException(e.getLocalizedMessage(), e);

        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps, res);
            }
        }

        return orders;
    }

    @Override
    public List<OrderEntity> sort(String sort) throws DAOException {
        List<OrderEntity> orders = new ArrayList<>();

        Connection connection = null;
        ConnectionPool pool = null;
        PreparedStatement ps = null;
        ResultSet res = null;

        try{
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SORT_QUERY + sort);
            res = ps.executeQuery();

            while (res.next()){
                OrderEntity order = new OrderEntity();
                order.setId(Long.parseLong(res.getString(ID)));
                order.setClient(res.getString(CLIENT));
                order.setOperation(res.getString(OPERATION));
                order.setBailiff(res.getString(BAILIFF));

                orders.add(order);
            }
        } catch (ConnectionPoolException | SQLException e){
            LOGGER.error("Some problems with extracting orders: " + e.getLocalizedMessage());
            throw new DAOException(e.getLocalizedMessage(), e);

        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps, res);
            }
        }

        return orders;
    }
}
