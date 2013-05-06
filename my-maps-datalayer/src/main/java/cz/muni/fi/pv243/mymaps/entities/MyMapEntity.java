/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;

/**
 *
 * @author Jiri Holusa
 */
public class MyMapEntity extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 19567189L;
    
    @Field
    private String name;
    
    @Field
    private ViewEntity view;
    
    @Field
    @DateBridge(resolution= Resolution.MILLISECOND)
    private Date creationDate;
    
    @Field
    private UserEntity creator; 
    
    @IndexedEmbedded    
    private List<PointOfInterestEntity> pointsOfInterest;

    @Override
    public String toString() {
        return "cz.muni.fi.pv243.mymaps.entities.MyMapEntity[ id=" + id + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ViewEntity getView() {
        return view;
    }

    public void setView(ViewEntity view) {
        this.view = view;
    }

    public Date getCreationDate() {
        return new Date(creationDate.getTime());
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = new Date(creationDate.getTime());
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    public List<PointOfInterestEntity> getPointsOfInterest() {
        return Collections.unmodifiableList(pointsOfInterest);
    }

    public void setPointsOfInterest(Collection<PointOfInterestEntity> pointsOfInterest) {
        this.pointsOfInterest = new ArrayList<>();
        this.pointsOfInterest.addAll(pointsOfInterest);
    }
    
}
