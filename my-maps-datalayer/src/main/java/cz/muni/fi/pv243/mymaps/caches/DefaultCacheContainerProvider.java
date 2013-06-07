package cz.muni.fi.pv243.mymaps.caches;

import cz.muni.fi.pv243.mymaps.logging.Logged;
import java.io.IOException;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import org.infinispan.api.BasicCacheContainer;
import org.infinispan.manager.DefaultCacheManager;
import org.jboss.logging.Logger;

/**
 *
 * @author Jiri Holusa
 */
@ApplicationScoped
@Alternative
public class DefaultCacheContainerProvider implements CacheContainerProvider {

    private BasicCacheContainer manager;

    @Inject
    private Logger log;
    
    @Override
    @Logged
    public BasicCacheContainer getCacheContainer() {        
        if (manager == null) {
            try {                
                manager = new DefaultCacheManager("infinispan.xml");
            } catch (IOException ex) {
                String msg = "Internal error: Cache manager couldn't be initialized.";
                log.error(msg);
                throw new IllegalStateException(msg, ex);
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
