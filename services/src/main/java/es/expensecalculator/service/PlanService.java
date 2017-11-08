package es.expensecalculator.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.expensecalculator.dao.IPlanDAO;
import es.expensecalculator.model.Plan;
import es.expensecalculator.model.Usuario;

@Transactional(readOnly = true)
public class PlanService implements IPlanService {

	IPlanDAO planDAO;

	@Override
	public Plan getPlanById(long id) {
		return planDAO.getPlanById(id);
	}

	
	@Transactional(readOnly = false)
	public void addPlan(Plan plan) {
		planDAO.addPlan(plan);
	}

	@Transactional(readOnly = false)
	public void actualizarPlan(Plan plan) {
		planDAO.updatePlan(plan);
	}

	@Transactional(readOnly = false)
	public void borrarPlan(Plan plan) {
		planDAO.deletePlan(plan);

	}

	public IPlanDAO getPlanDAO() {
		return planDAO;
	}

	public void setPlanDAO(IPlanDAO planDAO) {
		this.planDAO = planDAO;
	}

	@Override
	public List<Plan> getPlanDesdeDescripcion(String descripcion, Usuario usuario) {
		// TODO Auto-generated method stub
		return planDAO.getPlanDesdeDescripcion(descripcion, usuario);
	}

	@Override
	public Plan getPlanPorUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return planDAO.getPlanPorUsuario(usuario);
	}


	@Override
	public List<Plan> getPlanDesdeDescripcionExacta(String descripcion, Usuario usuario) {
		// TODO Auto-generated method stub
		return planDAO.getPlanDesdeDescripcionExacta(descripcion, usuario);
	}


	@Override
	public List<Plan> getPlanesPorUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return planDAO.getPlanesPorUsuario(usuario);
	}

}
