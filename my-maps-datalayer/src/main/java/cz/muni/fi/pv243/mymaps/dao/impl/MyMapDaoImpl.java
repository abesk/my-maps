package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.dao.MyMapDao;
import cz.muni.fi.pv243.mymaps.entities.MyMapEntity;

/**
 *
 * @author Jan Bliznak
 */
public class MyMapDaoImpl extends GenericDaoImpl<MyMapEntity> implements MyMapDao {

    private static final String CACHE_NAME = "MyMap";

    @Override
    protected String cacheName() {
        return CACHE_NAME;
    }
}
