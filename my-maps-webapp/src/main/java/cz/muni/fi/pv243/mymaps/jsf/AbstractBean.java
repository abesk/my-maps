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
        String id = identity.getUser().getId();
        return userService.getUserById(new Long(id));

        

    }
    
}
