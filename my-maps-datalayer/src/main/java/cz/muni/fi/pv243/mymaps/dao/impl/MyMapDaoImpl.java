package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.dao.MyMapDao;
import cz.muni.fi.pv243.mymaps.entities.MyMapEntity;
import cz.muni.fi.pv243.mymaps.entities.UserEntity;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jiri Holusa
 */
public class MyMapDaoImpl extends GenericDaoImpl<MyMapEntity> implements MyMapDao {

    private static final String CACHE_NAME = "MyMap";

    @Override
    protected String cacheName() {
        return CACHE_NAME;
    }

    @Override
    public List<MyMapEntity> findMapsByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MyMapEntity> findMapsByCreationDate(Date exactDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MyMapEntity> findMapsByCreationDate(Date from, Date to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MyMapEntity> findMapsByCreator(UserEntity creator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
