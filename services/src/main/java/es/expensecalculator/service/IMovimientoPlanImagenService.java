package es.expensecalculator.service;

import java.util.Date;
import java.util.List;

import es.expensecalculator.model.MovimientoPlanImagen;
import es.expensecalculator.model.Plan;
import es.expensecalculator.web.param.MovimientoPlanImagenParam;



/**
 * Interfaz de los servicios de persistencia de las entidades
 */
public interface IMovimientoPlanImagenService {

	/**
	 * Devuelve una entidad por su clave primaria
	 * 
	 * @param id
	 *            Clave primaria
	 * @return MovimientoPlanImagen
	 */
	MovimientoPlanImagen getMovimientoPlanImagenById(long id);

	/**
	 * Devuelve una lista de entiadades
	 * 
	 * @param filter
	 *            Contenedor de datos de los criterios de búsqueda
	 * @return Lista de entidades
	 */
	List<MovimientoPlanImagen> getMovimientoPlanImagenListWithRestrictions(MovimientoPlanImagenParam filter);
	
	List<MovimientoPlanImagen> getMovimientoDesdeFecha(Date fecha);

	/**
	 * Inserta una entidad
	 * 
	 * @param entidad
	 *            MovimientoPlanImagen
	 */
	void addMovimientoPlanImagen(MovimientoPlanImagen movimiento);

	/**
	 * Actualiza una entidad
	 * 
	 * @param entidad
	 *            MovimientoPlanImagen
	 */
	void actualizarMovimientoPlanImagen(MovimientoPlanImagen movimiento);

	/**
	 * Borra una entidad
	 * 
	 * @param entidad
	 *            MovimientoPlanImagen
	 */
	void borrarMovimientoPlanImagen(MovimientoPlanImagen movimiento);

	List<MovimientoPlanImagen> getMovimientosDesdePlan(Plan planSeleccionadoCombo);
}
