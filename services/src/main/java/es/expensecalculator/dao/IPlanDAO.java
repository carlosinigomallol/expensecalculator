package es.expensecalculator.dao;

import java.util.List;

import es.expensecalculator.model.Plan;
import es.expensecalculator.model.Usuario;

/**
 * Interfaz de acceso a los datos de las planes
 */
public interface IPlanDAO {

	/**
	 * Devuelve una plan por su clave
	 * 
	 * @param id
	 *            Clave primaria
	 * @return Plan
	 */
	Plan getPlanById(long id);

	/**
	 * Inserta una plan
	 * 
	 * @param plan
	 *            Plan
	 */
	void addPlan(Plan plan);

	/**
	 * Actualiza una plan
	 * 
	 * @param plan
	 *            Plan
	 */
	void updatePlan(Plan plan);

	/**
	 * Borra una plan
	 * 
	 * @param plan
	 *            Plan
	 */
	void deletePlan(Plan plan);

	List<Plan> getPlanDesdeDescripcion(String descripcion, Usuario usuario);

	Plan getPlanPorUsuario(Usuario usuario);

	List<Plan> getPlanDesdeDescripcionExacta(String descripcion, Usuario usuario);

	List<Plan> getPlanesPorUsuario(Usuario usuario);

}
