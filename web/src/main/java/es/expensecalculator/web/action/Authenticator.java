package es.expensecalculator.web.action;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import es.expensecalculator.model.Usuario;
import es.expensecalculator.service.ICalculadoraPlanService;
import es.expensecalculator.service.IUsuarioService;
import es.expensecalculator.web.param.UsuarioParam;
import es.expensecalculator.web.security.UserSessionBean;

@ManagedBean(name = "authenticator")
@SessionScoped
public class Authenticator {

	private String username;
	private String password;

	@ManagedProperty(value = "#{UsuarioService}")
	IUsuarioService usuarioService;

	@ManagedProperty(value = "#{userSessionBean}")
	UserSessionBean userSessionBean;

	public IUsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public UserSessionBean getUserSessionBean() {
		return userSessionBean;
	}

	public void setUserSessionBean(UserSessionBean userSessionBean) {
		this.userSessionBean = userSessionBean;
	}

	public String login() {
		UsuarioParam usuarioParam = new UsuarioParam();
		usuarioParam.setLogin(this.username);
		usuarioParam.setPassword(this.password);
		List<Usuario> users = usuarioService.getUsuarioListWithRestrictions(usuarioParam);
		// Tendria que buscar en usuario
		if (!users.isEmpty()) {
			Usuario usuario = users.get(0);
			userSessionBean.setUsuario(usuario);
			return "/pages/expensecalculator/CalculadoraFuturoGestion.xhtml?faces-redirect=true";
		} else {
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
					"El usuario no está dado de alta para el servicio.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
	}

	public String redirectToRegisterController() {
		return "/pages/expensecalculator/Register.xhtml?faces-redirect=true";
	}

	public String logout() {
		final FacesContext context = FacesContext.getCurrentInstance();
		final HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpSession session = request.getSession();
		session.invalidate();
		return "/login.xhtml?faces-redirect=true";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
