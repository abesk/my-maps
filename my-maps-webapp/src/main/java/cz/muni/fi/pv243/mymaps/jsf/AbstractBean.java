/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.jsf;

import cz.muni.fi.pv243.mymaps.dto.MapPermission;
import cz.muni.fi.pv243.mymaps.dto.MyMap;
import cz.muni.fi.pv243.mymaps.dto.User;
import cz.muni.fi.pv243.mymaps.dto.View;
import cz.muni.fi.pv243.mymaps.entities.Permission;
import cz.muni.fi.pv243.mymaps.service.MapService;
import cz.muni.fi.pv243.mymaps.service.UserService;
import cz.muni.fi.pv243.mymaps.service.ViewService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.jboss.seam.security.Identity;

/**
 *
 * @author andrej
 */
public class AbstractBean {

    protected static final String MAP_KEY = "mapId";
    @Inject
    protected MapService mapService;
    @Inject
    protected ViewService viewService;
    @Inject
    protected UserService userService;
    @Inject
    Identity identity;

    protected User getUser() {
        if (identity.isLoggedIn()) {
            String id = identity.getUser().getId();
            return userService.getUserById(new Long(id));
        }
        return userService.getUserByLogin(UserService.UNREGISTRED_LOGIN_NAME);



    }

    protected boolean hasUserRights(User user, MyMap map, Permission permission) {
        List<MapPermission> mapPermissionsForUser = mapService.getMapPermissionsForUser(user);
        for (MapPermission mapPermission : mapPermissionsForUser) {
            if (mapPermission.getMap().equals(map) && mapPermission.getPermission().equals(permission)) {
                return true;
            }
        }
        return false;
    }
}
