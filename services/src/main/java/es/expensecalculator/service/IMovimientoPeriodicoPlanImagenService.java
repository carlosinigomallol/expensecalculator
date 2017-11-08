package es.expensecalculator.service;

import java.util.Date;
import java.util.List;

import es.expensecalculator.model.MovimientoPeriodicoPlanImagen;
import es.expensecalculator.model.Plan;
import es.expensecalculator.web.param.MovimientoPeriodicoPlanImagenParam;



/**
 * Interfaz de los servicios de persistencia de las entidades
 */
public interface IMovimientoPeriodicoPlanImagenService {

	/**
	 * Devuelve una entidad por su clave primaria
	 * 
	 * @param id
	 *            Clave primaria
	 * @return MovimientoPeriodicoPlanImagen
	 */
	MovimientoPeriodicoPlanImagen getMovimientoPeriodicoPlanImagenById(long id);

	/**
	 * Devuelve una lista de entiadades
	 * 
	 * @param filter
	 *            Contenedor de datos de los criterios de búsqueda
	 * @return Lista de entidades
	 */
	List<MovimientoPeriodicoPlanImagen> getMovimientoPeriodicoPlanImagenListWithRestrictions(MovimientoPeriodicoPlanImagenParam filter);

	/**
	 * Inserta una entidad
	 * 
	 * @param entidad
	 *            MovimientoPeriodicoPlanImagen
	 */
	void addMovimientoPeriodicoPlanImagen(MovimientoPeriodicoPlanImagen movimiento);

	/**
	 * Actualiza una entidad
	 * 
	 * @param entidad
	 *            MovimientoPeriodicoPlanImagen
	 */
	void actualizarMovimientoPeriodicoPlanImagen(MovimientoPeriodicoPlanImagen movimiento);

	/**
	 * Borra una entidad
	 * 
	 * @param entidad
	 *            MovimientoPeriodicoPlanImagen
	 */
	void borrarMovimientoPeriodicoPlanImagen(MovimientoPeriodicoPlanImagen movimiento);
	
	List<MovimientoPeriodicoPlanImagen> getMovimientoDesdeFecha(Date fecha);

	List<MovimientoPeriodicoPlanImagen> getMovimientosDesdePlan(Plan planSeleccionadoCombo);
}
