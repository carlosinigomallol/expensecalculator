package es.expensecalculator.dao;

import java.util.Date;
import java.util.List;

import es.expensecalculator.model.MovimientoPlan;
import es.expensecalculator.web.param.MovimientoPlanParam;

/**
 * Interfaz de acceso a los datos de las movimientoes
 */
public interface IMovimientoPlanDAO {

	/**
	 * Devuelve una movimiento por su clave
	 * 
	 * @param id
	 *            Clave primaria
	 * @return MovimientoPlan
	 */
	MovimientoPlan getMovimientoPlanById(long id);

	/**
	 * Devuelve una lista de movimientoes
	 * 
	 * @param filter
	 *            Contenedor con los criterios de búsqueda
	 * @return Lista de movimientoes
	 */
	List<MovimientoPlan> getMovimientoPlanListWithRestrictions(MovimientoPlanParam filter);

	/**
	 * Inserta una movimiento
	 * 
	 * @param movimiento
	 *            MovimientoPlan
	 */
	void addMovimientoPlan(MovimientoPlan movimiento);

	/**
	 * Actualiza una movimiento
	 * 
	 * @param movimiento
	 *            MovimientoPlan
	 */
	void updateMovimientoPlan(MovimientoPlan movimiento);

	/**
	 * Borra una movimiento
	 * 
	 * @param movimiento
	 *            MovimientoPlan
	 */
	void deleteMovimientoPlan(MovimientoPlan movimiento);

	List<MovimientoPlan> getMovimientoDesdeFecha(Date fecha);

}
