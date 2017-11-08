/**
 * $Id: SecurityFacade.java 124745 2013-04-18 10:56:59Z jose.galo $
 */
package es.expensecalculator.web.security;

import java.util.List;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * Fachada de acceso al cliente de seguridad.
 *
 * @author jose
 */
public interface SecurityFacade {

    /**
     * Si el usuario está autenticado.
     *
     * @return true, if is authenticated
     */
    boolean isAuthenticated();

    /**
     * Devuelve el nombre del usuario (login).
     *
     * @return the user name
     */
    String getUserName();

    /**
     * Devuelve el nombre completo del usuario logado.
     *
     * @return the name
     */
    String getName();

    /**
     * Devuelve la lista de roles del usuario.
     *
     * @return the roles
     */
    List<String> getRoles();

    /**
     * Devuelve la lista de roles del usuario para una empresa.
     *
     * @param companyCode the company code
     * @return the roles
     */
    List<String> getRoles(String companyCode);

    /**
     * Comprueba si el usuario logado tiene determinado rol.
     *
     * @param role the role
     * @return true, if successful
     */
    boolean hasRole(String role);

    /**
     * Comprueba si el usuario logado tiene alguno de los roles.
     *
     * @param roles the roles
     * @return true, if successful
     */
    boolean hasAnyRole(String[] roles);

    /**
     * Devuelve las empresas del usuario, en formato NNNN. Si el usuario
     * es global y no pertenece a ninguna empresa, devuelve un conjunto vacío.
     *
     * @return the companies
     */
    Set<String> getCompanies();

    /**
     * Comprueba si un usuario está autorizado para realizar una acción (tiene permiso para acceder).
     *
     * @param permission the permission
     * @return true, if is authorized
     */
    boolean isAuthorized(String permission);

    /**
     * Comprueba si un usuario de una empresa está autorizado para realizar
     * una acción (tiene permiso para acceder).
     *
     * @param permission the permission
     * @param companyCode the company code
     * @return true, if is authorized
     */
    boolean isAuthorized(String permission, String companyCode);

}
