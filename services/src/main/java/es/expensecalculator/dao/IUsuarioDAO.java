package es.expensecalculator.dao;

import java.util.Date;
import java.util.List;

import es.expensecalculator.model.Usuario;
import es.expensecalculator.web.param.UsuarioParam;

/**
 * Interfaz de acceso a los datos de las usuarioes
 */
public interface IUsuarioDAO {

	/**
	 * Devuelve una usuario por su clave
	 * 
	 * @param id
	 *            Clave primaria
	 * @return Usuario
	 */
	Usuario getUsuarioById(long id);

	/**
	 * Devuelve una lista de usuarioes
	 * 
	 * @param filter
	 *            Contenedor con los criterios de búsqueda
	 * @return Lista de usuarioes
	 */
	List<Usuario> getUsuarioListWithRestrictions(UsuarioParam filter);

	/**
	 * Inserta una usuario
	 * 
	 * @param usuario
	 *            Usuario
	 */
	void addUsuario(Usuario usuario);

	/**
	 * Actualiza una usuario
	 * 
	 * @param usuario
	 *            Usuario
	 */
	void updateUsuario(Usuario usuario);

	/**
	 * Borra una usuario
	 * 
	 * @param usuario
	 *            Usuario
	 */
	void deleteUsuario(Usuario usuario);

}
