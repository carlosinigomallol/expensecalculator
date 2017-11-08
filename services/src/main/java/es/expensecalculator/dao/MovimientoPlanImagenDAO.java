package es.expensecalculator.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import es.expensecalculator.model.MovimientoPlanImagen;
import es.expensecalculator.model.Plan;
import es.expensecalculator.model.configuration.GlobalConstants;
import es.expensecalculator.web.param.MovimientoPlanImagenParam;

/**
 * Implementación del DAO para la tabla MovimientoPlanImagen
 */
public class MovimientoPlanImagenDAO implements IMovimientoPlanImagenDAO {

	/** Logger */
	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(MovimientoPlanImagenDAO.class);

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
	 * Inserta una movimiento
	 * 
	 * @param MovimientoPlanImagen
	 *            MovimientoPlanImagen
	 */
	public void addMovimientoPlanImagen(MovimientoPlanImagen MovimientoPlanImagen) {
		getSessionFactory().getCurrentSession().save(MovimientoPlanImagen);
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
	private String addRestrictions(String queryString, MovimientoPlanImagenParam filter) {
		List<String> restrictions = new ArrayList<String>();
		String ret = null;
		int conditions = 0;
		// SimpleDateFormat simpleDateFormat = new
		// SimpleDateFormat("yyyy-MM-dd");
		// String tiFecomuString = null;

		
		
		if (filter.getUsuario() != null) {
			restrictions.add(" e.usuario.id = " + filter.getUsuario().getId() + " ");
		}
		
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
	 * Devuelve una MovimientoPlanImagen por su clave
	 * 
	 * @param id
	 *            Clave primaria
	 * @return MovimientoPlanImagen
	 */
	public MovimientoPlanImagen getMovimientoPlanImagenById(long id) {
		List<MovimientoPlanImagen> list = getSessionFactory().getCurrentSession().createQuery("from MovimientoPlanImagen where id=?")
				.setParameter(0, id).list();
		MovimientoPlanImagen movimiento = null;
		for (MovimientoPlanImagen e : list) {
			movimiento = e;
		}
		return movimiento;
	}

	/**
	 * Devuelve una lista de movimientoes
	 * 
	 * @param filter
	 *            Contenedor con los criterios de búsqueda
	 * @return Lista de movimientoes
	 */
	public List<MovimientoPlanImagen> getMovimientoPlanImagenListWithRestrictions(MovimientoPlanImagenParam filter) {
		String queryString = " FROM MovimientoPlanImagen AS e ";
		queryString = addRestrictions(queryString, filter);
		List<MovimientoPlanImagen> movimientos = getSessionFactory().getCurrentSession().createQuery(queryString)
				.setMaxResults(49000000).list();
		return movimientos;
	}

	/**
	 * Actualiza una movimiento
	 * 
	 * @param movimiento
	 *            MovimientoPlanImagen
	 */
	public void updateMovimientoPlanImagen(MovimientoPlanImagen movimiento) {
		getSessionFactory().getCurrentSession().update(movimiento);
	}

	/**
	 * Borra una movimiento
	 * 
	 * @param movimiento
	 *            MovimientoPlanImagen
	 */
	public void deleteMovimientoPlanImagen(MovimientoPlanImagen movimiento) {
		getSessionFactory().getCurrentSession().delete(movimiento);
	}

	@Override
	public List<MovimientoPlanImagen> getMovimientoDesdeFecha(Date fecha) {
		Query query = getSessionFactory().getCurrentSession().createQuery("FROM MovimientoPlanImagen as m WHERE m.fechaInicio >=:param1");
		query.setParameter("param1", fecha);
		return query.list();
	}

	@Override
	public List<MovimientoPlanImagen> getMovimientosDesdePlan(Plan planSeleccionadoCombo) {
		Query query = getSessionFactory().getCurrentSession().createQuery("FROM MovimientoPlanImagen as m WHERE m.plan =:param1");
		query.setParameter("param1", planSeleccionadoCombo);
		return query.list();
	
	}

	
	
}
