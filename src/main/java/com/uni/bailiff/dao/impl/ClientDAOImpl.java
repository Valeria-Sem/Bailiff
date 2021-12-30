package com.uni.bailiff.dao.impl;

import com.uni.bailiff.dao.ClientDAO;
import com.uni.bailiff.dao.DAOException;
import com.uni.bailiff.dao.pool.ConnectionPool;
import com.uni.bailiff.dao.pool.ConnectionPoolException;
import com.uni.bailiff.entity.ClientEntity;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    private final Logger LOGGER = Logger.getLogger(ClientDAOImpl.class);

    private final String GET_ALL_QUERY = "select * from client";
    private final String GET_BY_ID_QUERY = "select * from client where id = ";
    private final String INSERT_QUERY = "insert into client (fio) values(?) ";
    private final String DELETE_QUERY = "delete from client where id = ";
    private final String UPDATE_QUERY ="update client SET fio = ? where id = ?";

    private final String ID ="id";
    private final String FIO ="fio";

    @Override
    public void save(ClientEntity client) throws DAOException {
        Connection connection = null;
        ConnectionPool pool = null;
        PreparedStatement statement = null;

        try{
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();

            statement = connection.prepareStatement(INSERT_QUERY);

            statement.setString(1, client.getFio());

            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e){
            LOGGER.error("Some problems with saving new client: " + e.getLocalizedMessage());
            throw new DAOException(e.getLocalizedMessage(), e);

        } finally {
            if(connection != null){
                pool.closeConnection(connection, statement);
            }
        }
    }

    @Override
    public List<ClientEntity> getAllClients() throws DAOException {
        List<ClientEntity> clients = new ArrayList<>();

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
                ClientEntity client = new ClientEntity();
                client.setId(Long.parseLong(res.getString(ID)));
                client.setFio(res.getString(FIO));

                clients.add(client);
            }
        } catch (ConnectionPoolException | SQLException e){
            LOGGER.error("Some problems with extracting clients: " + e.getLocalizedMessage());
            throw new DAOException(e.getLocalizedMessage(), e);

        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps, res);
            }
        }

        return clients;
    }

    @Override
    public ClientEntity getClientById(long id) throws DAOException {
        ClientEntity client = null;
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
                long clientId = res.getLong(ID);
                String clientFio = res.getString(FIO);

                client = new ClientEntity(clientId, clientFio);
            }

        } catch (SQLException | ConnectionPoolException e){
            LOGGER.error("Some problems with extracting client: " + e.getLocalizedMessage());
            throw new DAOException(e.getLocalizedMessage(), e);

        } finally {
            if(connection != null){
                pool.closeConnection(connection, statement, res);
            }
        }

        return client;
    }

    @Override
    public void updateClient(ClientEntity client) throws DAOException {
        Connection connection = null;
        ConnectionPool pool = null;
        PreparedStatement statement = null;

        try{
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();

            statement = connection.prepareStatement(UPDATE_QUERY);

            statement.setString(1, client.getFio());
            statement.setLong(2, client.getId());

            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e){
            LOGGER.error("Some problems with updating client: " + e.getLocalizedMessage());
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
            LOGGER.error("Some problems with deleting client: " + e.getLocalizedMessage());
            throw new DAOException(e.getLocalizedMessage(), e);

        } finally {
            if(connection != null){
                pool.closeConnection(connection, statement);
            }
        }
    }
}
