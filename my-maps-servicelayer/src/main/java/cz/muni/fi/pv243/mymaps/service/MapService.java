/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.service;

import cz.muni.fi.pv243.mymaps.dto.MapPermission;
import cz.muni.fi.pv243.mymaps.dto.MyMap;
import cz.muni.fi.pv243.mymaps.dto.User;
import cz.muni.fi.pv243.mymaps.entities.Permission;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kuba
 */
public interface MapService {

    /**
     * Creates new map
     * 
     * @param myMap map to be created
     * @throws IllegalArgumentException if myMap is null
     * @return newly created map
     */
    public MyMap createMap(MyMap myMap);

    /**
     * Updates existing map
     * 
     * @param myMap map to be updated
     * @throws IllegalArgumentException if myMap is null
     * @return updated map
     */
    public MyMap updateMap(MyMap myMap);

    /**
     * Deletes map and all associated permissions
     * 
     * @param myMap map to be deleted
     * @throws IllegalArgumentException if myMap is null
     */
    public void deleteMap(MyMap myMap);

    /**
     * Returns map with specified ID
     * 
     * @param id id of map
     * @throws IllegalArgumentException if id is null
     * @return map with specified id
     */
    public MyMap getMapById(Long id);

    /**
     * Returns list of maps which are owned by specified user
     * 
     * @param user owner of maps to be found
     * @throws IllegalArgumentException if user is null
     * @return list of maps owned by specified user
     */
    public List<MyMap> getMapsByUser(User user);

    /**
     * Returns list of map permission for specified user
     * 
     * @param user user whose permissions are to be found
     * @throws IllegalArgumentException if user is null
     * @return list of map permissions for specified user
     */
    public List<MapPermission> getMapPermissionsForUser(User user);

    /**
     * Creates new map permission
     * 
     * @param user user to add permission to
     * @param myMap map to add permission to
     * @param permission level of permission to be allowed
     * @throws IllegalArgumentException if user, myMap or permission are null
     * @return newly created map permission
     */
    public MapPermission addPermision(User user, MyMap myMap, Permission permission);

    /**
     * Removes specified map permission
     * 
   
     * @param permission specified level of permission to be removed
     * @throws IllegalArgumentException if user, myMap or permission are null
     */
    public void removePermision(MapPermission mapPermission);

    /**
     * Returns map permission with specified ID
     * 
     * @param id id of map permission
     * @throws IllegalArgumentException if id is null
     * @return map permission with specified id
     */
    public MapPermission getMapPermissionById(Long id);

    /**
     * Returns list of map permission for specified user
     * 
     * @param myMap map whose permissions are to be found
     * @throws IllegalArgumentException if myMap is null
     * @return list of map permissions for specified map
     */
    public List<MapPermission> getMapPermissionsForMap(MyMap myMap);
    
    /**
     * Retrieves list of maps whose names are equal to the name suplied.
     *      
     * @throws IllegalArgumentException when name == null or name.length == 0
     * @param name name of the map to be searched
     * @return list of maps with name equal to the name suplied
     */
    List<MyMap> findMapsByName(String name);
    
    /**
     * Retrieves list of maps whose creation date are exactly equal to the date suplied.
     *      
     * @throws IllegalArgumentException when exactDate == null
     * @param exactDate date of the map to be searched
     * @return list of maps created exactly on given date
     */
    List<MyMap> findMapsByCreationDate(Date exactDate);
    
    /**
     * Retrieves list of maps whose creation date belongs into the interval (from, to).
     *      
     * @throws IllegalArgumentException when from == null or to == null or from > to
     * @param from from which date the map should be searched, must be less than or equal to date to
     * @param to to which date the map should be searched, must be greater than or equal to date from
     * @return list of maps created within given time range
     */
    List<MyMap> findMapsByCreationDate(Date from, Date to);
    
    /**
     * Retrieves all maps whose creator is creator suplied.
     *      
     * @throws IllegalArgumentException when creator == null 
     * @param creator creator of the maps to be searched
     * @return  list of maps created by given user
     */
    List<MyMap> findMapsByCreator(User creator);
}