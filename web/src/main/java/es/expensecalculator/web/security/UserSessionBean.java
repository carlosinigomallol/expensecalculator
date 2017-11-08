/**
 * $Id: UserSessionBean.java 140230 2014-07-09 10:52:57Z jose.galo $
 */
package es.expensecalculator.web.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.expensecalculator.model.Usuario;

// TODO: Auto-generated Javadoc
/**
 * Almacena en la sesion datos relacionados con la session. Por ejemplo, los
 * filtros de empresa o entidad
 * 
 * @author jose.galo
 */
@ManagedBean(name = "userSessionBean")
@SessionScoped
public class UserSessionBean implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserSessionBean.class);
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7549940214407669051L;

    /** Empresa a la que pertenece el usuario, recogida desde el LDAP. */
    private List<String> roles = new ArrayList<String>();
    private Boolean activos;
    /** The companies. */
    private List<String> companies = new ArrayList<String>();

    private String codigoEntidadLDap;
    
    private Usuario usuario;

    private List<SelectItem> paises = new ArrayList<SelectItem>();

    private List<SelectItem> nacionalidad = new ArrayList<SelectItem>();

    public String getCodigoEntidadLDap() {
        return codigoEntidadLDap;
    }

    public void setCodigoEntidadLDap(String codigoEntidadLDap) {
        this.codigoEntidadLDap = codigoEntidadLDap;
    }

    public List<String> getCompanies() {
        return companies;
    }

    /**
     * Inits the.
     */
    @PostConstruct
    public void init() {
        getInformacionRoles();
    }

    public Boolean getActivos() {
        return activos;
    }

    public void setActivos(Boolean activos) {
        this.activos = activos;
    }

    public List<SelectItem> getPaises() {
        return paises;
    }

    public void setPaises(List<SelectItem> paises) {
        this.paises = paises;
    }

    public List<SelectItem> getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(List<SelectItem> nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * Devueve el login de usuario.
     * 
     * @return the user name
     */
    public String getUserName() {
        return SecurityClientFacade.getInstance().getUserName();
    }

    /**
     * Devueve el nombre de usuario (Nombre y apellidos).
     * 
     * @return the name
     */
    public String getName() {
        return SecurityClientFacade.getInstance().getName();
    }

    /**
     * Gets the informacion roles.
     * 
     * @return the informacion roles
     */
    public List<String> getInformacionRoles() {
        //Roles puede tener varios roles (operador y supervisor. Se encargara el modulo de supervision de ejercer el control para no supervisarse a si mismo)
        roles = new ArrayList<String>();
        //Empresas relacionadas con el usuario (si hay varias habría que tocar el index.xml para que redirigiera a una pagina de seleccion de rol por empresa)
        companies = new ArrayList<String>();
        for (String company : SecurityClientFacade.getInstance().getCompanies()) {
            for (String rol : SecurityClientFacade.getInstance().getRoles(company)) {
                roles.add(rol);
            }
            companies.add(company);
        }
        return roles;
    }

    /**
     * Gets the first company.
     * 
     * @return the first company
     */
    public String getFirstCompany() {
        return companies.get(0);
    }

    /**
     * Checks if is first company system administrator.
     * 
     * @return true, if is first company system administrator
     */
    public boolean isFirstCompanySystemAdministrator() {
        return companies.get(0).equals("0094");
    }

    public boolean isRBC() {
        return companies.get(0).equals("0094");
    }

        /**
     * Checks if is operador.
     * 
     * @return true, if is operador
     */
    public boolean isOperador() {
        for (String rol : roles) {
            if (rol.equals("operador")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if is supervisor.
     * 
     * @return true, if is supervisor
     */
    public boolean isSupervisor() {

        for (String rol : roles) {
            if (rol.equals("supervisor")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if is consulta.
     * 
     * @return true, if is consulta
     */
    public boolean isConsulta() {

        for (String rol : roles) {
            if (rol.equals("consulta")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the roles.
     * 
     * @return the roles
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * Sets the roles.
     * 
     * @param roles the new roles
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     * Sets the companies.
     * 
     * @param companies the new companies
     */
    public void setCompanies(List<String> companies) {
        this.companies = companies;
    }

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}
