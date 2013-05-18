package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.dao.GenericDao;
import cz.muni.fi.pv243.mymaps.dao.ViewDao;
import cz.muni.fi.pv243.mymaps.entities.ViewEntity;


public class ViewDaoImpl extends GenericDao<ViewEntity> implements ViewDao {

    private static final String CACHE_NAME = "View";
    
    @Override
    protected String cacheName() {
        return CACHE_NAME;
    }
}
