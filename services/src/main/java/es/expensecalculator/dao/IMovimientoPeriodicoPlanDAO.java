package es.expensecalculator.dao;

import java.util.Date;
import java.util.List;

import es.expensecalculator.model.MovimientoPeriodicoPlan;
import es.expensecalculator.web.param.MovimientoPeriodicoPlanParam;

/**
 * Interfaz de acceso a los datos de las movimientoes
 */
public interface IMovimientoPeriodicoPlanDAO {

	/**
	 * Devuelve una movimiento por su clave
	 * 
	 * @param id
	 *            Clave primaria
	 * @return MovimientoPeriodicoPlan
	 */
	MovimientoPeriodicoPlan getMovimientoPeriodicoPlanById(long id);

	/**
	 * Devuelve una lista de movimientoes
	 * 
	 * @param filter
	 *            Contenedor con los criterios de búsqueda
	 * @return Lista de movimientoes
	 */
	List<MovimientoPeriodicoPlan> getMovimientoPeriodicoPlanListWithRestrictions(MovimientoPeriodicoPlanParam filter);

	/**
	 * Inserta una movimiento
	 * 
	 * @param movimiento
	 *            MovimientoPeriodicoPlan
	 */
	void addMovimientoPeriodicoPlan(MovimientoPeriodicoPlan movimiento);

	/**
	 * Actualiza una movimiento
	 * 
	 * @param movimiento
	 *            MovimientoPeriodicoPlan
	 */
	void updateMovimientoPeriodicoPlan(MovimientoPeriodicoPlan movimiento);

	/**
	 * Borra una movimiento
	 * 
	 * @param movimiento
	 *            MovimientoPeriodicoPlan
	 */
	void deleteMovimientoPeriodicoPlan(MovimientoPeriodicoPlan movimiento);

	List<MovimientoPeriodicoPlan> getMovimientoDesdeFecha(Date fecha);

}
