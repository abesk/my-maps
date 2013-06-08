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
@Named(value = "userListBean")
public class UserListBean extends AbstractBean {

    public UserListBean() {
    }

    public List<User> getUsers() {
        return userService.geAllUsers();
    }
}
