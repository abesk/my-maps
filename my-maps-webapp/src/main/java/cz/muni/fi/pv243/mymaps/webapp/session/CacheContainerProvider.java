
package cz.muni.fi.pv243.mymaps.webapp.session;

import org.infinispan.api.BasicCacheContainer;

/**
 *  
 * @author Jiri Holusa
 */
public interface CacheContainerProvider {
    
    public BasicCacheContainer getCacheContainer();
    
}
