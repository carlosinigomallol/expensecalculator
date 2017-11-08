package es.expensecalculator.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import es.expensecalculator.model.Movimiento;
import es.expensecalculator.model.Usuario;
import es.expensecalculator.web.param.MovimientoParam;



/**
 * Interfaz de los servicios de persistencia de las entidades
 */
public interface IMovimientoService {

	/**
	 * Devuelve una entidad por su clave primaria
	 * 
	 * @param id
	 *            Clave primaria
	 * @return Movimiento
	 */
	Movimiento getMovimientoById(long id);

	/**
	 * Devuelve una lista de entiadades
	 * 
	 * @param filter
	 *            Contenedor de datos de los criterios de búsqueda
	 * @return Lista de entidades
	 */
	List<Movimiento> getMovimientoListWithRestrictions(MovimientoParam filter);

	/**
	 * Inserta una entidad
	 * 
	 * @param entidad
	 *            Movimiento
	 */
	void addMovimiento(Movimiento movimiento);

	/**
	 * Actualiza una entidad
	 * 
	 * @param entidad
	 *            Movimiento
	 */
	void actualizarMovimiento(Movimiento movimiento);

	/**
	 * Borra una entidad
	 * 
	 * @param entidad
	 *            Movimiento
	 */
	void borrarMovimiento(Movimiento movimiento);

	List<Movimiento> getMovimientoDesdeFecha(Date fecha);

	Movimiento getMovimientoPorUsuario(Usuario usuario);
}
