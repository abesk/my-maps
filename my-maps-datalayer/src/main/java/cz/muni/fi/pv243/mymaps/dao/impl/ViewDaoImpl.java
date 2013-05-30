package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.dao.ViewDao;
import cz.muni.fi.pv243.mymaps.entities.ViewEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Jiri Holusa
 */
public class ViewDaoImpl extends GenericDaoImpl<ViewEntity> implements ViewDao {

    private static final String CACHE_NAME = "View";

    @Override
    protected String cacheName() {
        return CACHE_NAME;
    }

    @Override
    public List<ViewEntity> findViewsByName(String name) {
        if (cache == null) {   
            String msg = "Internal error: cache is null.";
            log.error(msg);
            throw new IllegalStateException(msg);
        }
        
        if(name == null){
            String msg = "Name cannot be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        
        if(name.length() == 0){
            String msg = "Name cannot be empty.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        } 
        
        List<ViewEntity> result = new ArrayList<>();
        for(ViewEntity view: cache.values()){
            if(view.getName().equals(name)){
                result.add(view);
            }
        }
        
        if(result.isEmpty()){
            return null;
        }
        
        return result;
    }    
    
}
