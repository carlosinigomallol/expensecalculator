package es.expensecalculator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.expensecalculator.dao.EstructuraSaldos;
import es.expensecalculator.dao.SaldoEventosBean;
import es.expensecalculator.model.MovimientoPeriodicoPlan;
import es.expensecalculator.model.MovimientoPlan;
import es.expensecalculator.model.enums.TipoMovimiento;
import es.expensecalculator.service.ICalculadoraPlanService;
import es.expensecalculator.service.IMovimientoPeriodicoPlanService;
import es.expensecalculator.service.IMovimientoPlanService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class CalculadoraPlanServiceTest {

	@Autowired
	ICalculadoraPlanService calculadoraPlanService;
	
	@Autowired
	IMovimientoPlanService movimientoPlanService;
	
	@Autowired
	IMovimientoPeriodicoPlanService movimientoPeriodicoPlanService;

	
	@Test
	public void testConMovimientosPlan () throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaPeticion = simpleDateFormat.parse("2017-06-01");
		
		GregorianCalendar gregorianCalendarFin = new GregorianCalendar();
		gregorianCalendarFin.setTime(fechaPeticion);
		gregorianCalendarFin.add(GregorianCalendar.YEAR, 3); // Se suman
		
		
		TipoMovimiento tipoMovimiento = TipoMovimiento.NOMINA;
		
		System.out.println("RECOGIENDO LOS MOVIMIENTOS QUE SE VAN A TENER EN CUENTA PARA EL PLAN. DEBERAN SER INTRODUCIDOS MANUALMENTE EN LA WEB O TELEFONO");
		List<MovimientoPlan> movimientoPlans = movimientoPlanService.getMovimientoDesdeFecha(fechaPeticion);
		List<MovimientoPeriodicoPlan> movimientoPeriodicoPlans = movimientoPeriodicoPlanService.getMovimientoDesdeFecha(fechaPeticion);
		
		System.out.println("MOVIMIENTOS A TRATAR DE PLAN");
		for(MovimientoPlan m: movimientoPlans){
			
			System.out.println(m);
		}
		System.out.println();
		System.out.println("MOVIMIENTOS PERIODICOS A TRATAR DE PLAN");
		for(MovimientoPeriodicoPlan m: movimientoPeriodicoPlans){
			System.out.println(m);
		}
		System.out.println();
		
		System.out.println("***** CALCULANDO SALDOS POR DIA *****");
		
//		EstructuraSaldos  estructuraSaldos =  calculadoraPlanService.calculadoraTotalGastosPlanes( movimientoPlans, 
//				fechaPeticion, tipoMovimiento, movimientoPeriodicoPlans,null);
//				Map<Date, SaldoEventosBean> porDia = estructuraSaldos.getPorDia();
//				for(Date dia: porDia.keySet()){
//					System.out.println("Fecha: " + simpleDateFormat.format(dia) +  " " + porDia.get(dia));
//				}
	}

	// @Test
	// public void test(){
	// System.out.println(calculadoraPlanService.calcularSaldoActual(new
	// Date(System.currentTimeMillis())));
	// }

}
