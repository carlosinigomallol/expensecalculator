package es.expensecalculator.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import es.expensecalculator.model.Usuario;
import es.expensecalculator.web.param.UsuarioParam;

/**
 * Implementación del DAO para la tabla Usuario
 */
public class UsuarioDAO implements IUsuarioDAO {

	/** Logger */
	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(UsuarioDAO.class);

	/** Session de Hibernate */
	private SessionFactory sessionFactory;

	/**
	 * @return SessionFactory - Hibernate Session Factory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the new session factory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Inserta una usuario
	 * 
	 * @param Usuario
	 *            Usuario
	 */
	public void addUsuario(Usuario Usuario) {
		getSessionFactory().getCurrentSession().save(Usuario);
	}

	/**
	 * Añade la claúsula WHERE a la consulta base
	 * 
	 * @param queryString
	 *            Consulta base
	 * @param filter
	 *            Contenedor con los criterios de búsqueda
	 * @return Consulta con el WHERE añadido
	 */
	private String addRestrictions(String queryString, UsuarioParam filter) {
		List<String> restrictions = new ArrayList<String>();
		String ret = null;
		int conditions = 0;
		
		if (filter.getLogin() != null && filter.getLogin().trim().length() > 0) {
			restrictions.add(" e.login = '" + filter.getLogin() + "' ");
		}
		if (filter.getPassword() != null && filter.getPassword().trim().length() > 0) {
			restrictions.add(" e.password  = '" + filter.getPassword() + "' ");
		}

		if (restrictions.size() > 0) {
			ret = queryString.concat(" WHERE ");

			for (String aux : restrictions) {

				ret = ret.concat(aux);

				if (++conditions < restrictions.size()) {
					ret = ret.concat(" AND ");
				}

			}
		} else {
			ret = queryString;
		}

		//ret = ret.concat(" ORDER BY e.descripcion ASC");

		return ret;
	}

	/**
	 * Devuelve una Usuario por su clave
	 * 
	 * @param id
	 *            Clave primaria
	 * @return Usuario
	 */
	public Usuario getUsuarioById(long id) {
		List<Usuario> list = getSessionFactory().getCurrentSession().createQuery("from Usuario where id=?")
				.setParameter(0, id).list();
		Usuario usuario = null;
		for (Usuario e : list) {
			usuario = e;
		}
		return usuario;
	}

	/**
	 * Devuelve una lista de usuarioes
	 * 
	 * @param filter
	 *            Contenedor con los criterios de búsqueda
	 * @return Lista de usuarioes
	 */
	public List<Usuario> getUsuarioListWithRestrictions(UsuarioParam filter) {
		String queryString = " FROM Usuario AS e ";
		queryString = addRestrictions(queryString, filter);
		List<Usuario> usuarios = getSessionFactory().getCurrentSession().createQuery(queryString)
				.setMaxResults(49000000).list();
		return usuarios;
	}

	/**
	 * Actualiza una usuario
	 * 
	 * @param usuario
	 *            Usuario
	 */
	public void updateUsuario(Usuario usuario) {
		getSessionFactory().getCurrentSession().update(usuario);
	}

	/**
	 * Borra una usuario
	 * 
	 * @param usuario
	 *            Usuario
	 */
	public void deleteUsuario(Usuario usuario) {
		getSessionFactory().getCurrentSession().delete(usuario);
	}

	
	
}
