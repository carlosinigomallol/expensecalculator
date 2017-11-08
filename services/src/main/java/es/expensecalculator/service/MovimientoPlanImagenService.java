package es.expensecalculator.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.expensecalculator.dao.IMovimientoPlanImagenDAO;
import es.expensecalculator.model.MovimientoPlanImagen;
import es.expensecalculator.model.Plan;
import es.expensecalculator.web.param.MovimientoPlanImagenParam;

@Transactional(readOnly = true)
public class MovimientoPlanImagenService implements IMovimientoPlanImagenService {

	/** DAO de las cuenta-titular */
	IMovimientoPlanImagenDAO movimientoPlanImagenDAO;

	@Override
	public MovimientoPlanImagen getMovimientoPlanImagenById(long id) {
		return movimientoPlanImagenDAO.getMovimientoPlanImagenById(id);
	}

	@Override
	public List<MovimientoPlanImagen> getMovimientoPlanImagenListWithRestrictions(MovimientoPlanImagenParam filter) {
		return movimientoPlanImagenDAO.getMovimientoPlanImagenListWithRestrictions(filter);
	}

	@Transactional(readOnly = false)
	public void addMovimientoPlanImagen(MovimientoPlanImagen movimiento) {
		movimientoPlanImagenDAO.addMovimientoPlanImagen(movimiento);
	}

	@Transactional(readOnly = false)
	public void actualizarMovimientoPlanImagen(MovimientoPlanImagen movimiento) {
		movimientoPlanImagenDAO.updateMovimientoPlanImagen(movimiento);
	}

	@Transactional(readOnly = false)
	public void borrarMovimientoPlanImagen(MovimientoPlanImagen movimiento) {
		movimientoPlanImagenDAO.deleteMovimientoPlanImagen(movimiento);

	}

	public IMovimientoPlanImagenDAO getMovimientoPlanImagenDAO() {
		return movimientoPlanImagenDAO;
	}

	public void setMovimientoPlanImagenDAO(IMovimientoPlanImagenDAO movimientoPlanImagenDAO) {
		this.movimientoPlanImagenDAO = movimientoPlanImagenDAO;
	}

	@Override
	public List<MovimientoPlanImagen> getMovimientoDesdeFecha(Date fecha) {
		// TODO Auto-generated method stub
		return movimientoPlanImagenDAO.getMovimientoDesdeFecha( fecha);
	}

	@Override
	public List<MovimientoPlanImagen> getMovimientosDesdePlan(Plan planSeleccionadoCombo) {
		// TODO Auto-generated method stub
		return movimientoPlanImagenDAO.getMovimientosDesdePlan(planSeleccionadoCombo);
	}
	
	
	

}
