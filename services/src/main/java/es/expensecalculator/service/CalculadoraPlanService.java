package es.expensecalculator.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.expensecalculator.dao.EstructuraSaldos;
import es.expensecalculator.dao.ICalculadoraPlanDAO;
import es.expensecalculator.model.MovimientoBean;
import es.expensecalculator.model.MovimientoPeriodicoPlan;
import es.expensecalculator.model.MovimientoPeriodicoPlanBean;
import es.expensecalculator.model.MovimientoPlan;
import es.expensecalculator.model.MovimientoPlanBean;
import es.expensecalculator.model.Usuario;
import es.expensecalculator.model.enums.TipoMovimiento;

@Transactional(readOnly = true)
public class CalculadoraPlanService implements ICalculadoraPlanService {

	private ICalculadoraPlanDAO calculadoraPlanDAO;

	public ICalculadoraPlanDAO getCalculadoraPlanDAO() {
		return calculadoraPlanDAO;
	}

	public void setCalculadoraPlanDAO(ICalculadoraPlanDAO calculadoraPlanDAO) {
		this.calculadoraPlanDAO = calculadoraPlanDAO;
	}

	

	@Override
	public EstructuraSaldos calculadoraTotalGastosPlanes(List<MovimientoPlanBean> movimientoPlans, 
			Date fechaPeticionCalculoPlan, TipoMovimiento tipoMovimiento, List<MovimientoPeriodicoPlanBean> movimientoPeriodicoPlans, Usuario usuario, MovimientoBean movimientoBean) {
		// TODO Auto-generated method stub
		return calculadoraPlanDAO.calculadoraTotalGastosPlanes(
				 movimientoPlans,  fechaPeticionCalculoPlan,tipoMovimiento, movimientoPeriodicoPlans, usuario, movimientoBean);
	}

}
