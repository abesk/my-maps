/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.logging;


import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.jboss.logging.Logger;


/**
 *
 * @author Jiri Holusa
 */
@Logged
@Interceptor
public class LoggingInterceptor {
    
    @Inject
    private Logger log;
    
    @AroundInvoke
    public Object logMethodExecuted(InvocationContext ctx) throws Exception {        
        log.info("Method " + ctx.getMethod().getDeclaringClass().getSimpleName() 
                   + "." + ctx.getMethod().getName() + " executed.");
        
        return ctx.proceed();
    }
    
}
