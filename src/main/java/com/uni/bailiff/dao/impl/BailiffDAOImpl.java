package com.uni.bailiff.dao.impl;

import com.uni.bailiff.dao.BailiffDAO;
import com.uni.bailiff.dao.DAOException;
import com.uni.bailiff.dao.pool.ConnectionPool;
import com.uni.bailiff.dao.pool.ConnectionPoolException;
import com.uni.bailiff.entity.BailiffEntity;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BailiffDAOImpl implements BailiffDAO {
    private final Logger LOGGER = Logger.getLogger(BailiffDAOImpl.class);

    private final String GET_ALL_QUERY = "select * from bailiff";
    private final String GET_BY_ID_QUERY = "select * from bailiff where id = ";
    private final String INSERT_QUERY = "insert into bailiff (fio) values(?) ";
    private final String DELETE_QUERY = "delete from bailiff where id = ";
    private final String UPDATE_QUERY ="UPDATE bailiff SET fio = ? WHERE id = ?";

    private final String ID ="id";
    private final String FIO ="fio";

    @Override
    public void save(BailiffEntity bailiff) throws DAOException {
        Connection connection = null;
        ConnectionPool pool = null;
        PreparedStatement statement = null;

        try{
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();

            statement = connection.prepareStatement(INSERT_QUERY);

            statement.setString(1, bailiff.getFio());

            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e){
            LOGGER.error("Some problems with saving new bailiff: " + e.getLocalizedMessage());
            throw new DAOException(e.getLocalizedMessage(), e);

        } finally {
            if(connection != null){
                pool.closeConnection(connection, statement);
            }
        }
    }

    @Override
    public List<BailiffEntity> getAllBailiffs() throws DAOException {
        List<BailiffEntity> bailiffs = new ArrayList<>();

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
                BailiffEntity bailiff = new BailiffEntity();
                bailiff.setId(Long.parseLong(res.getString(ID)));
                bailiff.setFio(res.getString(FIO));

                bailiffs.add(bailiff);
            }
        } catch (ConnectionPoolException | SQLException e){
            LOGGER.error("Some problems with extracting bailiffs: " + e.getLocalizedMessage());
            throw new DAOException(e.getLocalizedMessage(), e);

        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps, res);
            }
        }

        return bailiffs;
    }

    @Override
    public BailiffEntity getBailiffById(long id) throws DAOException {
        BailiffEntity bailiff = null;
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
                long bailiffId = res.getLong(ID);
                String bailiffFio = res.getString(FIO);

                bailiff = new BailiffEntity(bailiffId, bailiffFio);
            }

        } catch (SQLException | ConnectionPoolException e){
            LOGGER.error("Some problems with extracting bailiff: " + e.getLocalizedMessage());
            throw new DAOException(e.getLocalizedMessage(), e);

        } finally {
            if(connection != null){
                pool.closeConnection(connection, statement, res);
            }
        }

        return bailiff;
    }

    @Override
    public void updateBailiff(BailiffEntity bailiff) throws DAOException {
        Connection connection = null;
        ConnectionPool pool = null;
        PreparedStatement statement = null;

        try{
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();

            statement = connection.prepareStatement(UPDATE_QUERY);

            statement.setString(1, bailiff.getFio());
            statement.setLong(2, bailiff.getId());

            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e){
            LOGGER.error("Some problems with updating bailiff: " + e.getLocalizedMessage());
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
            LOGGER.error("Some problems with deleting bailiff: " + e.getLocalizedMessage());
            throw new DAOException(e.getLocalizedMessage(), e);

        } finally {
            if(connection != null){
                pool.closeConnection(connection, statement);
            }
        }
    }
}
