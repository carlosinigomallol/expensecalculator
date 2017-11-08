package es.expensecalculator.dao;

import java.util.Date;
import java.util.List;

import es.expensecalculator.model.MovimientoPeriodicoPlanImagen;
import es.expensecalculator.model.Plan;
import es.expensecalculator.web.param.MovimientoPeriodicoPlanImagenParam;

/**
 * Interfaz de acceso a los datos de las movimientoes
 */
public interface IMovimientoPeriodicoPlanImagenDAO {

	/**
	 * Devuelve una movimiento por su clave
	 * 
	 * @param id
	 *            Clave primaria
	 * @return MovimientoPeriodicoPlanImagen
	 */
	MovimientoPeriodicoPlanImagen getMovimientoPeriodicoPlanImagenById(long id);

	/**
	 * Devuelve una lista de movimientoes
	 * 
	 * @param filter
	 *            Contenedor con los criterios de búsqueda
	 * @return Lista de movimientoes
	 */
	List<MovimientoPeriodicoPlanImagen> getMovimientoPeriodicoPlanImagenListWithRestrictions(MovimientoPeriodicoPlanImagenParam filter);

	/**
	 * Inserta una movimiento
	 * 
	 * @param movimiento
	 *            MovimientoPeriodicoPlanImagen
	 */
	void addMovimientoPeriodicoPlanImagen(MovimientoPeriodicoPlanImagen movimiento);

	/**
	 * Actualiza una movimiento
	 * 
	 * @param movimiento
	 *            MovimientoPeriodicoPlanImagen
	 */
	void updateMovimientoPeriodicoPlanImagen(MovimientoPeriodicoPlanImagen movimiento);

	/**
	 * Borra una movimiento
	 * 
	 * @param movimiento
	 *            MovimientoPeriodicoPlanImagen
	 */
	void deleteMovimientoPeriodicoPlanImagen(MovimientoPeriodicoPlanImagen movimiento);

	List<MovimientoPeriodicoPlanImagen> getMovimientoDesdeFecha(Date fecha);

	List<MovimientoPeriodicoPlanImagen> getMovimientosDesdePlan(Plan planSeleccionadoCombo);

}
