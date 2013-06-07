/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.jsf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pv243.mymaps.dto.MyMap;
import cz.muni.fi.pv243.mymaps.dto.Point;
import cz.muni.fi.pv243.mymaps.dto.PointOfInterest;
import cz.muni.fi.pv243.mymaps.dto.User;
import cz.muni.fi.pv243.mymaps.dto.View;
import cz.muni.fi.pv243.mymaps.service.MapService;
import cz.muni.fi.pv243.mymaps.service.UserService;
import cz.muni.fi.pv243.mymaps.service.ViewService;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import org.apache.avro.generic.GenericData;

/**
 *
 * @author andrej
 */
@ManagedBean
@SessionScoped
@Named(value = "createMap")
public class CreateMapBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private BigDecimal northEastLat;
    private BigDecimal northEastLng;
    private BigDecimal southWestLat;
    private BigDecimal southWestLng;
    private Long selectedView;
    private String viewName;
    private List<Long> updatePermission;
    private List<Long> viewPermission;
    private String mapName;
    private String points;
            
    @EJB
    MapService mapService;
    @EJB
    ViewService viewService;
    @EJB
    UserService userService;

    public CreateMapBean() {
        viewName = "fds";
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
    
    
    
    public List<Long> getViewPermission() {
        return viewPermission;
    }

    public void setViewPermission(List<Long> viewPermission) {
        this.viewPermission = viewPermission;
    }
    
    
    public Long getSelectedView() {
        return selectedView;
    }

    public List<Long> getUpdatePermission() {
        return updatePermission;
    }

    public void setUpdatePermission(List<Long> updatePermission) {
        this.updatePermission = updatePermission;
    }
    
    

    public void setSelectedView(Long selectedView) {
        this.selectedView = selectedView;
    }
    
    
    
   public String createMap() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node =  mapper.readTree(points);
           Iterator<JsonNode> elements = node.elements();
           List<PointOfInterest> pointOfInterests = new ArrayList<PointOfInterest>();
           while(elements.hasNext()){
               JsonNode pointNode = elements.next();
               PointOfInterest pointOfInterest = new PointOfInterest();
               pointOfInterest.setIconPath(pointNode.get("iconPath").textValue());
               pointOfInterest.setDescription(pointNode.get("desc").textValue());
               Point coord = new  Point();
               coord.setLatitude(pointNode.get("lat").decimalValue());
               coord.setLongitude(pointNode.get("lng").decimalValue());
               pointOfInterest.setPoint(coord);
               pointOfInterests.add(pointOfInterest);
               
           }
           MyMap newMap = new MyMap();
           newMap.setCreator(getUser());
           newMap.setPointsOfInterest(pointOfInterests);
           newMap.setName(mapName);
           newMap.setCreationDate(new Date());
           View mapView = viewService.getViewById(selectedView);
           newMap.setView(mapView);
           mapService.createMap(newMap);
           
            
        } catch (IOException ex) {
            Logger.getLogger(CreateMapBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index.xhtml";
   }

    public void createView(AjaxBehaviorEvent event) {
        View newView = new View();
        newView.setName(viewName);
        Point ne = new Point();
        ne.setLatitude(northEastLat);
        ne.setLongitude(northEastLat);
        Point sw = new Point();
        sw.setLatitude(southWestLat);
        sw.setLongitude(southWestLng);
        newView.setNorthEast(ne);
        newView.setSouthWest(sw);
        User user = getUser();
        Collection<View> views1 = user.getViews();       
        user.setViews(views1);
        
        viewService.createView(newView, getUser());
        

    }

  

    public List<View> getViews() {
        
        Collection<View> viewsByUser = viewService.getViewsByUser(getUser());
        List<View> views = new ArrayList<View>();
        for(View view : viewsByUser){
            views.add(view);
        }
        return views;

    }
    
    public List<User> getUsers(){
        return userService.geAllUsers();
    }

    public BigDecimal getNorthEastLat() {
        return northEastLat;
    }

    public void setNorthEastLat(BigDecimal northEastLat) {
        this.northEastLat = northEastLat;
    }

    public BigDecimal getNorthEastLng() {
        return northEastLng;
    }

    public void setNorthEastLng(BigDecimal northEastLng) {
        this.northEastLng = northEastLng;
    }

    public BigDecimal getSouthWestLat() {
        return southWestLat;
    }

    public void setSouthWestLat(BigDecimal southWestLat) {
        this.southWestLat = southWestLat;
    }

    public BigDecimal getSouthWestLng() {
        return southWestLng;
    }

    public void setSouthWestLng(BigDecimal southWestLng) {
        this.southWestLng = southWestLng;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
    
    private User getUser(){
        List<User> allUsers = userService.geAllUsers();
        if(allUsers == null || allUsers.isEmpty()){
            User user = new User();
            user.setName("sdf");
            user.setPassword("sdf");
            user.setNick("sdf");
            user.setViews(new ArrayList<View>());
            userService.createUser(user);
            allUsers = userService.geAllUsers();
        }
        
        return allUsers.get(0);
        
    }
}
