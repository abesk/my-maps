package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.dao.MapPermissionDao;
import cz.muni.fi.pv243.mymaps.entities.MapPermissionEntity;
import cz.muni.fi.pv243.mymaps.entities.MyMapEntity;
import cz.muni.fi.pv243.mymaps.entities.UserEntity;
import java.util.List;

/**
 * 
 * @author Jiri Holusa
 */
public class MapPermissionDaoImpl extends GenericDaoImpl<MapPermissionEntity> implements MapPermissionDao {

    private static final String CACHE_NAME = "MapPermission";

    @Override
    protected String cacheName() {
        return CACHE_NAME;
    }

    @Override
    public List<MapPermissionEntity> getUsersPermissions(UserEntity user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MapPermissionEntity> getMapsPermissions(MyMapEntity map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
