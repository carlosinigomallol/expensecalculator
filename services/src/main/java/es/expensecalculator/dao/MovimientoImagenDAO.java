package es.expensecalculator.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import es.expensecalculator.model.MovimientoImagen;
import es.expensecalculator.model.MovimientoPlanImagen;
import es.expensecalculator.model.Plan;
import es.expensecalculator.model.Usuario;
import es.expensecalculator.model.configuration.GlobalConstants;
import es.expensecalculator.web.param.MovimientoImagenParam;

/**
 * Implementación del DAO para la tabla MovimientoImagen
 */
public class MovimientoImagenDAO implements IMovimientoImagenDAO {

	/** Logger */
	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(MovimientoImagenDAO.class);

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
	 * Inserta una movimientoImagen
	 * 
	 * @param MovimientoImagen
	 *            MovimientoImagen
	 */
	public void addMovimientoImagen(MovimientoImagen MovimientoImagen) {
		getSessionFactory().getCurrentSession().save(MovimientoImagen);
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
	private String addRestrictions(String queryString, MovimientoImagenParam filter) {
		List<String> restrictions = new ArrayList<String>();
		String ret = null;
		int conditions = 0;
		// SimpleDateFormat simpleDateFormat = new
		// SimpleDateFormat("yyyy-MM-dd");
		// String tiFecomuString = null;

		if (filter.getDescripcion() != null && filter.getDescripcion().trim().length() > 0) {
			restrictions.add(" e.descripcion  LIKE '%" + filter.getDescripcion() + "%' ");
		}
		if (filter.getDivisa() != null && filter.getDivisa().trim().length() > 0) {
			restrictions.add(" e.divisa  LIKE '%" + filter.getDivisa() + "%' ");
		}

		if (filter.getSignoMovimiento() != null && filter.getSignoMovimiento().trim().length() > 0) {
			restrictions.add(" e.signoMovimiento  LIKE '%" + filter.getSignoMovimiento() + "%' ");
		}

		if (filter.getTipoMovimiento() != null && filter.getTipoMovimiento().trim().length() > 0) {
			restrictions.add(" e.tipoMovimiento  LIKE '%" + filter.getTipoMovimiento() + "%' ");
		}

		if (filter.getTotalMovimiento() != null) {
			restrictions.add(" e.totalMovimiento = " + filter.getTotalMovimiento() + " ");
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		if (filter.getFechaFin() != null) {
			restrictions.add(" e.fechaFin ='" + simpleDateFormat.format(filter.getFechaFin()) + "' ");
		}

		if (filter.getFechaInicio() != null) {
			restrictions.add(" e.fechaInicio ='" + simpleDateFormat.format(filter.getFechaInicio()) + "' ");
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

		ret = ret.concat(" ORDER BY e.descripcion ASC");

		return ret;
	}

	/**
	 * Devuelve una MovimientoImagen por su clave
	 * 
	 * @param id
	 *            Clave primaria
	 * @return MovimientoImagen
	 */
	public MovimientoImagen getMovimientoImagenById(long id) {
		List<MovimientoImagen> list = getSessionFactory().getCurrentSession().createQuery("from MovimientoImagen where id=?")
				.setParameter(0, id).list();
		MovimientoImagen movimientoImagen = null;
		for (MovimientoImagen e : list) {
			movimientoImagen = e;
		}
		return movimientoImagen;
	}

	/**
	 * Devuelve una lista de movimientoImagenes
	 * 
	 * @param filter
	 *            Contenedor con los criterios de búsqueda
	 * @return Lista de movimientoImagenes
	 */
	public List<MovimientoImagen> getMovimientoImagenListWithRestrictions(MovimientoImagenParam filter) {
		String queryString = " FROM MovimientoImagen AS e ";
		queryString = addRestrictions(queryString, filter);
		List<MovimientoImagen> movimientoImagens = getSessionFactory().getCurrentSession().createQuery(queryString)
				.setMaxResults(49000000).list();
		return movimientoImagens;
	}

	/**
	 * Actualiza una movimientoImagen
	 * 
	 * @param movimientoImagen
	 *            MovimientoImagen
	 */
	public void updateMovimientoImagen(MovimientoImagen movimientoImagen) {
		getSessionFactory().getCurrentSession().update(movimientoImagen);
	}

	/**
	 * Borra una movimientoImagen
	 * 
	 * @param movimientoImagen
	 *            MovimientoImagen
	 */
	public void deleteMovimientoImagen(MovimientoImagen movimientoImagen) {
		getSessionFactory().getCurrentSession().delete(movimientoImagen);
	}

	public List<MovimientoImagen> getMovimientoImagenDesdeFecha(Date fecha) {
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("FROM MovimientoImagen as m WHERE m.fechaInicio >=:param1");
		query.setParameter("param1", fecha);
		return query.list();
	}

	public MovimientoImagen getMovimientoImagenPorPlan(Plan plan) {

		Query query = getSessionFactory().getCurrentSession()
				.createQuery("FROM MovimientoImagen as m WHERE m.plan =:param1");
		query.setParameter("param1",plan);
		List<MovimientoImagen> list = query.list();
		if (list != null && !list.isEmpty())
			return (MovimientoImagen) list.get(0);
		return null;

	}

}
