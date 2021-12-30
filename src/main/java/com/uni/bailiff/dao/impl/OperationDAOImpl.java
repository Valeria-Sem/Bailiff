package com.uni.bailiff.dao.impl;

import com.uni.bailiff.dao.DAOException;
import com.uni.bailiff.dao.OperationDAO;
import com.uni.bailiff.dao.pool.ConnectionPool;
import com.uni.bailiff.dao.pool.ConnectionPoolException;
import com.uni.bailiff.entity.OperationEntity;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationDAOImpl implements OperationDAO {
    private final Logger LOGGER = Logger.getLogger(BailiffDAOImpl.class);

    private final String GET_ALL_QUERY = "select * from operation";
    private final String GET_BY_ID_QUERY = "select * from operation where id = ";
    private final String INSERT_QUERY = "insert into operation (name) values(?) ";
    private final String DELETE_QUERY = "delete from operation where id = ";
    private final String UPDATE_QUERY ="UPDATE operation SET name = ? WHERE id = ?";

    private final String ID ="id";
    private final String NAME ="name";

    @Override
    public void save(OperationEntity operation) throws DAOException {
        Connection connection = null;
        ConnectionPool pool = null;
        PreparedStatement statement = null;

        try{
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();

            statement = connection.prepareStatement(INSERT_QUERY);

            statement.setString(1, operation.getName());

            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e){
            LOGGER.error("Some problems with saving new operation: " + e.getLocalizedMessage());
            throw new DAOException(e.getLocalizedMessage(), e);

        } finally {
            if(connection != null){
                pool.closeConnection(connection, statement);
            }
        }
    }

    @Override
    public List<OperationEntity> getAllOperations() throws DAOException {
        List<OperationEntity> operations = new ArrayList<>();

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
                OperationEntity operation = new OperationEntity();
                operation.setId(Long.parseLong(res.getString(ID)));
                operation.setName(res.getString(NAME));

                operations.add(operation);
            }
        } catch (ConnectionPoolException | SQLException e){
            LOGGER.error("Some problems with extracting operations: " + e.getLocalizedMessage());
            throw new DAOException(e.getLocalizedMessage(), e);

        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps, res);
            }
        }

        return operations;
    }

    @Override
    public OperationEntity getOperationById(long id) throws DAOException {
        OperationEntity operation = null;
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
                long operationId = res.getLong(ID);
                String operationName = res.getString(NAME);

                operation = new OperationEntity(operationId, operationName);
            }

        } catch (SQLException | ConnectionPoolException e){
            LOGGER.error("Some problems with extracting operation: " + e.getLocalizedMessage());
            throw new DAOException(e.getLocalizedMessage(), e);

        } finally {
            if(connection != null){
                pool.closeConnection(connection, statement, res);
            }
        }

        return operation;
    }

    @Override
    public void updateOperation(OperationEntity operation) throws DAOException {
        Connection connection = null;
        ConnectionPool pool = null;
        PreparedStatement statement = null;

        try{
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();

            statement = connection.prepareStatement(UPDATE_QUERY);

            statement.setString(1, operation.getName());
            statement.setLong(2, operation.getId());

            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e){
            LOGGER.error("Some problems with updating operation: " + e.getLocalizedMessage());
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
            LOGGER.error("Some problems with deleting operation: " + e.getLocalizedMessage());
            throw new DAOException(e.getLocalizedMessage(), e);

        } finally {
            if(connection != null){
                pool.closeConnection(connection, statement);
            }
        }
    }
}
