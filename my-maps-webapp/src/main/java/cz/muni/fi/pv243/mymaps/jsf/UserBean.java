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
@Named(value = "userBean")
public class UserBean extends AbstractBean {

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
