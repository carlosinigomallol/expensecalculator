package es.expensecalculator.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import es.expensecalculator.model.Usuario;
import es.expensecalculator.service.ICalculadoraPlanService;
import es.expensecalculator.service.IUsuarioService;
import es.expensecalculator.web.param.UsuarioParam;
import es.expensecalculator.web.security.UserSessionBean;

@ManagedBean(name = "gestionCuenta")
@SessionScoped
public class GestionCuenta {

	
	
	private String username;
	private String password;
	private String repeatpassword;

	private static final String PATRON = "(?=^.{8,15}$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&amp;*()_+}{&quot;:;'?/&gt;.&lt;,])(?!.*\\s).*$";
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
            "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
            "(\\.[A-Za-z]{2,})$";

	public List<String> getComentarios() {

		List<String> comentarios = new ArrayList<String>();

		comentarios.add("Minimo 8 caracteres");
		comentarios.add("Maximo 15");
		comentarios.add("Al menos una letra mayúscula");
		comentarios.add("Al menos una letra minúscula");
		comentarios.add("Al menos un dígito");
		comentarios.add("No espacios en blanco");
		comentarios.add("Al menos 1 caracter especial");
		return comentarios;
	}

	public String crearCuenta() {
		if (!username.matches(EMAIL_PATTERN)) {
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", "El nombre de usuario debe ser una cuenta de correo electrónico");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		
		if (!password.matches(PATRON)) {
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", "No cumple con el patrón de password!");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		if (!password.equals(repeatpassword)) {
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", "El password no es correcto");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		try {
			UsuarioParam usuarioParam = new UsuarioParam();
			usuarioParam.setLogin(username);
			List<Usuario> usuarios = usuarioService.getUsuarioListWithRestrictions(usuarioParam);
			if (usuarios != null && !usuarios.isEmpty()) {
				FacesMessage message = null;
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "El nombre de usuario ya existe. Introduzca uno nuevo");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}

			Usuario usuario = new Usuario();
			usuario.setName(username);
			usuario.setLogin(username);
			usuario.setPassword(password);
			usuarioService.addUsuario(usuario);
			RequestContext context = RequestContext.getCurrentInstance();
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario", "Usuario creado");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return login();
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", "Error al crear la cuenta");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}

	public String getRepeatpassword() {
		return repeatpassword;
	}

	public void setRepeatpassword(String repeatpassword) {
		this.repeatpassword = repeatpassword;
	}

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
		} else
			return null;
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
