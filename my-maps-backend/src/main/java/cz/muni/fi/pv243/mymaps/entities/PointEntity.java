/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import org.hibernate.search.annotations.Field;

/**
 *
 * @author Jiøí Holuša
 */
public class PointEntity extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 19567189L;    
    
    @Field
    private BigDecimal latitude;
    
    @Field
    private BigDecimal longitude;

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }    

    @Override
    public String toString() {
        return "cz.muni.fi.pv243.mymaps.entities.PointEntity[ id=" + id + " ]";
    }
    
}
