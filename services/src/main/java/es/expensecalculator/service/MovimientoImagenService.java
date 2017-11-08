package es.expensecalculator.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.expensecalculator.dao.IMovimientoImagenDAO;
import es.expensecalculator.model.MovimientoImagen;
import es.expensecalculator.model.MovimientoPlanImagen;
import es.expensecalculator.model.Plan;
import es.expensecalculator.model.Usuario;
import es.expensecalculator.web.param.MovimientoImagenParam;

@Transactional(readOnly = true)
public class MovimientoImagenService implements IMovimientoImagenService {

	/** DAO de las cuenta-titular */
	IMovimientoImagenDAO movimientoImagenDAO;

	@Override
	public MovimientoImagen getMovimientoImagenById(long id) {
		return movimientoImagenDAO.getMovimientoImagenById(id);
	}

	@Override
	public List<MovimientoImagen> getMovimientoImagenListWithRestrictions(MovimientoImagenParam filter) {
		return movimientoImagenDAO.getMovimientoImagenListWithRestrictions(filter);
	}

	@Transactional(readOnly = false)
	public void addMovimientoImagen(MovimientoImagen movimientoImagen) {
		movimientoImagenDAO.addMovimientoImagen(movimientoImagen);
	}

	@Transactional(readOnly = false)
	public void actualizarMovimientoImagen(MovimientoImagen movimientoImagen) {
		movimientoImagenDAO.updateMovimientoImagen(movimientoImagen);
	}

	@Transactional(readOnly = false)
	public void borrarMovimientoImagen(MovimientoImagen movimientoImagen) {
		movimientoImagenDAO.deleteMovimientoImagen(movimientoImagen);

	}

	public IMovimientoImagenDAO getMovimientoImagenDAO() {
		return movimientoImagenDAO;
	}

	public void setMovimientoImagenDAO(IMovimientoImagenDAO movimientoImagenDAO) {
		this.movimientoImagenDAO = movimientoImagenDAO;
	}
	
	@Override
	public List<MovimientoImagen> getMovimientoImagenDesdeFecha(Date fecha) {
		// TODO Auto-generated method stub
		return movimientoImagenDAO.getMovimientoImagenDesdeFecha( fecha);
	}

	@Override
	public MovimientoImagen getMovimientoImagenPorPlan(Plan plan) {
		// TODO Auto-generated method stub
		return movimientoImagenDAO.getMovimientoImagenPorPlan(plan);
	}
		

}
