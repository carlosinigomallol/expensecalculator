package es.expensecalculator.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.expensecalculator.dao.IMovimientoPlanDAO;
import es.expensecalculator.model.MovimientoPlan;
import es.expensecalculator.web.param.MovimientoPlanParam;

@Transactional(readOnly = true)
public class MovimientoPlanService implements IMovimientoPlanService {

	/** DAO de las cuenta-titular */
	IMovimientoPlanDAO movimientoPlanDAO;

	@Override
	public MovimientoPlan getMovimientoPlanById(long id) {
		return movimientoPlanDAO.getMovimientoPlanById(id);
	}

	@Override
	public List<MovimientoPlan> getMovimientoPlanListWithRestrictions(MovimientoPlanParam filter) {
		return movimientoPlanDAO.getMovimientoPlanListWithRestrictions(filter);
	}

	@Transactional(readOnly = false)
	public void addMovimientoPlan(MovimientoPlan movimiento) {
		movimientoPlanDAO.addMovimientoPlan(movimiento);
	}

	@Transactional(readOnly = false)
	public void actualizarMovimientoPlan(MovimientoPlan movimiento) {
		movimientoPlanDAO.updateMovimientoPlan(movimiento);
	}

	@Transactional(readOnly = false)
	public void borrarMovimientoPlan(MovimientoPlan movimiento) {
		movimientoPlanDAO.deleteMovimientoPlan(movimiento);

	}

	public IMovimientoPlanDAO getMovimientoPlanDAO() {
		return movimientoPlanDAO;
	}

	public void setMovimientoPlanDAO(IMovimientoPlanDAO movimientoPlanDAO) {
		this.movimientoPlanDAO = movimientoPlanDAO;
	}

	@Override
	public List<MovimientoPlan> getMovimientoDesdeFecha(Date fecha) {
		// TODO Auto-generated method stub
		return movimientoPlanDAO.getMovimientoDesdeFecha( fecha);
	}
	
	
	

}
