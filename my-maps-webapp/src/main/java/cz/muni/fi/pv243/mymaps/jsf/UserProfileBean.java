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
@Named(value = "userProfileBean")
public class UserProfileBean extends AbstractBean {

    @Inject
    LoginBean loginBean;

    private User user;
    private String name;
    private String nick;
    private String role;

    public UserProfileBean() {
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

}
