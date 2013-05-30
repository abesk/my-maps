package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.dao.MapPermissionDao;
import cz.muni.fi.pv243.mymaps.entities.MapPermissionEntity;
import cz.muni.fi.pv243.mymaps.entities.MyMapEntity;
import cz.muni.fi.pv243.mymaps.entities.UserEntity;
import java.util.ArrayList;
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
        if (cache == null) {   
            String msg = "Internal error: cache is null.";
            log.error(msg);
            throw new IllegalStateException(msg);
        }
        
        if(user == null){
            String msg = "User cannot be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        
        if(user.getId() == null){
            String msg = "User's ID cannot be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        
        List<MapPermissionEntity> result = new ArrayList<>();
        for(MapPermissionEntity permission: cache.values()){
            if(permission.getUser().equals(user)){
                result.add(permission);
            }
        }
        
        if(result.isEmpty()){
            return null;
        }
        
        return result;
    }

    @Override
    public List<MapPermissionEntity> getMapsPermissions(MyMapEntity map) {
        if (cache == null) {   
            String msg = "Internal error: cache is null.";
            log.error(msg);
            throw new IllegalStateException(msg);
        }
        
        if(map == null){
            String msg = "Map cannot be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        
        if(map.getId() == null){
            String msg = "Map's ID cannot be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        
        List<MapPermissionEntity> result = new ArrayList<>();
        for(MapPermissionEntity permission: cache.values()){
            if(permission.getMap().equals(map)){
                result.add(permission);
            }
        }
        
        if(result.isEmpty()){
            return null;
        }
        
        return result;
    }
}
