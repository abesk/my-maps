/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.jsf;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


/**
 *
 * @author Jiri Holusa
 */
@ManagedBean
@Named(value = "arbitaryNameUsedInHTML")
@RequestScoped
public class IndexBean {
    
    private String beanName = "IndexBeanName";
    
    public String getBeanName(){
        return beanName;
    }
    
}
