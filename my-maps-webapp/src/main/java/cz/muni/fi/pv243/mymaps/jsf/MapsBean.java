/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.jsf;

import cz.muni.fi.pv243.mymaps.dto.MyMap;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
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
    
    public List<MyMap> getMaps(){
        return mapService.getMapsByUser(getUser());
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
    
    
    
}
