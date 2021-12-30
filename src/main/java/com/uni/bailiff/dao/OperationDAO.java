package com.uni.bailiff.dao;

import com.uni.bailiff.entity.OperationEntity;

import java.util.List;

public interface OperationDAO {
    void save(OperationEntity operation) throws DAOException;

    List<OperationEntity> getAllOperations() throws DAOException;

    OperationEntity getOperationById(long id) throws DAOException;

    void updateOperation(OperationEntity operation) throws DAOException;

    void delete(long id) throws DAOException;
}
