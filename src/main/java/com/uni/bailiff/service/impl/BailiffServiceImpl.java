package com.uni.bailiff.service.impl;

import com.uni.bailiff.dao.BailiffDAO;
import com.uni.bailiff.dao.DAOException;
import com.uni.bailiff.dao.DAOProvider;
import com.uni.bailiff.entity.BailiffEntity;
import com.uni.bailiff.service.BailiffService;
import com.uni.bailiff.service.ServiceException;

import java.util.List;

public class BailiffServiceImpl implements BailiffService {

    @Override
    public void save(BailiffEntity bailiff) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        BailiffDAO bailiffDAO = provider.getBailiffDAO();

        try{
            bailiffDAO.save(bailiff);

        } catch (DAOException e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public List<BailiffEntity> getAllBailiffs() throws ServiceException {
        List<BailiffEntity> bailiffs;

        DAOProvider provider = DAOProvider.getInstance();
        BailiffDAO bailiffDAO = provider.getBailiffDAO();

        try{
            bailiffs = bailiffDAO.getAllBailiffs();

        } catch (DAOException e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }

        return bailiffs;
    }

    @Override
    public BailiffEntity getBailiffById(long id) throws ServiceException {
        BailiffEntity bailiff;

        DAOProvider provider = DAOProvider.getInstance();
        BailiffDAO bailiffDAO = provider.getBailiffDAO();

        try{
            bailiff = bailiffDAO.getBailiffById(id);

        } catch (DAOException e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }

        return bailiff;
    }

    @Override
    public void updateBailiff(BailiffEntity bailiff) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        BailiffDAO bailiffDAO = provider.getBailiffDAO();

        try{
            bailiffDAO.updateBailiff(bailiff);

        } catch (DAOException e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        BailiffDAO bailiffDAO = provider.getBailiffDAO();

        try{
            bailiffDAO.delete(id);

        } catch (DAOException e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }
}
