package es.expensecalculator;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.expensecalculator.dao.EstructuraSaldos;
import es.expensecalculator.dao.SaldoEventosBean;
import es.expensecalculator.model.MovimientoBean;
import es.expensecalculator.model.MovimientoPeriodicoPlanBean;
import es.expensecalculator.model.MovimientoPlanBean;
import es.expensecalculator.model.TipoEntorno;
import es.expensecalculator.model.Usuario;
import es.expensecalculator.model.enums.Periodicidad;
import es.expensecalculator.model.enums.SignoMovimiento;
import es.expensecalculator.model.enums.TipoMovimiento;
import es.expensecalculator.service.ICalculadoraPlanService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class CalculadoraPlanServiceMockTest {
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private ICalculadoraPlanService calc; 
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void test() throws Exception {
		
		List<MovimientoPlanBean> movimientoPlans = new ArrayList<MovimientoPlanBean>();
		MovimientoPlanBean movimientoPlanBean = new MovimientoPlanBean();
		movimientoPlanBean.setDescripcion("Movimiento a fecha 1");
		movimientoPlanBean.setDivisa("EUR");
		movimientoPlanBean.setFechaFin(simpleDateFormat.parse("2017-12-01"));
		movimientoPlanBean.setFechaInicio(simpleDateFormat.parse("2017-12-01"));
		movimientoPlanBean.setId(Long.valueOf("2"));
		movimientoPlanBean.setSignoMovimiento(SignoMovimiento.NEGATIVO.getValue());
		movimientoPlanBean.setTipoentorno(TipoEntorno.MOVIMIENTO);
		movimientoPlanBean.setTipoMovimiento(TipoMovimiento.MOVIMIENTO_A_FECHA.getValue());
		movimientoPlanBean.setTotalMovimiento(new BigDecimal("50"));
		movimientoPlans.add(movimientoPlanBean);
		List<MovimientoPeriodicoPlanBean> movimientoPeriodicoPlans = new ArrayList<MovimientoPeriodicoPlanBean>();
		MovimientoPeriodicoPlanBean movimientoPeriodicoPlanBean = new MovimientoPeriodicoPlanBean();
		movimientoPeriodicoPlanBean.setCantidad(new BigDecimal("100"));
		movimientoPeriodicoPlanBean.setDescripcion("Movimiento periodico 1");
		movimientoPeriodicoPlanBean.setDiaEjecucion(new BigDecimal("2"));
		movimientoPeriodicoPlanBean.setDivisa("EUR");
		movimientoPeriodicoPlanBean.setFechaFin(simpleDateFormat.parse("2019-06-01"));
		movimientoPeriodicoPlanBean.setFechaInicio(simpleDateFormat.parse("2017-06-01"));
		movimientoPeriodicoPlanBean.setId(Long.valueOf("1"));
		movimientoPeriodicoPlanBean.setPeriodidadEjecucion(Periodicidad.MENSUAL.getValue());
		movimientoPeriodicoPlanBean.setSignoMovimiento(SignoMovimiento.NEGATIVO.getValue());
		movimientoPeriodicoPlanBean.setTipoentorno(TipoEntorno.PLAN);
		movimientoPeriodicoPlanBean.setTipoMovimiento(TipoMovimiento.MOVIMIENTO_PERIODICO.getValue());
		movimientoPeriodicoPlans.add(movimientoPeriodicoPlanBean);
		Date fechaPeticionCalculoPlan = simpleDateFormat.parse("2017-11-10");
		TipoMovimiento tipoMovimiento = TipoMovimiento.NOMINA;
		Usuario usuario = new Usuario();
		MovimientoBean movimientoBean = new MovimientoBean();
		movimientoBean.setDescripcion("Saldo inicial");
		movimientoBean.setDivisa("EUR");
		
		movimientoBean.setFechaFin(simpleDateFormat.parse("2017-01-01"));
		movimientoBean.setFechaInicio(simpleDateFormat.parse("2017-01-01"));
		movimientoBean.setId(Long.valueOf("-1"));
		movimientoBean.setSignoMovimiento(SignoMovimiento.POSITIVO.getValue());
		movimientoBean.setTipoentorno(TipoEntorno.PLAN);
		movimientoBean.setTipoMovimiento(TipoMovimiento.SALDO_INICIAL.getValue());
		movimientoBean.setTotalMovimiento(new BigDecimal("12000"));
		
		
		EstructuraSaldos estructuraSaldos = calc.calculadoraTotalGastosPlanes(movimientoPlans, fechaPeticionCalculoPlan, tipoMovimiento, movimientoPeriodicoPlans, usuario, movimientoBean);
		TreeMap<Date, SaldoEventosBean> porDia  = estructuraSaldos.getPorDia();
		Assert.assertTrue(!porDia.isEmpty() && porDia.size()>0);
		Assert.assertTrue(!porDia.isEmpty() && porDia.size()>0);
		Assert.assertEquals(porDia.get(fechaPeticionCalculoPlan).getSaldo(), new BigDecimal("12000"));
		Assert.assertEquals(porDia.get(simpleDateFormat.parse("2020-01-01")).getSaldo(), new BigDecimal("10150"));
	}

}
