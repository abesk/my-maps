package cz.muni.fi.pv243.mymaps.dao;

import cz.muni.fi.pv243.mymaps.entities.MyMapEntity;
import cz.muni.fi.pv243.mymaps.entities.UserEntity;
import java.util.Date;
import java.util.List;

/**
 * DAO layer for MyMap entity.
 * 
 * @author Jiri Holusa
 */
public interface MyMapDao extends GenericDao<MyMapEntity> {
    
    /**
     * Retrieves list of maps whose names are equal to the name suplied.
     * 
     * @throws IllegalStateException when data layer is not available due to internal error
     * @throws IllegalArgumentException when name == null or name.length == 0
     * @param name name of the map to be searched
     * @return list of maps with name equal to the name suplied
     */
    List<MyMapEntity> findMapsByName(String name);
    
    /**
     * Retrieves list of maps whose creation date are exactly equal to the date suplied.
     * 
     * @throws IllegalStateException when data layer is not available due to internal error
     * @throws IllegalArgumentException when exactDate == null
     * @param exactDate date of the map to be searched
     * @return list of maps created exactly on given date
     */
    List<MyMapEntity> findMapsByCreationDate(Date exactDate);
    
    /**
     * Retrieves list of maps whose creation date belongs into the interval (from, to).
     * 
     * @throws IllegalStateException when data layer is not available due to internal error
     * @throws IllegalArgumentException when from == null or to == null or from > to
     * @param from from which date the map should be searched, must be less than or equal to date to
     * @param to to which date the map should be searched, must be greater than or equal to date from
     * @return list of maps created within given time range
     */
    List<MyMapEntity> findMapsByCreationDate(Date from, Date to);
    
    /**
     * Retrieves all maps whose creator is creator suplied.
     * 
     * @throws IllegalStateException when data layer is not available due to internal error
     * @throws IllegalArgumentException when creator == null 
     * @param creator creator of the maps to be searched
     * @return  list of maps created by given user
     */
    List<MyMapEntity> findMapsByCreator(UserEntity creator);
}
