package cz.muni.fi.pv243.mymaps.jsf;

import cz.muni.fi.pv243.mymaps.dto.User;
import cz.muni.fi.pv243.mymaps.dto.View;
import cz.muni.fi.pv243.mymaps.entities.Role;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Jan Bliznak
 */
@ManagedBean
@RequestScoped
@Named(value = "userDeleteBean")
public class UserDeleteBean extends AbstractBean {

    public UserDeleteBean() {
    }

    public String deleteUser(long id) {

        User u = userService.getUserById(id);
        userService.deleteUser(u);

        return "users.xhtml";
    }
}
