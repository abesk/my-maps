/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.jsf;

import cz.muni.fi.pv243.mymaps.dto.MyMap;
import java.util.Date;
import java.util.List;
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
            mapsList = mapService.getMapsByUser(getUser());
        }
        
        return mapsList;
    } 
    
    public void getAllMaps(){
        mapsList = mapService.getMapsByUser(getUser());
    }
    
    public void searchByName(){
        mapsList = mapService.findMapsByName(searchName);
    }
    
    public void searchByDate(){
        mapsList = mapService.findMapsByCreationDate(searchDateFrom, searchDateTo);
    }
    
    public String goToMap(Long id){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(MAP_KEY, id);
        return "createMap.xhtml";
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
