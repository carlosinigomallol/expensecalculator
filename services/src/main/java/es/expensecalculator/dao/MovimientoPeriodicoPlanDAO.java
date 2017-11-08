package es.expensecalculator.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import es.expensecalculator.model.MovimientoPeriodicoPlan;
import es.expensecalculator.model.MovimientoPlan;
import es.expensecalculator.model.configuration.GlobalConstants;
import es.expensecalculator.web.param.MovimientoPeriodicoPlanParam;

/**
 * Implementación del DAO para la tabla MovimientoPeriodicoPlan
 */
public class MovimientoPeriodicoPlanDAO implements IMovimientoPeriodicoPlanDAO {

	/** Logger */
	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(MovimientoPeriodicoPlanDAO.class);

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
	 * @param MovimientoPeriodicoPlan
	 *            MovimientoPeriodicoPlan
	 */
	public void addMovimientoPeriodicoPlan(MovimientoPeriodicoPlan MovimientoPeriodicoPlan) {
		getSessionFactory().getCurrentSession().save(MovimientoPeriodicoPlan);
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
	private String addRestrictions(String queryString, MovimientoPeriodicoPlanParam filter) {
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

		if (filter.getCantidad() != null) {
			restrictions.add(" e.cantidad = " + filter.getCantidad() + " ");
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
	 * Devuelve una MovimientoPeriodicoPlan por su clave
	 * 
	 * @param id
	 *            Clave primaria
	 * @return MovimientoPeriodicoPlan
	 */
	public MovimientoPeriodicoPlan getMovimientoPeriodicoPlanById(long id) {
		List<MovimientoPeriodicoPlan> list = getSessionFactory().getCurrentSession().createQuery("from MovimientoPeriodicoPlan where id=?")
				.setParameter(0, id).list();
		MovimientoPeriodicoPlan movimiento = null;
		for (MovimientoPeriodicoPlan e : list) {
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
	public List<MovimientoPeriodicoPlan> getMovimientoPeriodicoPlanListWithRestrictions(MovimientoPeriodicoPlanParam filter) {
		String queryString = " FROM MovimientoPeriodicoPlan AS e ";
		queryString = addRestrictions(queryString, filter);
		List<MovimientoPeriodicoPlan> movimientos = getSessionFactory().getCurrentSession().createQuery(queryString)
				.setMaxResults(49000000).list();
		return movimientos;
	}

	/**
	 * Actualiza una movimiento
	 * 
	 * @param movimiento
	 *            MovimientoPeriodicoPlan
	 */
	public void updateMovimientoPeriodicoPlan(MovimientoPeriodicoPlan movimiento) {
		getSessionFactory().getCurrentSession().update(movimiento);
	}

	/**
	 * Borra una movimiento
	 * 
	 * @param movimiento
	 *            MovimientoPeriodicoPlan
	 */
	public void deleteMovimientoPeriodicoPlan(MovimientoPeriodicoPlan movimiento) {
		getSessionFactory().getCurrentSession().delete(movimiento);
	}

	@Override
	public List<MovimientoPeriodicoPlan> getMovimientoDesdeFecha(Date fecha) {
		Query query = getSessionFactory().getCurrentSession().createQuery("FROM MovimientoPeriodicoPlan as m WHERE m.fechaInicio >=:param1");
		query.setParameter("param1", fecha);
		return query.list();
	}
	
}
