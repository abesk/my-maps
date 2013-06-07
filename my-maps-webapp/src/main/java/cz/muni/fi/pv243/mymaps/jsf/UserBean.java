/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.jsf;

import cz.muni.fi.pv243.mymaps.dto.User;
import cz.muni.fi.pv243.mymaps.dto.View;
import cz.muni.fi.pv243.mymaps.entities.Role;
import cz.muni.fi.pv243.mymaps.service.UserService;
import cz.muni.fi.pv243.mymaps.service.ViewService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Kuba
 */
@ManagedBean
@SessionScoped
@Named(value = "userBean")
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String nick;
    private String password;
    private String role;
    private String newUserText;
    private Role roles;
    private List<View> views;
    
    private List<User> users;
     
    @EJB
    UserService userService;
    
    @EJB
    ViewService viewService;

    public UserBean() {
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

    public List<View> getViews() {
        return views;
    }

    public void setViews(List<View> views) {
        this.views = views;
    }

    
     public Role[] getRoles() {
        return Role.values();
    }
    public String createUser() {
            
            User newUser = new User();
            newUser.setName(name);
            newUser.setNick(nick);
            newUser.setRole(role);
            views = new ArrayList<>();
            newUser.setViews(views);
            newUser = userService.createUser(newUser);

            newUserText = "User: " + newUser.getNick() + " created. Your PASSWORD is: " + newUser.getPassword();
            
        return "createUser.xhtml";
    }


    public List<User> getUsers() {
        return userService.geAllUsers();
    }

}
