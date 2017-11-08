package es.expensecalculator.web.security;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.rbcdexia.security.client.SecurityClient;
import es.rbcdexia.security.client.user.SecurityUser;
import es.rbcdexia.security.common.WebKeys;

// TODO: Auto-generated Javadoc
/**
 * Facade de acceso al cliente de seguridad.
 *
 * @author jose
 */
public final class SecurityClientFacade implements SecurityFacade {

    /** The log. */
    private static final Logger LOG = LoggerFactory.getLogger(SecurityClientFacade.class);

    /**
     * Id de la aplicación en LDAP. Lo recupera del fichero security.properties
     */
    private static final String APP_ID = SecurityClient.getInstance().getApplicationId();

    /**
     * Unica instancia...
     */
    private static SecurityClientFacade instance = new SecurityClientFacade();

    /**
     * Instantiates a new security client facade.
     */
    private SecurityClientFacade() {
    }

    /**
     * Gets the single instance of SecurityClientFacade.
     *
     * @return single instance of SecurityClientFacade
     */
    public static SecurityFacade getInstance() {
        return instance;
    }

    /**
     * Recupera los datos de usuario de la sesion.
     *
     * @return the user data
     */
    private SecurityUser getUserData() {
        SecurityUser user = null;
        HttpSession session = Faces.getSession(false);
        if (session != null) {
            user = (SecurityUser) session.getAttribute(WebKeys.SECURITY_USER);
        }
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAuthenticated() {
        return getUserData() != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUserName() {
        SecurityUser user = getUserData();
        return user != null ? user.getUsername() : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        SecurityUser user = getUserData();
        return user != null ? user.getName() : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getRoles() {
        SecurityUser user = getUserData();
        List<String> roles = Collections.emptyList();
        if (user != null) {
            roles = user.getRoles(APP_ID);
        }
        return roles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getRoles(String companyCode) {
        SecurityUser user = getUserData();
        List<String> roles = Collections.emptyList();
        if (user != null) {
            roles = user.getRoles(APP_ID, companyCode);
        }
        return roles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasRole(String role) {
        SecurityUser user = getUserData();
        if (user != null) {
            return user.isUserInRole(role, APP_ID);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasAnyRole(String[] roles) {
        SecurityUser user = getUserData();
        if (user != null && roles != null) {
            for (String role : roles) {
                if (user.isUserInRole(role, APP_ID)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Devuelve las compañías a las que pertenece el usuario.
     *
     * @return the companies
     */
    public Set<String> getCompanies() {
        SecurityUser user = getUserData();
        if (user != null) {
            return user.getCompaniesAsStrings(APP_ID);
        } else {
            return Collections.emptySet();
        }
    }

    /**
     * Comprueba si un usuario está autorizado para realizar una acción (tiene permiso para acceder).
     *
     * @param permission the permission
     * @return true, if is authorized
     */
    public boolean isAuthorized(String permission) {
        boolean hasPerm = SecurityClient.getInstance().isUserAuthorized(permission);
        LOG.debug("isAuthorized({}) -> {}", permission, hasPerm);
        return hasPerm;
    }

    /**
     * Comprueba si un usuario de una empresa está autorizado para realizar
     * una acción (tiene permiso para acceder).
     *
     * @param permission the permission
     * @param companyCode the company code
     * @return true, if is authorized
     */
    public boolean isAuthorized(String permission, String companyCode) {
        boolean hasPerm = SecurityClient.getInstance().isUserAuthorized(permission, companyCode);
        LOG.debug("isAuthorized({}, {}) -> {}", new Object[] { permission, companyCode, hasPerm });
        return hasPerm;
    }

}
