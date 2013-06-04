package cz.muni.fi.pv243.mymaps.dao;

import cz.muni.fi.pv243.mymaps.entities.ViewEntity;
import java.util.List;

/**
 * DAO layer for View entity.
 * 
 * @author Jiri Holusa
 */
public interface ViewDao extends GenericDao<ViewEntity> {
    
    /**
     * Retrieves list of view with corresponding name.
     * 
     * @throws IllegalStateException when data layer is not available due to internal error
     * @throws IllegalArgumentException when name == null or name.length == 0
     * @param name name to be searched
     * @return list of views with name equal to the name suplied
     */
    List<ViewEntity> findViewsByName(String name);
}
