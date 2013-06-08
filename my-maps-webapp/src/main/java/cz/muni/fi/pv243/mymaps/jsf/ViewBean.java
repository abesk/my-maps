/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.jsf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pv243.mymaps.dto.MyMap;
import cz.muni.fi.pv243.mymaps.dto.PointOfInterest;
import cz.muni.fi.pv243.mymaps.entities.Permission;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author andrej
 */
@ManagedBean
@RequestScoped
@Named(value = "viewMap")
public class ViewBean extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private MyMap map;
    @Inject
    private org.jboss.logging.Logger log;

    public ViewBean() {

        map = new MyMap();
        map.setPointsOfInterest(new ArrayList<PointOfInterest>());


    }

    @PostConstruct
    public void init() {
        Object obj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(MAP_KEY);
        if (obj != null && obj instanceof Long) {
            Long id = (Long) obj;
            map = mapService.getMapById(id);
        }
        obj = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (obj != null && obj instanceof Long) {
            Long id = (Long) obj;
            map = mapService.getMapById(id);
        }
        if(map.getId() != null){
            if(!hasUserRights(getUser(), map, Permission.READ)){
                try {
                    FacesContext.getCurrentInstance().getExternalContext().dispatch("maps.xhtml");
                } catch (IOException ex) {
                    log.error(ex);
                }
            }
        }


    }

    public String getJsonMap() {
        String result = "null";
        try {
            ObjectMapper mapper = new ObjectMapper();
            result = mapper.writeValueAsString(map);

        } catch (JsonProcessingException ex) {
            Logger.getLogger(ViewBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }

    public MyMap getMap() {
        return map;
    }

    public void setMap(MyMap map) {
        this.map = map;
    }

  
}
