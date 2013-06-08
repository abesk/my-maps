/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.security;

import cz.muni.fi.pv243.mymaps.entities.Role;
import cz.muni.fi.pv243.mymaps.service.MapService;
import cz.muni.fi.pv243.mymaps.service.UserService;
import javax.inject.Inject;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;

/**
 *
 * @author andrej
*/
public class Restrictions {
    
    @Inject
    MapService mapService;
    
    @Inject
    UserService userService;

    @Secures
    @Admin
    public boolean isAdmin(Identity identity) {

        if (!identity.isLoggedIn()) {
            return false;
        }
        cz.muni.fi.pv243.mymaps.dto.User user = userService.getUserById(new Long(identity.getUser().getId()));
       //return "admin".equals(((User) identity.getUser()).getRole());
        return user.getRole().equals(Role.Admin.toString());
       
    }

    @Secures
    @User
    public boolean isUser(Identity identity) {

        if (!identity.isLoggedIn()) {
            return false;
        }
        return true;
    }
    
    
}
