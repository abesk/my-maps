/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.serviceImpl;

import cz.muni.fi.pv243.mymaps.dao.MapPermissionDao;
import cz.muni.fi.pv243.mymaps.dao.MyMapDao;
import cz.muni.fi.pv243.mymaps.dto.MapPermission;
import cz.muni.fi.pv243.mymaps.dto.MyMap;
import cz.muni.fi.pv243.mymaps.dto.User;
import cz.muni.fi.pv243.mymaps.entities.MapPermissionEntity;
import cz.muni.fi.pv243.mymaps.entities.MyMapEntity;
import cz.muni.fi.pv243.mymaps.entities.Permission;
import cz.muni.fi.pv243.mymaps.entities.UserEntity;
import cz.muni.fi.pv243.mymaps.service.MapService;
import cz.muni.fi.pv243.mymaps.util.EntityDTOconvertor;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.jboss.logging.Logger;

/**
 *
 * @author Kuba
 */
public class MapServiceImpl implements MapService {

    @Inject
    private MyMapDao myMapDao;
    @Inject
    private MapPermissionDao mapPermissionDao;
    @Inject
    private Logger log;

    @Override
    public void createMap(MyMap myMap) {
        if (myMap == null) {
            String msg = "Map can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        MyMapEntity newMyMap = EntityDTOconvertor.convertMyMap(myMap);

        myMapDao.create(newMyMap);

    }

    @Override
    public void updateMap(MyMap myMap) {
        if (myMap == null) {
            String msg = "Map can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        MyMapEntity newMyMap = EntityDTOconvertor.convertMyMap(myMap);

        myMapDao.update(newMyMap);
    }

    @Override
    public void deleteMap(MyMap myMap) {
        if (myMap == null) {
            String msg = "Map can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        MyMapEntity myMapEntity = EntityDTOconvertor.convertMyMap(myMap);
        List<MapPermissionEntity> mapPermissions = mapPermissionDao.getMapsPermissions(myMapEntity);

        for (MapPermissionEntity p : mapPermissions) {

            mapPermissionDao.delete(p);
        }

        myMapDao.delete(myMapEntity);
    }

    @Override
    public MyMap getMapById(Long id) {
        if (id == null) {
            String msg = "ID can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        return EntityDTOconvertor.convertMyMap(myMapDao.getById(id));
    }

    @Override
    public List<MyMap> getMapsByUser(User user) {
        if (user == null) {
            String msg = "User can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        UserEntity userEntity = EntityDTOconvertor.convertUser(user);
        List<MyMap> mapList = new ArrayList<>();
        List<MyMapEntity> mapEntityList = myMapDao.findMapsByCreator(userEntity);

        for (MyMapEntity m : mapEntityList) {
            mapList.add(EntityDTOconvertor.convertMyMap(m));
        }

        return mapList;
    }

    @Override
    public List<MapPermission> getMapPermissionsForUser(User user) {
        if (user == null) {
            String msg = "User can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        UserEntity userEntity = EntityDTOconvertor.convertUser(user);
        List<MapPermission> permissionList = new ArrayList<>();
        List<MapPermissionEntity> mapPermissions = mapPermissionDao.getUsersPermissions(userEntity);

        for (MapPermissionEntity p : mapPermissions) {
            permissionList.add(EntityDTOconvertor.convertMapPermission(p));
        }

        return permissionList;
    }

    @Override
    public void addPermision(User user, MyMap myMap, Permission permission) {
        if (user == null || myMap == null || permission == null) {
            String msg = "User, Map and Permission can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        MapPermission mapPermission = new MapPermission();
        mapPermission.setUser(user);
        mapPermission.setMap(myMap);
        mapPermission.setPermission(permission);

        mapPermissionDao.create(EntityDTOconvertor.convertMapPermission(mapPermission));

    }

    @Override
    public void removePermision(User user, MyMap myMap, Permission permission) {
        if (permission == null) {
            String msg = "User, Map and Permission can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        MapPermission mapPermission = new MapPermission();
        mapPermission.setUser(user);
        mapPermission.setMap(myMap);
        mapPermission.setPermission(permission);

        mapPermissionDao.delete(EntityDTOconvertor.convertMapPermission(mapPermission));

    }

    @Override
    public MapPermission getMapPermissionById(Long id) {
        if (id == null) {
            String msg = "ID can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        return EntityDTOconvertor.convertMapPermission(mapPermissionDao.getById(id));
    }

    @Override
    public List<MapPermission> getMapPermissionsForMap(MyMap myMap) {
        if (myMap == null) {
            String msg = "Map can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        MyMapEntity myMapEntity = EntityDTOconvertor.convertMyMap(myMap);
        List<MapPermission> permissionList = new ArrayList<>();
        List<MapPermissionEntity> mapPermissions = mapPermissionDao.getMapsPermissions(myMapEntity);

        for (MapPermissionEntity p : mapPermissions) {
            permissionList.add(EntityDTOconvertor.convertMapPermission(p));
        }

        return permissionList;
    }
}
