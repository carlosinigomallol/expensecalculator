package es.expensecalculator.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import es.expensecalculator.model.Plan;
import es.expensecalculator.model.Usuario;
import es.expensecalculator.model.configuration.GlobalConstants;

/**
 * Implementación del DAO para la tabla Plan
 */
public class PlanDAO implements IPlanDAO {

	/** Logger */
	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(PlanDAO.class);

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
	 * Inserta una plan
	 * 
	 * @param Plan
	 *            Plan
	 */
	public void addPlan(Plan Plan) {
		getSessionFactory().getCurrentSession().save(Plan);
	}

	/**
	 * Devuelve una Plan por su clave
	 * 
	 * @param id
	 *            Clave primaria
	 * @return Plan
	 */
	public Plan getPlanById(long id) {
		List<Plan> list = getSessionFactory().getCurrentSession().createQuery("from Plan where id=?")
				.setParameter(0, id).list();
		Plan plan = null;
		for (Plan e : list) {
			plan = e;
		}
		return plan;
	}

	/**
	 * Actualiza una plan
	 * 
	 * @param plan
	 *            Plan
	 */
	public void updatePlan(Plan plan) {
		getSessionFactory().getCurrentSession().update(plan);
	}

	/**
	 * Borra una plan
	 * 
	 * @param plan
	 *            Plan
	 */
	public void deletePlan(Plan plan) {
		getSessionFactory().getCurrentSession().delete(plan);
	}

	@Override
	public List<Plan> getPlanDesdeDescripcion(String descripcion, Usuario usuario) {
		Query query = null;
		if (descripcion != null && descripcion.length() > 0) {
			query = getSessionFactory().getCurrentSession()
					.createQuery("FROM Plan as m WHERE m.usuario=:param2 AND m.descripcionPlan like '%"+descripcion+"%' ");
			query.setParameter("param2", usuario);

		} else {
			query = getSessionFactory().getCurrentSession().createQuery("FROM Plan as m WHERE m.usuario=:param2");
			query.setParameter("param2", usuario);
		}

		return query.list();
	}

	@Override
	public Plan getPlanPorUsuario(Usuario usuario) {

		Query query = getSessionFactory().getCurrentSession().createQuery("FROM Plan as m WHERE m.usuario >=:param1");
		query.setParameter("param1", usuario);
		List<Plan> list = query.list();
		if (list != null && !list.isEmpty())
			return (Plan) list.get(0);
		return null;

	}

	@Override
	public List<Plan> getPlanDesdeDescripcionExacta(String descripcion, Usuario usuario) {
		Query query = null;
		if (descripcion != null && descripcion.length() > 0) {
			query = getSessionFactory().getCurrentSession()
					.createQuery("FROM Plan as m WHERE m.usuario=:param2 AND m.descripcionPlan =:param1");
			query.setParameter("param1", descripcion);
			query.setParameter("param2", usuario);

		}

		return query.list();
	}

	@Override
	public List<Plan> getPlanesPorUsuario(Usuario usuario) {
		Query query = null;
		query = getSessionFactory().getCurrentSession().createQuery("FROM Plan as m WHERE m.usuario=:param2");
		query.setParameter("param2", usuario);
		return query.list();
	}

}
