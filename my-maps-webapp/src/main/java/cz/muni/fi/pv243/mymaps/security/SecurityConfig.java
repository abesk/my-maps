/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.security;

import org.jboss.seam.faces.event.PhaseIdType;
import org.jboss.seam.faces.security.AccessDeniedView;
import org.jboss.seam.faces.security.LoginView;
import org.jboss.seam.faces.security.RestrictAtPhase;
import org.jboss.seam.faces.view.config.ViewConfig;
import org.jboss.seam.faces.view.config.ViewPattern;
import org.jboss.seam.security.annotations.LoggedIn;

/**
 *
 * @author andrej
 */
@ViewConfig
public interface SecurityConfig {

    static enum Pages1 {

        @ViewPattern("/index.xhtml")
        INDEX,
        @ViewPattern("/maps.xhtml")
        MAPS,
        @ViewPattern("/viewMap.xhtml")
        VIEW,
        @ViewPattern("/createView.xhtml")
        CREATEVIEW,
        @ViewPattern("/users.xhtml")
        @LoginView("/login.xhtml")
        @AccessDeniedView("/login.xhtml")
        @LoggedIn
        @Admin
        @RestrictAtPhase({PhaseIdType.RESTORE_VIEW,
            PhaseIdType.INVOKE_APPLICATION})
        USERS,
        @ViewPattern("/editUser.xhtml")
        @LoginView("/login.xhtml")
        @AccessDeniedView("/login.xhtml")
        @LoggedIn
        @Admin
        @RestrictAtPhase({PhaseIdType.RESTORE_VIEW,
            PhaseIdType.INVOKE_APPLICATION})
        EDIT_USER,
        @ViewPattern("/profile.xhtml")
        @LoginView("/login.xhtml")
        @AccessDeniedView("/login.xhtml")
        @LoggedIn
        @RestrictAtPhase({PhaseIdType.RESTORE_VIEW,
            PhaseIdType.INVOKE_APPLICATION})
        USER_PROFILE,
        @ViewPattern("/editProfile.xhtml")
        @LoginView("/login.xhtml")
        @AccessDeniedView("/login.xhtml")
        @LoggedIn
        @RestrictAtPhase({PhaseIdType.RESTORE_VIEW,
            PhaseIdType.INVOKE_APPLICATION})
        EDIT_PROFILE,
        @ViewPattern("/createMap.xhtml")
        @LoginView("/login.xhtml")
        @AccessDeniedView("/login.xhtml")
        @LoggedIn
        @RestrictAtPhase({PhaseIdType.RESTORE_VIEW,
            PhaseIdType.INVOKE_APPLICATION})
        CREATE_MAP
    }
}