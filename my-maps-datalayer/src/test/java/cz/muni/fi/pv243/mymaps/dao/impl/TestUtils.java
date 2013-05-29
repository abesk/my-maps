package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.caches.DefaultCacheContainerProvider;
import cz.muni.fi.pv243.mymaps.logging.LoggingFactory;
import cz.muni.fi.pv243.mymaps.logging.LoggingInterceptor;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 *
 * @author Jan Bliznak
 */
public class TestUtils {

    public static WebArchive createDeploymentArchive() {

        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(DefaultCacheContainerProvider.class)
                .addClass(LoggingFactory.class)
                .addClass(LoggingInterceptor.class)
                .addAsWebInfResource("META-INF/beans-test.xml", "beans.xml");
    }
}
