package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.dao.MyMapDao;
import cz.muni.fi.pv243.mymaps.entities.MyMapEntity;
import cz.muni.fi.pv243.mymaps.entities.UserEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jiri Holusa
 */
public class MyMapDaoImpl extends GenericDaoImpl<MyMapEntity> implements MyMapDao {

    private static final String CACHE_NAME = "MyMap";

    @Override
    protected String cacheName() {
        return CACHE_NAME;
    }

    @Override
    public List<MyMapEntity> findMapsByName(String name) {
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
        
        List<MyMapEntity> result = new ArrayList<>();
        for(MyMapEntity map: cache.values()){
            if(map.getName().equals(name)){
                result.add(map);
            }
        }
        
        if(result.isEmpty()){
            return null;
        }
        
        return result;
    }

    @Override
    public List<MyMapEntity> findMapsByCreationDate(Date exactDate) {
        if (cache == null) {   
            String msg = "Internal error: cache is null.";
            log.error(msg);
            throw new IllegalStateException(msg);
        }
        
        if(exactDate == null){
            String msg = "Date cannot be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }        
        
        List<MyMapEntity> result = new ArrayList<>();
        for(MyMapEntity map: cache.values()){
            if(map.getCreationDate().equals(exactDate)){
                result.add(map);
            }
        }
        
        if(result.isEmpty()){
            return null;
        }
        
        return result;
    }

    @Override
    public List<MyMapEntity> findMapsByCreationDate(Date from, Date to) {
        if (cache == null) {   
            String msg = "Internal error: cache is null.";
            log.error(msg);
            throw new IllegalStateException(msg);
        }
        
        if(from == null){
            String msg = "Date (from) cannot be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        
        if(to == null){
            String msg = "Date (to) cannot be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        } 
        
        List<MyMapEntity> result = new ArrayList<>();
        for(MyMapEntity map: cache.values()){
            if(map.getCreationDate().after(from) && map.getCreationDate().before(to)){
                result.add(map);
            }
        }
        
        if(result.isEmpty()){
            return null;
        }
        
        return result;
    }

    @Override
    public List<MyMapEntity> findMapsByCreator(UserEntity creator) {
        if (cache == null) {   
            String msg = "Internal error: cache is null.";
            log.error(msg);
            throw new IllegalStateException(msg);
        }
        
        if(creator == null){
            String msg = "Creator cannot be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        
        if(creator.getId() == null){
            String msg = "Creator's ID cannot be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        } 
        
        List<MyMapEntity> result = new ArrayList<>();
        for(MyMapEntity map: cache.values()){
            if(map.getCreator().equals(creator)){
                result.add(map);
            }
        }
        
        if(result.isEmpty()){
            return null;
        }
        
        return result;
    }
}
