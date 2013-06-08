package cz.muni.fi.pv243.mymaps.jsf;

import cz.muni.fi.pv243.mymaps.dto.User;
import java.io.IOException;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Jan Bliznak
 */
@ManagedBean
@RequestScoped
@Named(value = "userProfileEditBean")
public class UserProfileEditBean extends AbstractBean {

    @Inject
    LoginBean loginBean;

    private User user;
    private String name;
    private String nick;
    private String role;
    private String password1;
    private String password2;

    public UserProfileEditBean() {
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

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @PostConstruct
    public void init() throws IOException {

        user = loginBean.getCurrentUser();

        if (user == null) //shouldn't be in the profile!
        {
            //redirect to index
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }

        name = user.getName();
        nick = user.getNick();
        role = user.getRole();

    }

    public String showProfileForward() {
        return "profile.xhtml";
    }

    public String editProfileForward() {
        return "editProfile.xhtml";
    }

    public String editProfile() {

        try {
            if (password1 == null || !password1.equals(password2)) {
                //FacesMessages.instance().add("Passwords must be the same");
                return null;
            }

            //get current user data
            User toEdit = user;

            //get updated data
            toEdit.setName(name);
            toEdit.setPassword(password1);

            //perist change
            userService.updateUser(toEdit);

            //go back to profile
            return "profile.xhtml";
        } catch (Exception e) {
            return null;
        }
    }

}
