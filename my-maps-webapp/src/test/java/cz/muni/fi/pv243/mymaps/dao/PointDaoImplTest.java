
package cz.muni.fi.pv243.mymaps.dao;

import static org.junit.Assert.assertEquals;
import cz.muni.fi.pv243.mymaps.dao.impl.PointDaoImpl;
import cz.muni.fi.pv243.mymaps.entities.PointEntity;
import cz.muni.fi.pv243.mymaps.webapp.session.CacheContainerProvider;
import cz.muni.fi.pv243.mymaps.webapp.session.JBossASCacheContainerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import org.infinispan.api.BasicCache;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Jiří Holuša
 */
@RunWith(Arquillian.class)
public class PointDaoImplTest {
    
    @Deployment
    public static JavaArchive createDeployment() 
    {
       return ShrinkWrap.create(JavaArchive.class)
          .addClasses(PointDao.class, PointDaoImpl.class, CacheContainerProvider.class,
                      JBossASCacheContainerProvider.class, UserTransaction.class)
          .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    private CacheContainerProvider provider;
    
    private PointDaoImpl manager;    
            
    @Before    
    public void setUp() throws IOException {
        BasicCache<Long, PointEntity> cache = provider.getCacheContainer().getCache("testCache");
        manager = new PointDaoImpl(cache);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testBasic() {
        /*PointEntity entity = new PointEntity();
        entity.setLatitude(BigDecimal.ONE);
        entity.setLongitude(BigDecimal.ZERO);
        manager.create(entity);
        
        PointEntity retrieved = manager.getById(entity.getId());
        assertEquals(retrieved.getLatitude(), BigDecimal.ONE);
        assertEquals(retrieved.getLongitude(), BigDecimal.ZERO);*/        
    }
}