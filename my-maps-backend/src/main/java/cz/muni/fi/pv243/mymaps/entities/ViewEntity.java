/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.entities;

import java.io.Serializable;
import org.hibernate.search.annotations.*;

/**
 *
 * @author Jiri Holusa
 */
public class ViewEntity extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 19567189L;
    
        

    @Override
    public String toString() {
        return "cz.muni.fi.pv243.mymaps.entities.ViewEntity[ id=" + id + " ]";
    }
    
}
