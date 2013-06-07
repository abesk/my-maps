/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.logging;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import org.jboss.logging.Logger;

/**
 *
 * @author Jiri Holusa
 */
public class LoggingFactory {
    
    @Produces
    public Logger createLogger(InjectionPoint ip){          
        return Logger.getLogger(ip.getMember().getDeclaringClass());
    }
    
}
