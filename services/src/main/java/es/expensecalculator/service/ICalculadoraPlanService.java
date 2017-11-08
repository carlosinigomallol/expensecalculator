package es.expensecalculator.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import es.expensecalculator.dao.EstructuraSaldos;
import es.expensecalculator.model.MovimientoBean;
import es.expensecalculator.model.MovimientoPeriodicoPlan;
import es.expensecalculator.model.MovimientoPeriodicoPlanBean;
import es.expensecalculator.model.MovimientoPlan;
import es.expensecalculator.model.MovimientoPlanBean;
import es.expensecalculator.model.Usuario;
import es.expensecalculator.model.enums.TipoMovimiento;

public interface ICalculadoraPlanService {

	
	public BigDecimal calcularSaldoActual(Date fechaPeticionCalculoPlan, Usuario usuario);
	public EstructuraSaldos calculadoraTotalGastosPlanes(
			List<MovimientoPlanBean> movimientoPlans,
			Date fechaPeticionCalculoPlan, TipoMovimiento tipoMovimiento, List<MovimientoPeriodicoPlanBean> movimientoPeriodicoPlans, Usuario usuario, MovimientoBean movimientoBean);
}
