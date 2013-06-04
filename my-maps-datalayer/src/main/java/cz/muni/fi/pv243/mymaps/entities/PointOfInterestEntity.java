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
    
    @Field
    private String description;

    @Field
    private String iconPath;

    public PointEntity getLocation() {
        return location;
    }

    public void setLocation(PointEntity location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    @Override
    public String toString() {
        return "PointOfInterestEntity{" + "location=" + location + ", description=" + description + ", iconPath=" + iconPath + '}';
    }

}
