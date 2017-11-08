package es.expensecalculator.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.expensecalculator.dao.IMovimientoPeriodicoPlanImagenDAO;
import es.expensecalculator.model.MovimientoPeriodicoPlanImagen;
import es.expensecalculator.model.Plan;
import es.expensecalculator.web.param.MovimientoPeriodicoPlanImagenParam;

@Transactional(readOnly = true)
public class MovimientoPeriodicoPlanImagenService implements IMovimientoPeriodicoPlanImagenService {

	/** DAO de las cuenta-titular */
	IMovimientoPeriodicoPlanImagenDAO movimientoPeriodicoPlanImagenDAO;

	@Override
	public MovimientoPeriodicoPlanImagen getMovimientoPeriodicoPlanImagenById(long id) {
		return movimientoPeriodicoPlanImagenDAO.getMovimientoPeriodicoPlanImagenById(id);
	}

	@Override
	public List<MovimientoPeriodicoPlanImagen> getMovimientoPeriodicoPlanImagenListWithRestrictions(MovimientoPeriodicoPlanImagenParam filter) {
		return movimientoPeriodicoPlanImagenDAO.getMovimientoPeriodicoPlanImagenListWithRestrictions(filter);
	}

	@Transactional(readOnly = false)
	public void addMovimientoPeriodicoPlanImagen(MovimientoPeriodicoPlanImagen movimiento) {
		movimientoPeriodicoPlanImagenDAO.addMovimientoPeriodicoPlanImagen(movimiento);
	}

	@Transactional(readOnly = false)
	public void actualizarMovimientoPeriodicoPlanImagen(MovimientoPeriodicoPlanImagen movimiento) {
		movimientoPeriodicoPlanImagenDAO.updateMovimientoPeriodicoPlanImagen(movimiento);
	}

	@Transactional(readOnly = false)
	public void borrarMovimientoPeriodicoPlanImagen(MovimientoPeriodicoPlanImagen movimiento) {
		movimientoPeriodicoPlanImagenDAO.deleteMovimientoPeriodicoPlanImagen(movimiento);

	}

	public IMovimientoPeriodicoPlanImagenDAO getMovimientoPeriodicoPlanImagenDAO() {
		return movimientoPeriodicoPlanImagenDAO;
	}

	public void setMovimientoPeriodicoPlanImagenDAO(IMovimientoPeriodicoPlanImagenDAO movimientoPeriodicoPlanImagenDAO) {
		this.movimientoPeriodicoPlanImagenDAO = movimientoPeriodicoPlanImagenDAO;
	}

	@Override
	public List<MovimientoPeriodicoPlanImagen> getMovimientoDesdeFecha(Date fecha) {
		// TODO Auto-generated method stub
		return movimientoPeriodicoPlanImagenDAO.getMovimientoDesdeFecha(fecha);
	}

	@Override
	public List<MovimientoPeriodicoPlanImagen> getMovimientosDesdePlan(Plan planSeleccionadoCombo) {
		// TODO Auto-generated method stub
		return movimientoPeriodicoPlanImagenDAO.getMovimientosDesdePlan(planSeleccionadoCombo);
	}

	
	

}
