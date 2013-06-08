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
 * @author Kuba
 */
@ManagedBean
@RequestScoped
@Named(value = "userCreateBean")
public class UserCreateBean extends AbstractBean {

    private User user;

    private String name;
    private String nick;
    private String password;
    private String password2;
    private String role;
    private String newUserText;

    public UserCreateBean() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password) {
        this.password2 = password;
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

    public String createForward() {
        return "createUser.xhtml";
    }

    public String createUser() {

        User newUser = new User();
        newUser.setName(name);
        newUser.setNick(nick);
        newUser.setRole(role);
        newUser.setPassword("");
        newUser = userService.createUser(newUser);

        newUserText = "User: " + newUser.getNick() + " created. PASSWORD is: " + newUser.getPassword();

        return "users.xhtml";
    }

    public List<User> getUsers() {

        return userService.geAllUsers();
    }

}
