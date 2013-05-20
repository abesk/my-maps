package cz.muni.fi.pv243.mymaps.entities;

import java.io.Serializable;
import org.hibernate.search.annotations.Field;

/**
 *
 * @author Jiri Holusa
 */
public class ViewEntity extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 19567189L;
    
    @Field
    private PointEntity southWest;
    
    @Field
    private PointEntity northEast;

    @Field
    private MapType mapType;

    public PointEntity getSouthWest() {
        return southWest;
    }

    public void setSouthWest(PointEntity southWest) {
        this.southWest = southWest;
    }

    public PointEntity getNorthEast() {
        return northEast;
    }

    public void setNorthEast(PointEntity northEast) {
        this.northEast = northEast;
    }

    public MapType getMapType() {
        return mapType;
    }

    public void setMapType(MapType mapType) {
        this.mapType = mapType;
    }

    @Override
    public String toString() {
        return "ViewEntity{" + "southWest=" + southWest + ", northEast=" + northEast + ", mapType=" + mapType + '}';
    }
 
}
