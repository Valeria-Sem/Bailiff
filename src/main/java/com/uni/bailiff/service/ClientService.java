package com.uni.bailiff.service;

import com.uni.bailiff.entity.ClientEntity;

import java.util.List;

public interface ClientService {
    void save(ClientEntity client) throws ServiceException;

    List<ClientEntity> getAllClients() throws ServiceException;

    ClientEntity getClientById(long id) throws ServiceException;

    void updateClient(ClientEntity client) throws ServiceException;

    void delete(long id) throws ServiceException;
}
