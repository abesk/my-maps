package cz.muni.fi.pv243.mymaps.entities;

import java.io.Serializable;
import org.hibernate.search.annotations.Field;

/**
 *
 * @author Jiri Holusa
 */
public class UserEntity extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 19567189L;
    
    @Field
    private String login;        
    @Field
    private String passwordHash;        
    @Field
    private String name;        

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserEntity{" + "login=" + login + ", passwordHash=" + passwordHash + ", name=" + name + '}';
    }
    
}
