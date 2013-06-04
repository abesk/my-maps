package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.dao.UserDao;
import cz.muni.fi.pv243.mymaps.entities.UserEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Jiri Holusa
 */
public class UserDaoImpl extends GenericDaoImpl<UserEntity> implements UserDao {

    private static final String CACHE_NAME = "User";

    @Override
    protected String cacheName() {
        return CACHE_NAME;
    }

    @Override
    public UserEntity getUserByLogin(String login) {
        if (cache == null) {   
            String msg = "Internal error: cache is null.";
            log.error(msg);
            throw new IllegalStateException(msg);
        }
        
        if(login == null){
            String msg = "Login cannot be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        
        if(login.length() == 0){
            String msg = "Login cannot be empty.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        } 
                
        for(UserEntity user: cache.values()){
            if(user.getLogin().equals(login)){
                return user;
            }
        }
                
        return null;
    }

    @Override
    public List<UserEntity> findUsersByName(String name) {
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
        
        List<UserEntity> result = new ArrayList<>();
        for(UserEntity user: cache.values()){
            if(user.getName().equals(name)){
                result.add(user);
            }
        }
        
        return result;
    }    

    @Override
    public List<UserEntity> findUsersByRole(String role) {
        if (cache == null) {   
            String msg = "Internal error: cache is null.";
            log.error(msg);
            throw new IllegalStateException(msg);
        }
        
        if(role == null){
            String msg = "Role cannot be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        
        if(role.length() == 0){
            String msg = "Role cannot be empty.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        } 
        
        List<UserEntity> result = new ArrayList<>();
        for(UserEntity user: cache.values()){
            if(user.getRole().equals(role)){
                result.add(user);
            }
        }
        
        return result;
    }
    
}
