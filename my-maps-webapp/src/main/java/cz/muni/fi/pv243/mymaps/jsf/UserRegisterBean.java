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
@Named(value = "userRegisterBean")
public class UserRegisterBean extends AbstractBean {

    private User user;

    private String name;
    private String nick;
    private String password;
    private String password2;
    private String role = Role.User.toString();
    private String newUserText;
    

    public UserRegisterBean() {
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

   
    public List<User> getUsers() {

        return userService.geAllUsers();
    }

    

    public String register() {

        System.out.println(name);
        System.out.println(nick);
        System.out.println(role);
        System.out.println(password);
        System.out.println(password2);

        if (password == null || !password.equals(password2)) {
            return "registration.xhtml";
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setNick(nick);
        newUser.setRole(role);
        newUser.setPassword(password);

        User u = userService.createUser(newUser);
        return "login.xhtml";
    }

    public String getCurrentUserToString() {
        User u = getUser();

        if (u == null) {
            return "";
        }
        String label = "";
        if (u.getNick() != null) {
            label += u.getNick();
        }

        if (u.getNick() != null && u.getName() != null && !u.getName().isEmpty()) {
            label += " ( " + u.getName() + " )";
        }

        return label;
    }
}
