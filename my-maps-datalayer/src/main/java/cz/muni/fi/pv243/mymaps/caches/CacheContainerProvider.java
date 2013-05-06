
package cz.muni.fi.pv243.mymaps.caches;

import org.infinispan.api.BasicCacheContainer;

/**
 *  
 * @author Jiri Holusa
 */
public interface CacheContainerProvider {
    
    public BasicCacheContainer getCacheContainer();
    
}
