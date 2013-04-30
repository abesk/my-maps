
package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.dao.GenericDao;
import cz.muni.fi.pv243.mymaps.dao.PointDao;
import cz.muni.fi.pv243.mymaps.entities.PointEntity;
import java.io.IOException;
import org.infinispan.api.BasicCache;

/**
 *
 * @author Jiri Holusa
 */
public class PointDaoImpl extends GenericDao<PointEntity> implements PointDao<PointEntity>{
    
    public PointDaoImpl() throws IOException{
        cache = provider.getCacheContainer().getCache();
    }
    
    public PointDaoImpl(BasicCache<Long, PointEntity> cache){
        this.cache = cache;
    }
    
}
