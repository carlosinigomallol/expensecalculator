package es.expensecalculator.service;

import java.util.Date;
import java.util.List;

import es.expensecalculator.model.MovimientoPlan;
import es.expensecalculator.web.param.MovimientoPlanParam;



/**
 * Interfaz de los servicios de persistencia de las entidades
 */
public interface IMovimientoPlanService {

	/**
	 * Devuelve una entidad por su clave primaria
	 * 
	 * @param id
	 *            Clave primaria
	 * @return MovimientoPlan
	 */
	MovimientoPlan getMovimientoPlanById(long id);

	/**
	 * Devuelve una lista de entiadades
	 * 
	 * @param filter
	 *            Contenedor de datos de los criterios de búsqueda
	 * @return Lista de entidades
	 */
	List<MovimientoPlan> getMovimientoPlanListWithRestrictions(MovimientoPlanParam filter);
	
	List<MovimientoPlan> getMovimientoDesdeFecha(Date fecha);

	/**
	 * Inserta una entidad
	 * 
	 * @param entidad
	 *            MovimientoPlan
	 */
	void addMovimientoPlan(MovimientoPlan movimiento);

	/**
	 * Actualiza una entidad
	 * 
	 * @param entidad
	 *            MovimientoPlan
	 */
	void actualizarMovimientoPlan(MovimientoPlan movimiento);

	/**
	 * Borra una entidad
	 * 
	 * @param entidad
	 *            MovimientoPlan
	 */
	void borrarMovimientoPlan(MovimientoPlan movimiento);
}
