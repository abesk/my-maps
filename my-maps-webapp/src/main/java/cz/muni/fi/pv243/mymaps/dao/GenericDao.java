package cz.muni.fi.pv243.mymaps.dao;

import cz.muni.fi.pv243.mymaps.entities.AbstractEntity;
import cz.muni.fi.pv243.mymaps.webapp.session.CacheContainerProvider;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import org.infinispan.api.BasicCache;

@Model
@Dependent
public class GenericDao<T extends AbstractEntity> implements PointDao<T> {
    protected Class<T> entityClass;
    
    @Inject
    protected CacheContainerProvider provider;
    
    @Inject
    protected UserTransaction utx;
    
    protected BasicCache<Long, T> cache;

    public GenericDao() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        entityClass = (Class<T>) type.getActualTypeArguments()[0];
    }

    @Override
    public T create(T entity) {
        if(cache == null){
            //TODO: write exception text
            throw new IllegalStateException();
        }        
        
        try {               
            utx.begin();  
            Long id = Collections.max(cache.keySet());
            cache.put(id, entity);
            entity.setId(id);
            utx.commit();
        } catch (Exception e) {
            if (utx != null) {
                try {
                    utx.rollback();
                } catch (Exception e1) {
                    //TODO: handle exception
                }
            }
        }
        
        return entity;
    }

    @Override
    public void delete(T entity) {
        if(cache == null){
            //TODO: write exception text
            throw new IllegalStateException();
        }        
        
        try {
            utx.begin();            
            cache.remove(entity.getId());
            utx.commit();
        } catch (Exception e) {
            if (utx != null) {
                try {
                    utx.rollback();
                } catch (Exception e1) {
                    //TODO: handle exception
                }
            }
        }
    }

    @Override
    public T update(T entity) {
        if(cache == null){
            //TODO: write exception text
            throw new IllegalStateException();
        }        
        
        try {
            utx.begin();            
            cache.put(entity.getId(), entity);
            utx.commit();
        } catch (Exception e) {
            if (utx != null) {
                try {
                    utx.rollback();
                } catch (Exception e1) {
                    //TODO: handle exception
                }
            }
        }
        
        return entity;
    }

    @Override
    public T getById(Long id) {
        if(cache == null){
            //TODO: write exception text
            throw new IllegalStateException();
        } 
       
        return cache.get(id);
    }

}
