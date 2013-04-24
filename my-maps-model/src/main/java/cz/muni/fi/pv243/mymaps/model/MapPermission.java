package cz.muni.fi.pv243.mymaps.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: andrej
 * Date: 4/24/13
 * Time: 6:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class MapPermission {

    @NotNull
    @Valid
    private User user;

    @NotNull
    @Valid
    private MyMap map;

    @NotNull
    private Permission permission;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MyMap getMap() {
        return map;
    }

    public void setMap(MyMap map) {
        this.map = map;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
