
package cz.muni.fi.pv243.mymaps.webapp.session;

import java.io.IOException;
import org.infinispan.api.BasicCacheContainer;

/**
 *  
 * @author Jiří Holuša
 */
public interface CacheContainerProvider {

    public BasicCacheContainer getCacheContainer() throws IOException;
    
}
