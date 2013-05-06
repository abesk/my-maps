
package cz.muni.fi.pv243.mymaps.dao;

import static org.junit.Assert.*;
import cz.muni.fi.pv243.mymaps.dao.impl.PointDaoImpl;
import cz.muni.fi.pv243.mymaps.entities.PointEntity;
import cz.muni.fi.pv243.mymaps.caches.DefaultCacheContainerProvider;
import java.math.BigDecimal;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
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
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(PointDaoImpl.class,                                                        
                            DefaultCacheContainerProvider.class                            
                            )
                 .addAsWebInfResource(new StringAsset("<beans><alternatives>" +
                                                        "<class>cz.muni.fi.pv243.mymaps.caches.DefaultCacheContainerProvider</class>\n" +
                                                        "</alternatives></beans>"), 
                                                      "beans.xml");                
    }    
    
    @Inject
    private PointDaoImpl manager;    
        

    @Test
    public void testBasicTest() {
        PointEntity entity = new PointEntity();
        entity.setLatitude(BigDecimal.ONE);
        entity.setLongitude(BigDecimal.ZERO);
        manager.create(entity);
        
        PointEntity retrieved = manager.getById(entity.getId());
        assertEquals(retrieved.getLatitude(), BigDecimal.ONE);
        assertEquals(retrieved.getLongitude(), BigDecimal.ZERO);           
    }
}