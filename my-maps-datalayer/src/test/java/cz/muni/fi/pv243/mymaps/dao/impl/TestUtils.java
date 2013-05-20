package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.caches.DefaultCacheContainerProvider;
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
                .addAsWebInfResource("META-INF/beans-test.xml", "beans.xml");
    }
}
