package es.expensecalculator;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.expensecalculator.model.MovimientoPlan;
import es.expensecalculator.model.Usuario;
import es.expensecalculator.model.enums.SignoMovimiento;
import es.expensecalculator.service.IMovimientoPlanService;
import es.expensecalculator.service.IUsuarioService;
import es.expensecalculator.web.param.MovimientoPlanParam;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class MovimientoPlanServiceTest {

	@Autowired
	IMovimientoPlanService movimientoService;
	
	@Autowired
	IUsuarioService usuarioService;


	@Test
	public void testMovimientoService() {
		List<MovimientoPlan> lista = null;
		MovimientoPlan movimiento = getMovimientoPlan();
		Usuario usuario = new Usuario();
        usuario.setName("name");
        usuario.setLogin("login");
        usuario.setPassword("password");
        usuarioService.addUsuario(usuario);
        movimiento.setUsuario(usuario);
		MovimientoPlanParam movimientoParam = new MovimientoPlanParam();
		movimientoService.addMovimientoPlan(movimiento);
		lista = movimientoService.getMovimientoPlanListWithRestrictions(movimientoParam);
		for (MovimientoPlan mo : lista) {
			System.out.println(mo);
		}
		//movimientoService.borrarMovimientoPlan(movimiento);
	}

	private MovimientoPlan getMovimientoPlan() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		MovimientoPlan movimiento = new MovimientoPlan();
		movimiento.setDescripcion("DESPEDIDA MARCOS ");
		movimiento.setTotalMovimiento(new BigDecimal("300"));
		movimiento.setDivisa("EUR");
		try {
			movimiento.setFechaInicio(simpleDateFormat.parse("2017-08-12"));
			movimiento.setFechaFin(simpleDateFormat.parse("2017-08-12"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		movimiento.setTipoMovimiento("DP"); // Nomina
		movimiento.setSignoMovimiento(SignoMovimiento.NEGATIVO.getValue());
		return movimiento;
	}

}
