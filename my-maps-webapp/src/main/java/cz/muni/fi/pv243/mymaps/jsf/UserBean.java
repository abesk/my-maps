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

    private User user;

    private String name;
    private String nick;
    private String password;
    private String password2;
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
        newUser.setPassword(password);
        views = new ArrayList<>();
        newUser.setViews(views);
        newUser = userService.createUser(newUser);

        newUserText = "User: " + newUser.getNick() + " created. PASSWORD is: " + newUser.getPassword();

        return "createUser.xhtml";
    }

    public List<User> getUsers() {

        User u1 = new User();
        u1.setName("Franta");
        u1.setPassword("Franta");
        u1.setNick("xfranta");
        u1.setRole(Role.User.toString());

        User u2 = new User();
        u2.setName("Petr");
        u2.setPassword("Franta");
        u2.setNick("xpetr");
        u2.setRole(Role.User.toString());

        User u3 = new User();
        u3.setName("Pepa");
        u3.setPassword("Franta");
        u3.setNick("xpepa");
        u3.setRole(Role.User.toString());

        userService.createUser(u1);
        System.out.println("inserted user1");
        userService.createUser(u2);
        System.out.println("inserted user2");
        userService.createUser(u3);
        System.out.println("inserted user3");

        return userService.geAllUsers();
    }

    public String editUserForward(User user) {

        if (user == null || user.getId() == null) {
            return "404.xhtml";
        }

        return "editUser.xhtml?id=" + user.getId();
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

    public String deleteUser(User user) {

        if (user == null || user.getId() == null) {
            return "404.xhtml";
        }

        userService.deleteUser(user);

        return "users.xhtml";
    }

    public String showProfile() {
        return "profile.xhtml";
    }

    public String editProfileForward() {
        return "editProfile.xhtml";
    }

    public String editProfile() {
        //TODO

        //get current user data
        //get updated data
        //perist change
        //go back to profile
        return "profile.xhtml";
    }
}
