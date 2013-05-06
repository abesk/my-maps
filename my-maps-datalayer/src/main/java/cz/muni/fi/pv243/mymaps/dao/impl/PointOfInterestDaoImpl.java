/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.dao.GenericDao;
import cz.muni.fi.pv243.mymaps.dao.PointOfInterestDao;
import cz.muni.fi.pv243.mymaps.entities.PointOfInterestEntity;
import javax.annotation.PostConstruct;


public class PointOfInterestDaoImpl extends GenericDao<PointOfInterestEntity> implements PointOfInterestDao {
    private static final String CACHE_NAME = "User";
    
    @Override
    protected String cacheName() {
        return CACHE_NAME;
    }

}
