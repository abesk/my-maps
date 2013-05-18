package cz.muni.fi.pv243.mymaps.entities;

import java.io.Serializable;
import org.hibernate.search.annotations.*;

/**
 *
 * @author Jiri Holusa
 */
public class MapPermissionEntity extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 19567189L;
    
    @Field
    private UserEntity user;
    
    @Field
    private MyMapEntity map;
    
    @Field
    private Permission permission;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public MyMapEntity getMap() {
        return map;
    }

    public void setMap(MyMapEntity map) {
        this.map = map;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }    

    @Override
    public String toString() {
        return "cz.muni.fi.pv243.mymaps.entities.MapPermission[ id=" + id + " ]";
    }
    
}
