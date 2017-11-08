package es.expensecalculator.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.expensecalculator.dao.IMovimientoDAO;
import es.expensecalculator.model.Movimiento;
import es.expensecalculator.model.MovimientoPlan;
import es.expensecalculator.model.Usuario;
import es.expensecalculator.web.param.MovimientoParam;

@Transactional(readOnly = true)
public class MovimientoService implements IMovimientoService {

	/** DAO de las cuenta-titular */
	IMovimientoDAO movimientoDAO;

	@Override
	public Movimiento getMovimientoById(long id) {
		return movimientoDAO.getMovimientoById(id);
	}

	@Override
	public List<Movimiento> getMovimientoListWithRestrictions(MovimientoParam filter) {
		return movimientoDAO.getMovimientoListWithRestrictions(filter);
	}

	@Transactional(readOnly = false)
	public void addMovimiento(Movimiento movimiento) {
		movimientoDAO.addMovimiento(movimiento);
	}

	@Transactional(readOnly = false)
	public void actualizarMovimiento(Movimiento movimiento) {
		movimientoDAO.updateMovimiento(movimiento);
	}

	@Transactional(readOnly = false)
	public void borrarMovimiento(Movimiento movimiento) {
		movimientoDAO.deleteMovimiento(movimiento);

	}

	public IMovimientoDAO getMovimientoDAO() {
		return movimientoDAO;
	}

	public void setMovimientoDAO(IMovimientoDAO movimientoDAO) {
		this.movimientoDAO = movimientoDAO;
	}
	
	@Override
	public List<Movimiento> getMovimientoDesdeFecha(Date fecha) {
		// TODO Auto-generated method stub
		return movimientoDAO.getMovimientoDesdeFecha( fecha);
	}

	@Override
	public Movimiento getMovimientoPorUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return movimientoDAO.getMovimientoPorUsuario(usuario);
	}
		

}
