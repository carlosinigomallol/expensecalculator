package es.expensecalculator.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import es.expensecalculator.model.Movimiento;
import es.expensecalculator.model.Usuario;
import es.expensecalculator.web.param.MovimientoParam;

/**
 * Interfaz de acceso a los datos de las movimientoes
 */
public interface IMovimientoDAO {

	/**
	 * Devuelve una movimiento por su clave
	 * 
	 * @param id
	 *            Clave primaria
	 * @return Movimiento
	 */
	Movimiento getMovimientoById(long id);

	/**
	 * Devuelve una lista de movimientoes
	 * 
	 * @param filter
	 *            Contenedor con los criterios de búsqueda
	 * @return Lista de movimientoes
	 */
	List<Movimiento> getMovimientoListWithRestrictions(MovimientoParam filter);

	/**
	 * Inserta una movimiento
	 * 
	 * @param movimiento
	 *            Movimiento
	 */
	void addMovimiento(Movimiento movimiento);

	/**
	 * Actualiza una movimiento
	 * 
	 * @param movimiento
	 *            Movimiento
	 */
	void updateMovimiento(Movimiento movimiento);

	/**
	 * Borra una movimiento
	 * 
	 * @param movimiento
	 *            Movimiento
	 */
	void deleteMovimiento(Movimiento movimiento);

	List<Movimiento> getMovimientoDesdeFecha(Date fecha);

	Movimiento getMovimientoPorUsuario(Usuario usuario);

}
