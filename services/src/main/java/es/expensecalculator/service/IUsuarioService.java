package es.expensecalculator.service;

import java.util.Date;
import java.util.List;

import es.expensecalculator.model.Usuario;
import es.expensecalculator.web.param.UsuarioParam;



/**
 * Interfaz de los servicios de persistencia de las entidades
 */
public interface IUsuarioService {

	/**
	 * Devuelve una entidad por su clave primaria
	 * 
	 * @param id
	 *            Clave primaria
	 * @return Usuario
	 */
	Usuario getUsuarioById(long id);

	/**
	 * Devuelve una lista de entiadades
	 * 
	 * @param filter
	 *            Contenedor de datos de los criterios de búsqueda
	 * @return Lista de entidades
	 */
	List<Usuario> getUsuarioListWithRestrictions(UsuarioParam filter);

	/**
	 * Inserta una entidad
	 * 
	 * @param entidad
	 *            Usuario
	 */
	void addUsuario(Usuario usuario);

	/**
	 * Actualiza una entidad
	 * 
	 * @param entidad
	 *            Usuario
	 */
	void actualizarUsuario(Usuario usuario);

	/**
	 * Borra una entidad
	 * 
	 * @param entidad
	 *            Usuario
	 */
	void borrarUsuario(Usuario usuario);

}
