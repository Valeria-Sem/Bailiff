package com.uni.bailiff.dao;

import com.uni.bailiff.entity.BailiffEntity;

import java.util.List;

public interface BailiffDAO {
    void save(BailiffEntity bailiff) throws DAOException;

    List<BailiffEntity> getAllBailiffs() throws DAOException;

    BailiffEntity getBailiffById(long id) throws DAOException;

    void updateBailiff(BailiffEntity bailiff) throws DAOException;

    void delete(long id) throws DAOException;
}
