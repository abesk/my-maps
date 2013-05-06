package cz.muni.fi.pv243.mymaps.dao;

import cz.muni.fi.pv243.mymaps.entities.AbstractEntity;
import cz.muni.fi.pv243.mymaps.caches.CacheContainerProvider;
import java.util.Collections;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import org.infinispan.api.BasicCache;

@Model
@Dependent
public class GenericDao<T extends AbstractEntity>{
    
    @Inject
    protected CacheContainerProvider provider;    
    
    protected BasicCache<Long, T> cache;
    
    public T create(T entity) {        
        if(cache == null){
            //TODO: write exception text
            throw new IllegalStateException();
        }    
        
        if(entity.getId() != null){
            //TODO: write exception text
            throw new IllegalArgumentException();
        }        
                 
        Long id;
        if(!cache.keySet().isEmpty()){
            id = Collections.max(cache.keySet()) + 1;
        }
        else{
            id = 1L;
        }

        cache.put(id, entity);
        entity.setId(id);        
        
        return entity;
    }

    public void delete(T entity) {
        if(cache == null){
            //TODO: write exception text
            throw new IllegalStateException();
        }   
        
        if(entity.getId() == null){
            //TODO: write exception text
            throw new IllegalArgumentException();
        }        
                   
        cache.remove(entity.getId());        
    }

    public T update(T entity) {
        if(cache == null){
            //TODO: write exception text
            throw new IllegalStateException();
        }   
        
        if(entity.getId() == null){
            //TODO: write exception text
            throw new IllegalArgumentException();
        }        
                 
        cache.put(entity.getId(), entity);
                
        return entity;
    }

    public T getById(Long id) {
        if(cache == null){
            //TODO: write exception text
            throw new IllegalStateException();
        } 
        
        if(id == null){
            //TODO: write exception text
            throw new IllegalArgumentException();
        }
       
        return cache.get(id);
    }

}