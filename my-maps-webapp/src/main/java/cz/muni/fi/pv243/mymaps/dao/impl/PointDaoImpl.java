
package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.dao.GenericDao;
import cz.muni.fi.pv243.mymaps.dao.PointDao;
import cz.muni.fi.pv243.mymaps.entities.PointEntity;
import cz.muni.fi.pv243.mymaps.webapp.session.CacheContainerProvider;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author Jiri Holusa
 */
public class PointDaoImpl extends GenericDao<PointEntity> implements PointDao<PointEntity>{
    
    private static final String POINT_CACHE_NAME = "pointCache";
        
    @PostConstruct
    public void init(){        
        cache = provider.getCacheContainer().getCache(POINT_CACHE_NAME);        
    }
}
