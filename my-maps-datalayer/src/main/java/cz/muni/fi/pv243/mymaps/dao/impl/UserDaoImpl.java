/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.dao.GenericDao;
import cz.muni.fi.pv243.mymaps.dao.UserDao;
import cz.muni.fi.pv243.mymaps.entities.UserEntity;


public class UserDaoImpl extends GenericDao<UserEntity> implements UserDao {
    private static final String CACHE_NAME = "MapPermission";
    
    @Override
    protected String cacheName() {
        return CACHE_NAME;
    }
    
}
