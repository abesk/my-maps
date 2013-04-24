package cz.muni.fi.pv243.mymaps.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
