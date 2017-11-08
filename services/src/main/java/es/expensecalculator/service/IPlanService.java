package es.expensecalculator.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import es.expensecalculator.model.Plan;
import es.expensecalculator.model.Usuario;



/**
 * Interfaz de los servicios de persistencia de las entidades
 */
public interface IPlanService {

	/**
	 * Devuelve una entidad por su clave primaria
	 * 
	 * @param id
	 *            Clave primaria
	 * @return Plan
	 */
	Plan getPlanById(long id);

	/**
	 * Inserta una entidad
	 * 
	 * @param entidad
	 *            Plan
	 */
	void addPlan(Plan plan);

	/**
	 * Actualiza una entidad
	 * 
	 * @param entidad
	 *            Plan
	 */
	void actualizarPlan(Plan plan);

	/**
	 * Borra una entidad
	 * 
	 * @param entidad
	 *            Plan
	 */
	void borrarPlan(Plan plan);

	List<Plan> getPlanDesdeDescripcion(String descripcion, Usuario usuario);
	List<Plan> getPlanDesdeDescripcionExacta(String descripcion, Usuario usuario);
	
	List<Plan> getPlanesPorUsuario(Usuario usuario);

	Plan getPlanPorUsuario(Usuario usuario);
}
