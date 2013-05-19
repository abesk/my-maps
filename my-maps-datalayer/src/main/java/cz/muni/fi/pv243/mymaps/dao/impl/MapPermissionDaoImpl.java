package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.dao.MapPermissionDao;
import cz.muni.fi.pv243.mymaps.entities.MapPermissionEntity;

public class MapPermissionDaoImpl extends GenericDaoImpl<MapPermissionEntity> implements MapPermissionDao {

    private static final String CACHE_NAME = "MapPermission";

    @Override
    protected String cacheName() {
        return CACHE_NAME;
    }
}
