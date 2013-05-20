package cz.muni.fi.pv243.mymaps.entities;

import java.io.Serializable;
import org.hibernate.search.annotations.Field;

/**
 *
 * @author Jiri Holusa
 */
public class PointOfInterestEntity extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 19567189L;
    
    @Field
    private PointEntity location;

    public PointEntity getLocation() {
        return location;
    }

    public void setLocation(PointEntity location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "PointOfInterestEntity{" + "location=" + location + '}';
    }

}
