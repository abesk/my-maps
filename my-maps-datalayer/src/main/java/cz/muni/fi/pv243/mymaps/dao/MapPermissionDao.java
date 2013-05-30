package cz.muni.fi.pv243.mymaps.dao;

import cz.muni.fi.pv243.mymaps.entities.MapPermissionEntity;
import cz.muni.fi.pv243.mymaps.entities.MyMapEntity;
import cz.muni.fi.pv243.mymaps.entities.UserEntity;
import java.util.List;

/**
 * DAO layer for MapPermission entity
 * 
 * @author Jiri Holusa
 */
public interface MapPermissionDao extends GenericDao<MapPermissionEntity> {
    
    /**
     * Retrieves list of permissions assigned to the corresponding user.
     * 
     * @throws IllegalStateException when data layer is not available due to internal error
     * @throws IllegalArgumentException when user == null or user.getId() == null
     * @param user user whose permissions to be retrieved
     * @return list of permissions or null when no permission is found
     */
    List<MapPermissionEntity> getUsersPermissions(UserEntity user);
    
    /**
     * Retrieves list of permissions assigned to the corresponding map.
     * 
     * @throws IllegalStateException when data layer is not available due to internal error
     * @throws IllegalArgumentException when map == null or map.getId() == null
     * @param map map whose permissions to be retrieved
     * @return list of permissions or null when no permission is found
     */
    List<MapPermissionEntity> getMapsPermissions(MyMapEntity map);
    
    
}
