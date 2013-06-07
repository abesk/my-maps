package cz.muni.fi.pv243.mymaps.caches;

import cz.muni.fi.pv243.mymaps.logging.Logged;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import org.infinispan.api.BasicCacheContainer;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.transaction.LockingMode;
import org.infinispan.transaction.TransactionMode;
import org.infinispan.transaction.lookup.GenericTransactionManagerLookup;
import org.infinispan.util.concurrent.IsolationLevel;

/**
 * @author Jiri Holusa
 */
@ApplicationScoped
@Alternative
public class FileCacheContainerProvider implements CacheContainerProvider {

    private BasicCacheContainer manager;
    
    @Override
    @Logged
    public BasicCacheContainer getCacheContainer() {        
        if (manager == null) {
            GlobalConfiguration glob = new GlobalConfigurationBuilder()
               .globalJmxStatistics().enable()               
               .build();

            Configuration defaultConfig = new ConfigurationBuilder()
                  .transaction().transactionMode(TransactionMode.TRANSACTIONAL)
                  .build(); //default config

            Configuration projectCacheConfig = new ConfigurationBuilder()
                  .jmxStatistics()
                     .enable()
                  .clustering()
                     .cacheMode(CacheMode.REPL_SYNC)
                  .transaction()
                     .transactionMode(TransactionMode.TRANSACTIONAL)
                     .autoCommit(false)
                     .lockingMode(LockingMode.OPTIMISTIC)
                  .transactionManagerLookup(new GenericTransactionManagerLookup())
                  .locking()
                     .isolationLevel(IsolationLevel.REPEATABLE_READ)                  
                  .loaders()
                     .passivation(true)
                     .addFileCacheStore()
                     .purgeOnStartup(true)
                  .indexing()
                     .enable()
                     .addProperty("default.directory_provider", "ram")
                  .build();

            manager = new DefaultCacheManager(glob, defaultConfig);
            ((DefaultCacheManager) manager).defineConfiguration("projectCache", projectCacheConfig);
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
