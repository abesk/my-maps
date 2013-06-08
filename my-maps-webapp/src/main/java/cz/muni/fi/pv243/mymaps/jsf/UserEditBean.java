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
@Named(value = "userEditBean")
public class UserEditBean extends AbstractBean {

    private User user;

    private String name;
    private String nick;
    private String role;
    private String newUserText;

    public UserEditBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNewUserText() {
        return newUserText;
    }

    public void setNewUserText(String newUserText) {
        this.newUserText = newUserText;
    }


    public Role[] getRoles() {
        return Role.values();
    }
    

    public String editUserForward(long x) {

//        if (user == null || user.getId() == null) {
//            return "404.xhtml";
//        }
        user = userService.getUserById(x);
        return "editUser.xhtml?id=" + x;
    }

    public String editUser() {

        if (user == null || user.getId() == null) {
            return "404.xhtml";
        }

        //get user
        User userToUpdate = userService.getUserById(user.getId());

        //change what I want
        userToUpdate.setName(name);
        userToUpdate.setNick(nick);
        userToUpdate.setRole(role);

        //persist
        userService.updateUser(userToUpdate);

        //go back to list
        return "users.xhtml";
    }


   
}
