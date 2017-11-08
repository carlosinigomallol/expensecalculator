package es.expensecalculator.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import es.expensecalculator.model.MovimientoImagen;
import es.expensecalculator.model.Plan;
import es.expensecalculator.model.Usuario;
import es.expensecalculator.web.param.MovimientoImagenParam;

/**
 * Interfaz de acceso a los datos de las movimientoImagenes
 */
public interface IMovimientoImagenDAO {

	/**
	 * Devuelve una movimientoImagen por su clave
	 * 
	 * @param id
	 *            Clave primaria
	 * @return MovimientoImagen
	 */
	MovimientoImagen getMovimientoImagenById(long id);

	/**
	 * Devuelve una lista de movimientoImagenes
	 * 
	 * @param filter
	 *            Contenedor con los criterios de búsqueda
	 * @return Lista de movimientoImagenes
	 */
	List<MovimientoImagen> getMovimientoImagenListWithRestrictions(MovimientoImagenParam filter);

	/**
	 * Inserta una movimientoImagen
	 * 
	 * @param movimientoImagen
	 *            MovimientoImagen
	 */
	void addMovimientoImagen(MovimientoImagen movimientoImagen);

	/**
	 * Actualiza una movimientoImagen
	 * 
	 * @param movimientoImagen
	 *            MovimientoImagen
	 */
	void updateMovimientoImagen(MovimientoImagen movimientoImagen);

	/**
	 * Borra una movimientoImagen
	 * 
	 * @param movimientoImagen
	 *            MovimientoImagen
	 */
	void deleteMovimientoImagen(MovimientoImagen movimientoImagen);

	List<MovimientoImagen> getMovimientoImagenDesdeFecha(Date fecha);

	MovimientoImagen getMovimientoImagenPorPlan(Plan plan);

}
