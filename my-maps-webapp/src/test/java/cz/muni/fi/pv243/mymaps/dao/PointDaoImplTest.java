
package cz.muni.fi.pv243.mymaps.dao;

import cz.muni.fi.pv243.mymaps.dao.impl.PointDaoImpl;
import cz.muni.fi.pv243.mymaps.entities.AbstractEntity;
import cz.muni.fi.pv243.mymaps.entities.PointEntity;
import cz.muni.fi.pv243.mymaps.webapp.session.CacheContainerProvider;
import cz.muni.fi.pv243.mymaps.webapp.session.JBossASCacheContainerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import org.infinispan.api.BasicCacheContainer;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Jiri Holusa
 */
@RunWith(Arquillian.class)
public class PointDaoImplTest {
    
    @Deployment
    public static WebArchive createDeployment(){        
        JavaArchive[] lib = Maven.resolver()
                .resolve("org.infinispan:infinispan-core:5.2.5.Final")
                .withTransitivity()
                .as(JavaArchive.class);        
        
        WebArchive archive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(PointDao.class, 
                            PointDaoImpl.class,
                            GenericDao.class,                           
                            CacheContainerProvider.class,
                            BasicCacheContainer.class,
                            JBossASCacheContainerProvider.class,
                            AbstractEntity.class,
                            PointEntity.class)
                .addAsLibraries(lib)
                .addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));        
       
        return archive;
    }
    
    @Inject
    private CacheContainerProvider provider;
    
    @Inject
    private UserTransaction utx;
    
    @Inject
    private PointDaoImpl manager;    
            
    @Before    
    public void setUp() throws IOException {  
        if(provider == null) throw new NullPointerException("hehee"); 
       // BasicCache<Long, PointEntity> cache = provider.getCacheContainer().getCache("testCache");
        //manager = new PointDaoImpl(cache);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testBasic() {
        PointEntity entity = new PointEntity();
        entity.setLatitude(BigDecimal.ONE);
        entity.setLongitude(BigDecimal.ZERO);
        manager.create(entity);
        
        //PointEntity retrieved = manager.getById(entity.getId());
       // assertEquals(retrieved.getLatitude(), BigDecimal.ONE);
       // assertEquals(retrieved.getLongitude(), BigDecimal.ZERO);       
    }
}