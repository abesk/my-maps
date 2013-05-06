
package cz.muni.fi.pv243.mymaps.dao;

import cz.muni.fi.pv243.mymaps.dao.impl.PointOfInterestDaoImpl;
import cz.muni.fi.pv243.mymaps.caches.DefaultCacheContainerProvider;
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
public class PointOfInterestDaoImplTest {
    
    @Deployment
    public static WebArchive createDeployment(){                
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(PointOfInterestDaoImpl.class,                                                        
                            DefaultCacheContainerProvider.class                            
                            )
                 .addAsWebInfResource(new StringAsset("<beans><alternatives>" +
                                                        "<class>cz.muni.fi.pv243.mymaps.caches.DefaultCacheContainerProvider</class>\n" +
                                                        "</alternatives></beans>"), 
                                                      "beans.xml");                
    }    
    
    @Inject
    private PointOfInterestDaoImpl manager;    
        

    @Test
    public void testBasicTest() {
//        PointOfInterestEntity entity = new PointOfInterestEntity();
//        entity.setLatitude(BigDecimal.ONE);
//        entity.setLongitude(BigDecimal.ZERO);
//        manager.create(entity);
//        
//        PointOfInterestEntity retrieved = manager.getById(entity.getId());
//        assertEquals(retrieved.getLatitude(), BigDecimal.ONE);
//        assertEquals(retrieved.getLongitude(), BigDecimal.ZERO);           
    }
}