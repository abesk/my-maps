/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.service;

import cz.muni.fi.pv243.mymaps.dto.MapPermission;
import cz.muni.fi.pv243.mymaps.dto.MyMap;
import cz.muni.fi.pv243.mymaps.dto.PointOfInterest;
import cz.muni.fi.pv243.mymaps.dto.User;
import cz.muni.fi.pv243.mymaps.dto.View;
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

    public void createMapPermission(MapPermission mapPermission);

    public void updateMapPermission(MapPermission mapPermission);

    public void deleteMapPermission(MapPermission mapPermission);

    public MapPermission getMapPermissionById(Long id);

    public List<MapPermission> getMapPermissionsForMap(MyMap myMap);

    public void createPointOfInterest(PointOfInterest pointOfInterest);

    public void updatePointOfInterest(PointOfInterest pointOfInterest);

    public void deletePointOfInterest(PointOfInterest pointOfInterest);

    public PointOfInterest getPointOfInterestById(Long id);
}
