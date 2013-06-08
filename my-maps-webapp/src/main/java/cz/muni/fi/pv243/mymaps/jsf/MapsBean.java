/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.jsf;

import cz.muni.fi.pv243.mymaps.dto.MapPermission;
import cz.muni.fi.pv243.mymaps.dto.MyMap;
import cz.muni.fi.pv243.mymaps.dto.User;
import cz.muni.fi.pv243.mymaps.entities.Permission;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


/**
 *
 * @author Jiri Holusa
 */
@ManagedBean
@Named(value = "userMaps")
@RequestScoped
public class MapsBean extends AbstractBean{
    
    private String searchName;    
    private Date searchDateFrom;
    private Date searchDateTo;
    
    private List<MyMap> mapsList;
    
    public List<MyMap> getMaps(){
        if(mapsList == null){
            List<MapPermission> mapPermissionList = mapService.getMapPermissionsForUser(getUser());
            
            Set<MyMap> set = new HashSet<>();            
            for(MapPermission permission: mapPermissionList){
                set.add(permission.getMap());
            }
            
            mapsList = new ArrayList<>();
            mapsList.addAll(set);
        }           
        
        return mapsList;
    } 
    
    public void getAllMaps(){
        mapsList = null;
    }
    
    public void searchByName(){
        List<MapPermission> mapPermissionList = mapService.getMapPermissionsForUser(getUser());
        
        Set<MyMap> set = new HashSet<>();            
        for(MapPermission permission: mapPermissionList){
            set.add(permission.getMap());
        }
        
        List<MyMap> list = new ArrayList<>();
        list.addAll(set);
        
        List<MyMap> searchedMaps = mapService.findMapsByName(searchName);
        
        mapsList = new ArrayList<>();
        for(MyMap map: searchedMaps){
            if(list.contains(map)){
                mapsList.add(map);
            }
        }        
    }
    
    public void searchByDate(){
        List<MapPermission> mapPermissionList = mapService.getMapPermissionsForUser(getUser());
        Set<MyMap> set = new HashSet<>();            
        for(MapPermission permission: mapPermissionList){
            set.add(permission.getMap());
        }
        
        List<MyMap> list = new ArrayList<>();
        list.addAll(set);
        
        List<MyMap> searchedMaps = mapService.findMapsByCreationDate(searchDateFrom, searchDateTo);
        
        mapsList = new ArrayList<>();
        for(MyMap map: searchedMaps){
            if(list.contains(map)){
                mapsList.add(map);
            }
        } 
    }
    
    public String goToMap(Long id){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(MAP_KEY, id);
        User user = getUser();
        MyMap map = mapService.getMapById(id);
        if(hasUserRights(user, map, Permission.WRITE)){
            return "createMap.xhtml";
        }
        return "viewMap.xhtml";
        
        
    }
    
    public String deleteMap(Long id){
        MyMap mapById = mapService.getMapById(id);
        mapService.deleteMap(mapById);
        return "maps.xhtml";
    }
    
    public String newMap(){
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(MAP_KEY);
         return "createMap.xhtml";
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public Date getSearchDateFrom() {
        return searchDateFrom;
    }

    public void setSearchDateFrom(Date searchDateFrom) {
        this.searchDateFrom = searchDateFrom;
    }

    public Date getSearchDateTo() {
        return searchDateTo;
    }

    public void setSearchDateTo(Date searchDateTo) {
        this.searchDateTo = searchDateTo;
    }
    
}
