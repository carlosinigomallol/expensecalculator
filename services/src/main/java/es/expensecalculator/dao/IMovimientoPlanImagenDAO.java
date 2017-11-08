package es.expensecalculator.dao;

import java.util.Date;
import java.util.List;

import es.expensecalculator.model.MovimientoPlanImagen;
import es.expensecalculator.model.Plan;
import es.expensecalculator.web.param.MovimientoPlanImagenParam;

/**
 * Interfaz de acceso a los datos de las movimientoes
 */
public interface IMovimientoPlanImagenDAO {

	/**
	 * Devuelve una movimiento por su clave
	 * 
	 * @param id
	 *            Clave primaria
	 * @return MovimientoPlanImagen
	 */
	MovimientoPlanImagen getMovimientoPlanImagenById(long id);

	/**
	 * Devuelve una lista de movimientoes
	 * 
	 * @param filter
	 *            Contenedor con los criterios de búsqueda
	 * @return Lista de movimientoes
	 */
	List<MovimientoPlanImagen> getMovimientoPlanImagenListWithRestrictions(MovimientoPlanImagenParam filter);

	/**
	 * Inserta una movimiento
	 * 
	 * @param movimiento
	 *            MovimientoPlanImagen
	 */
	void addMovimientoPlanImagen(MovimientoPlanImagen movimiento);

	/**
	 * Actualiza una movimiento
	 * 
	 * @param movimiento
	 *            MovimientoPlanImagen
	 */
	void updateMovimientoPlanImagen(MovimientoPlanImagen movimiento);

	/**
	 * Borra una movimiento
	 * 
	 * @param movimiento
	 *            MovimientoPlanImagen
	 */
	void deleteMovimientoPlanImagen(MovimientoPlanImagen movimiento);

	List<MovimientoPlanImagen> getMovimientoDesdeFecha(Date fecha);

	List<MovimientoPlanImagen> getMovimientosDesdePlan(Plan planSeleccionadoCombo);

}
