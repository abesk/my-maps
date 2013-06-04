/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.service;

import cz.muni.fi.pv243.mymaps.dto.MapPermission;
import cz.muni.fi.pv243.mymaps.dto.MyMap;
import cz.muni.fi.pv243.mymaps.dto.PointOfInterest;
import cz.muni.fi.pv243.mymaps.dto.User;
import cz.muni.fi.pv243.mymaps.entities.Permission;
import java.util.List;

/**
 *
 * @author Kuba
 */
public interface MapService {

    public void createMap(MyMap myMap);

    public void updateMap(MyMap myMap);

    public void deleteMap(MyMap myMap);

    public MyMap getMapById(Long id);

    public List<MyMap> getMapsByUser(User user);

    public List<MapPermission> getMapPermissionsForUser(User user);

    public void addPermision(User user, MyMap myMap, Permission permission);

    public void removePermision(User user, MyMap myMap, Permission permission);

    public MapPermission getMapPermissionById(Long id);

    public List<MapPermission> getMapPermissionsForMap(MyMap myMap);
}
