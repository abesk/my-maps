/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.jsf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pv243.mymaps.dto.MapPermission;
import cz.muni.fi.pv243.mymaps.dto.MyMap;
import cz.muni.fi.pv243.mymaps.dto.Point;
import cz.muni.fi.pv243.mymaps.dto.PointOfInterest;
import cz.muni.fi.pv243.mymaps.dto.User;
import cz.muni.fi.pv243.mymaps.dto.View;
import cz.muni.fi.pv243.mymaps.entities.Permission;
import cz.muni.fi.pv243.mymaps.service.MapService;
import cz.muni.fi.pv243.mymaps.service.UserService;
import cz.muni.fi.pv243.mymaps.service.ViewService;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author andrej
 */
@ManagedBean
@RequestScoped
@Named(value = "createMap")
public class CreateMapBean extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long selectedView;
    private List<String> updatePermission;
    private List<String> viewPermission;
    private String mapName;
    private String points;
    private View newView;
    private MyMap map;
    
    @Inject
    private org.jboss.logging.Logger log;
   

    public CreateMapBean() {
        newView = new View();
        newView.setNorthEast(new Point());
        newView.setSouthWest(new Point());
        map = new MyMap();
        map.setPointsOfInterest(new ArrayList<PointOfInterest>());
        

    }
    
    @PostConstruct
    public void init(){
        Object obj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(MAP_KEY);
        if (obj != null && obj instanceof Long) {
            Long id = (Long) obj;
            map = mapService.getMapById(id);
            if(!hasUserRights(getUser(), map, Permission.WRITE)){
                try {
                    FacesContext.getCurrentInstance().getExternalContext().dispatch("maps.xhtml");
                } catch (IOException ex) {
                    log.error(ex);
                }
            }
            if(map != null){
                mapName = map.getName();
                selectedView = map.getView().getId();
                List<MapPermission> permissions = mapService.getMapPermissionsForMap(map);
                if(permissions != null){
                    for(MapPermission permission : permissions){
                        if(permission.getPermission().equals(Permission.READ)){
                            if(viewPermission == null){
                                viewPermission = new ArrayList<String>();
                            }
                            viewPermission.add(permission.getUser().getId().toString());
                        }
                        else if(permission.getPermission().equals(Permission.WRITE)){
                            if(updatePermission == null){
                                updatePermission = new ArrayList<String>();
                            }
                            updatePermission.add(permission.getUser().getId().toString());
                        }
                    }
                }
            }
        }
        
    }
    public String getJsonMap(){
        String result = "null";
        try {
            ObjectMapper mapper = new ObjectMapper();
            result = mapper.writeValueAsString(map);
            
        } catch (JsonProcessingException ex) {
            Logger.getLogger(CreateMapBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
        
    }
    public MyMap getMap() {
        return map;
    }

    public void setMap(MyMap map) {
        this.map = map;
    }

    public View getNewView() {
        return newView;
    }

    public void setNewView(View newView) {
        this.newView = newView;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public List<String> getViewPermission() {
        return viewPermission;
    }

    public void setViewPermission(List<String> viewPermission) {
        this.viewPermission = viewPermission;
    }

    public Long getSelectedView() {
        return selectedView;
    }

    public List<String> getUpdatePermission() {
        return updatePermission;
    }

    public void setUpdatePermission(List<String> updatePermission) {
        this.updatePermission = updatePermission;
    }

    public void setSelectedView(Long selectedView) {
        this.selectedView = selectedView;
    }

    public String createMap() {
        try {
            ObjectMapper mapper = new ObjectMapper();
             List<PointOfInterest> pointOfInterests = new ArrayList<PointOfInterest>();
            if (points != null && !points.isEmpty()) {
                JsonNode node = mapper.readTree(points);
                Iterator<JsonNode> elements = node.elements();
               
                while (elements.hasNext()) {
                    JsonNode pointNode = elements.next();
                    PointOfInterest pointOfInterest = new PointOfInterest();
                    pointOfInterest.setIconPath(pointNode.get("iconPath").textValue());
                    pointOfInterest.setDescription(pointNode.get("desc").textValue());
                    Point coord = new Point();
                    coord.setLatitude(pointNode.get("lat").decimalValue());
                    coord.setLongitude(pointNode.get("lng").decimalValue());
                    pointOfInterest.setPoint(coord);
                    pointOfInterests.add(pointOfInterest);

                }
               
            }
            if(map == null){
                map = new MyMap();
            }
            map.setCreator(getUser());
            map.setPointsOfInterest(pointOfInterests);
            map.setName(mapName);
            map.setCreationDate(new Date());
            View mapView = viewService.getViewById(selectedView);
            map.setView(mapView);
            if(map.getId() == null){
                map = mapService.createMap(map);
            }
            else{
                map = mapService.updateMap(map);
                List<MapPermission> oldPermissions = mapService.getMapPermissionsForMap(map);
                for(MapPermission permission : oldPermissions){
                    mapService.removePermision(permission);
                }
            }
            
            for (String id : updatePermission) {
                User user = userService.getUserById(new Long(id));
                
                mapService.addPermision(user, map, Permission.WRITE);
            }
            for (String id : viewPermission) {
                User user = userService.getUserById(new Long(id));
                mapService.addPermision(user, map, Permission.READ);
            }
            


        } catch (IOException ex) {
            Logger.getLogger(CreateMapBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "maps.xhtml";
    }

    public void createView(AjaxBehaviorEvent event) {

        User user = getUser();
       

        viewService.createView(newView, getUser());


    }

    public List<View> getViews() {

        Collection<View> viewsByUser = viewService.getViewsByUser(getUser());
        List<View> views = new ArrayList<View>();
        for (View view : viewsByUser) {
            views.add(view);
        }
        return views;

    }

    public List<User> getUsers() {
        return userService.geAllUsers();
    }

    
}
