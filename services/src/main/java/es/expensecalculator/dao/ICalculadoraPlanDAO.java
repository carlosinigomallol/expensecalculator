package es.expensecalculator.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import es.expensecalculator.model.MovimientoBean;
import es.expensecalculator.model.MovimientoPeriodicoPlan;
import es.expensecalculator.model.MovimientoPeriodicoPlanBean;
import es.expensecalculator.model.MovimientoPlan;
import es.expensecalculator.model.MovimientoPlanBean;
import es.expensecalculator.model.Usuario;
import es.expensecalculator.model.enums.TipoMovimiento;

public interface ICalculadoraPlanDAO {

	
	public EstructuraSaldos calculadoraTotalGastosPlanes(List<MovimientoPlanBean> movimientoPlans,
			Date fechaPeticionCalculoPlan, TipoMovimiento tipoMovimiento,
			List<MovimientoPeriodicoPlanBean> movimientoPeriodicoPlans, Usuario usuario, MovimientoBean movimientoBean);
}
