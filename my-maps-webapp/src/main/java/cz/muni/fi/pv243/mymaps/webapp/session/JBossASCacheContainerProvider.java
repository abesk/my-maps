package cz.muni.fi.pv243.mymaps.webapp.session;


import java.io.IOException;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import org.infinispan.api.BasicCacheContainer;
import org.infinispan.manager.DefaultCacheManager;


/** 
 * @author Jiří Holuša
 */
@ApplicationScoped
public class JBossASCacheContainerProvider implements CacheContainerProvider {
   
   private BasicCacheContainer manager;

   @Override
   public BasicCacheContainer getCacheContainer() throws IOException {
      if (manager == null) {         
         manager = new DefaultCacheManager("infinispan.xml");         
         manager.start();           
      }
      
      return manager;
   }

   @PreDestroy
   public void cleanUp() {
      manager.stop();
      manager = null;
   }
}
