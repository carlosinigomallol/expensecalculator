package es.expensecalculator;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.expensecalculator.model.MovimientoPeriodicoPlan;
import es.expensecalculator.model.Usuario;
import es.expensecalculator.model.enums.Periodicidad;
import es.expensecalculator.model.enums.SignoMovimiento;
import es.expensecalculator.service.IMovimientoPeriodicoPlanService;
import es.expensecalculator.service.IUsuarioService;
import es.expensecalculator.web.param.MovimientoPeriodicoPlanParam;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class MovimientoPeriodicoPlanServiceTest {

	@Autowired
	IMovimientoPeriodicoPlanService movimientoPeriodicoPlanService;

	@Autowired
	IUsuarioService usuarioService;
	
	@Test
	public void testMovimientoPeriodicoPlanService() {

		Long id = null;
		List<MovimientoPeriodicoPlan> lista = null;
		MovimientoPeriodicoPlan movimiento = getMovimientoPeriodicoPlan();
		Usuario usuario = new Usuario();
        usuario.setName("name");
        usuario.setLogin("login");
        usuario.setPassword("password");
        usuarioService.addUsuario(usuario);
        movimiento.setUsuario(usuario);
		MovimientoPeriodicoPlanParam movimientoParam = new MovimientoPeriodicoPlanParam();
		movimientoPeriodicoPlanService.addMovimientoPeriodicoPlan(movimiento);

		lista = movimientoPeriodicoPlanService.getMovimientoPeriodicoPlanListWithRestrictions(movimientoParam);
		for (MovimientoPeriodicoPlan mo : lista) {
			System.out.println(mo);
		}

	}

	private MovimientoPeriodicoPlan getMovimientoPeriodicoPlan() {
		MovimientoPeriodicoPlan movimiento = new MovimientoPeriodicoPlan();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		movimiento.setDescripcion("PAGO HIPOTECA CASA NUEVA");
		movimiento.setCantidad(new BigDecimal("750"));
		movimiento.setDivisa("EUR");
		movimiento.setPeriodidadEjecucion(Periodicidad.MENSUAL.getValue());
		movimiento.setDiaEjecucion(new BigDecimal("5")); // Dia 1 de cada mes
		try {
			movimiento.setFechaInicio(simpleDateFormat.parse("2017-12-30"));
			movimiento.setFechaFin(simpleDateFormat.parse("2042-12-30"));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		movimiento.setTipoMovimiento("HI");
		movimiento.setSignoMovimiento(SignoMovimiento.NEGATIVO.getValue());

		return movimiento;

	}

}
