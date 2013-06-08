/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.security;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import org.jboss.seam.security.annotations.SecurityBindingType;

/**
 *
 * @author andrej
 */
@SecurityBindingType
@Target(
{TYPE, METHOD, PARAMETER, FIELD})
@Retention(RUNTIME)
public @interface User {
    
}
