package es.expensecalculator.web.param;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Contenedor de los datos para los parámetros del formulario
 */
public class UsuarioParam extends ParamCommun implements EntityParam<UsuarioParam>, Serializable {

	/**
	 * Generador por el IDE
	 */
	private static final long serialVersionUID = 5107792745323596286L;

	private String login;
	private String password;
	
	/**
	 * Constructor por defecto
	 */
	public UsuarioParam() {
		super();
	}

	
	
	
	public String getLogin() {
		return login;
	}




	public void setLogin(String login) {
		this.login = login;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	@Override
	public UsuarioParam getEntityParam() {
		return this;
	}

}
