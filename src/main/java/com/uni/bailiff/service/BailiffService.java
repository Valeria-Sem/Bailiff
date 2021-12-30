package com.uni.bailiff.service;

import com.uni.bailiff.entity.BailiffEntity;

import java.util.List;

public interface BailiffService {
    void save(BailiffEntity bailiff) throws ServiceException;

    List<BailiffEntity> getAllBailiffs() throws ServiceException;

    BailiffEntity getBailiffById(long id) throws ServiceException;

    void updateBailiff(BailiffEntity bailiff) throws ServiceException;

    void delete(long id) throws ServiceException;
}
