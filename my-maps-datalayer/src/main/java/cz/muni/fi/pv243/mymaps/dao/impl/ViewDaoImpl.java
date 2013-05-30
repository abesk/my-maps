package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.dao.ViewDao;
import cz.muni.fi.pv243.mymaps.entities.ViewEntity;
import java.util.List;

/**
 * 
 * @author Jiri Holusa
 */
public class ViewDaoImpl extends GenericDaoImpl<ViewEntity> implements ViewDao {

    private static final String CACHE_NAME = "View";

    @Override
    protected String cacheName() {
        return CACHE_NAME;
    }

    @Override
    public List<ViewEntity> findViewsByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
    
}
