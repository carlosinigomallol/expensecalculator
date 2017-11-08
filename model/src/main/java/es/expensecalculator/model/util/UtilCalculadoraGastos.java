package es.expensecalculator.model.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import es.expensecalculator.model.Movimiento;
import es.expensecalculator.model.MovimientoPlan;
import es.expensecalculator.model.Plan;

public class UtilCalculadoraGastos {

	private BigDecimal calcularSaldoActual(Date fechaPeticionCalculoPlan) {
		//Saldo de movimientos bancarios
		BigDecimal saldoActual = BigDecimal.ZERO;
		String sql = "SELECT SUM(CASE m.tipoMovimiento WHEN '00' THEN m.tipoMovimiento WHEN '01' THEN m.tipoMovimiento * (-1) END) From Movimiento as m";
		
		String sql2 = "SELECT SUM(CASE m.tipoMovimiento WHEN '00' THEN m.tipoMovimiento WHEN '01' THEN m.tipoMovimiento * (-1) END) From MovimientoConIva as m";
		
		BigDecimal saldoPorMovimientos = null;
		BigDecimal saldoPorMovimientosConIva = null;
		
		saldoActual = saldoPorMovimientos.add(saldoPorMovimientosConIva);
		
		
		return saldoActual;
	}
	
}
