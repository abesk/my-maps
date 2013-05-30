package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.dao.UserDao;
import cz.muni.fi.pv243.mymaps.entities.UserEntity;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserEntity> findUsersByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    

    @Override
    public List<UserEntity> findUsersByRole(String role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
