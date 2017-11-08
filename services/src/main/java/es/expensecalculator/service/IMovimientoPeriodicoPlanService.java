package es.expensecalculator.service;

import java.util.Date;
import java.util.List;

import es.expensecalculator.model.MovimientoPeriodicoPlan;
import es.expensecalculator.web.param.MovimientoPeriodicoPlanParam;



/**
 * Interfaz de los servicios de persistencia de las entidades
 */
public interface IMovimientoPeriodicoPlanService {

	/**
	 * Devuelve una entidad por su clave primaria
	 * 
	 * @param id
	 *            Clave primaria
	 * @return MovimientoPeriodicoPlan
	 */
	MovimientoPeriodicoPlan getMovimientoPeriodicoPlanById(long id);

	/**
	 * Devuelve una lista de entiadades
	 * 
	 * @param filter
	 *            Contenedor de datos de los criterios de búsqueda
	 * @return Lista de entidades
	 */
	List<MovimientoPeriodicoPlan> getMovimientoPeriodicoPlanListWithRestrictions(MovimientoPeriodicoPlanParam filter);

	/**
	 * Inserta una entidad
	 * 
	 * @param entidad
	 *            MovimientoPeriodicoPlan
	 */
	void addMovimientoPeriodicoPlan(MovimientoPeriodicoPlan movimiento);

	/**
	 * Actualiza una entidad
	 * 
	 * @param entidad
	 *            MovimientoPeriodicoPlan
	 */
	void actualizarMovimientoPeriodicoPlan(MovimientoPeriodicoPlan movimiento);

	/**
	 * Borra una entidad
	 * 
	 * @param entidad
	 *            MovimientoPeriodicoPlan
	 */
	void borrarMovimientoPeriodicoPlan(MovimientoPeriodicoPlan movimiento);
	
	List<MovimientoPeriodicoPlan> getMovimientoDesdeFecha(Date fecha);
}
