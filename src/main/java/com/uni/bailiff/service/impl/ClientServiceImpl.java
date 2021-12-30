package com.uni.bailiff.service.impl;

import com.uni.bailiff.dao.ClientDAO;
import com.uni.bailiff.dao.DAOException;
import com.uni.bailiff.dao.DAOProvider;
import com.uni.bailiff.entity.ClientEntity;
import com.uni.bailiff.service.ClientService;
import com.uni.bailiff.service.ServiceException;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    @Override
    public void save(ClientEntity client) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        ClientDAO clientDAO = provider.getClientDAO();

        try{
            clientDAO.save(client);

        } catch (DAOException e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public List<ClientEntity> getAllClients() throws ServiceException {
        List<ClientEntity> clients;

        DAOProvider provider = DAOProvider.getInstance();
        ClientDAO clientDAO = provider.getClientDAO();

        try{
            clients = clientDAO.getAllClients();

        } catch (DAOException e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }

        return clients;
    }

    @Override
    public ClientEntity getClientById(long id) throws ServiceException {
        ClientEntity client;

        DAOProvider provider = DAOProvider.getInstance();
        ClientDAO clientDAO = provider.getClientDAO();

        try{
            client = clientDAO.getClientById(id);

        } catch (DAOException e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }

        return client;
    }

    @Override
    public void updateClient(ClientEntity client) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        ClientDAO clientDAO = provider.getClientDAO();

        try{
            clientDAO.updateClient(client);

        } catch (DAOException e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        ClientDAO clientDAO = provider.getClientDAO();

        try{
            clientDAO.delete(id);

        } catch (DAOException e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }
}
