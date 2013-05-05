/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.webapp.session;

import java.io.IOException;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import org.infinispan.api.BasicCacheContainer;
import org.infinispan.manager.DefaultCacheManager;

/**
 *
 * @author Jiri Holusa
 */
@ApplicationScoped
public class DefaultCacheContainerProvider implements CacheContainerProvider {
    
    private BasicCacheContainer manager;

   @Override
   public BasicCacheContainer getCacheContainer(){
     if (manager == null) {         
         try {  
             //TODO: change to file cache manager
             manager = new DefaultCacheManager("infinispan.xml");
         } catch (IOException ex) {
             //TODO: do something
         }
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
