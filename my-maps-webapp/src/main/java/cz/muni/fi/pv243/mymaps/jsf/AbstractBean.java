/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.jsf;

import cz.muni.fi.pv243.mymaps.dto.User;
import cz.muni.fi.pv243.mymaps.dto.View;
import cz.muni.fi.pv243.mymaps.service.MapService;
import cz.muni.fi.pv243.mymaps.service.UserService;
import cz.muni.fi.pv243.mymaps.service.ViewService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

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
    protected User getUser() {
        List<User> allUsers = userService.geAllUsers();
        if (allUsers == null || allUsers.isEmpty()) {
            User user = new User();
            user.setName("sdf");
            user.setPassword("sdf");
            user.setNick("sdf");
            user.setViews(new ArrayList<View>());
            userService.createUser(user);
            allUsers = userService.geAllUsers();
        }

        return allUsers.get(0);

    }
    
}
