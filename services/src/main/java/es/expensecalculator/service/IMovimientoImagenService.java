package es.expensecalculator.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import es.expensecalculator.model.MovimientoImagen;
import es.expensecalculator.model.Plan;
import es.expensecalculator.model.Usuario;
import es.expensecalculator.web.param.MovimientoImagenParam;



/**
 * Interfaz de los servicios de persistencia de las entidades
 */
public interface IMovimientoImagenService {

	/**
	 * Devuelve una entidad por su clave primaria
	 * 
	 * @param id
	 *            Clave primaria
	 * @return MovimientoImagen
	 */
	MovimientoImagen getMovimientoImagenById(long id);

	/**
	 * Devuelve una lista de entiadades
	 * 
	 * @param filter
	 *            Contenedor de datos de los criterios de búsqueda
	 * @return Lista de entidades
	 */
	List<MovimientoImagen> getMovimientoImagenListWithRestrictions(MovimientoImagenParam filter);

	/**
	 * Inserta una entidad
	 * 
	 * @param entidad
	 *            MovimientoImagen
	 */
	void addMovimientoImagen(MovimientoImagen movimientoImagen);

	/**
	 * Actualiza una entidad
	 * 
	 * @param entidad
	 *            MovimientoImagen
	 */
	void actualizarMovimientoImagen(MovimientoImagen movimientoImagen);

	/**
	 * Borra una entidad
	 * 
	 * @param entidad
	 *            MovimientoImagen
	 */
	void borrarMovimientoImagen(MovimientoImagen movimientoImagen);

	List<MovimientoImagen> getMovimientoImagenDesdeFecha(Date fecha);

	MovimientoImagen getMovimientoImagenPorPlan(Plan plan);
}
