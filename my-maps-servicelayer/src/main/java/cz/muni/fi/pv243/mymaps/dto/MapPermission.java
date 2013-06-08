package cz.muni.fi.pv243.mymaps.dto;

import cz.muni.fi.pv243.mymaps.entities.Permission;
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
    
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
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