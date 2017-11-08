package es.expensecalculator.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.expensecalculator.dao.IMovimientoPeriodicoPlanDAO;
import es.expensecalculator.model.MovimientoPeriodicoPlan;
import es.expensecalculator.web.param.MovimientoPeriodicoPlanParam;

@Transactional(readOnly = true)
public class MovimientoPeriodicoPlanService implements IMovimientoPeriodicoPlanService {

	/** DAO de las cuenta-titular */
	IMovimientoPeriodicoPlanDAO movimientoPeriodicoPlanDAO;

	@Override
	public MovimientoPeriodicoPlan getMovimientoPeriodicoPlanById(long id) {
		return movimientoPeriodicoPlanDAO.getMovimientoPeriodicoPlanById(id);
	}

	@Override
	public List<MovimientoPeriodicoPlan> getMovimientoPeriodicoPlanListWithRestrictions(MovimientoPeriodicoPlanParam filter) {
		return movimientoPeriodicoPlanDAO.getMovimientoPeriodicoPlanListWithRestrictions(filter);
	}

	@Transactional(readOnly = false)
	public void addMovimientoPeriodicoPlan(MovimientoPeriodicoPlan movimiento) {
		movimientoPeriodicoPlanDAO.addMovimientoPeriodicoPlan(movimiento);
	}

	@Transactional(readOnly = false)
	public void actualizarMovimientoPeriodicoPlan(MovimientoPeriodicoPlan movimiento) {
		movimientoPeriodicoPlanDAO.updateMovimientoPeriodicoPlan(movimiento);
	}

	@Transactional(readOnly = false)
	public void borrarMovimientoPeriodicoPlan(MovimientoPeriodicoPlan movimiento) {
		movimientoPeriodicoPlanDAO.deleteMovimientoPeriodicoPlan(movimiento);

	}

	public IMovimientoPeriodicoPlanDAO getMovimientoPeriodicoPlanDAO() {
		return movimientoPeriodicoPlanDAO;
	}

	public void setMovimientoPeriodicoPlanDAO(IMovimientoPeriodicoPlanDAO movimientoPeriodicoPlanDAO) {
		this.movimientoPeriodicoPlanDAO = movimientoPeriodicoPlanDAO;
	}

	@Override
	public List<MovimientoPeriodicoPlan> getMovimientoDesdeFecha(Date fecha) {
		// TODO Auto-generated method stub
		return movimientoPeriodicoPlanDAO.getMovimientoDesdeFecha(fecha);
	}

	
	

}
