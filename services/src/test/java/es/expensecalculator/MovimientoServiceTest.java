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

import es.expensecalculator.model.Movimiento;
import es.expensecalculator.model.enums.SignoMovimiento;
import es.expensecalculator.service.IMovimientoService;
import es.expensecalculator.web.param.MovimientoParam;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class MovimientoServiceTest {

    @Autowired
    IMovimientoService movimientoService;
    
    
    

    @Test
    public void testMovimientoService() {

        Long id = null;
        List<Movimiento> lista = null;
        Movimiento movimiento = getMovimiento();
        MovimientoParam movimientoParam = new MovimientoParam();
        lista = movimientoService.getMovimientoListWithRestrictions(movimientoParam);
        for(Movimiento mo: lista){
        	System.out.println(mo);
        }
        movimientoService.addMovimiento(movimiento);
        lista = movimientoService.getMovimientoListWithRestrictions(movimientoParam);
        for(Movimiento mo: lista){
        	System.out.println(mo);
        }

        
        //movimientoService.borrarMovimiento(movimiento);

    }

    private Movimiento getMovimiento() {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Movimiento movimiento = new Movimiento();
        movimiento.setDescripcion("SALDO INICIAL A 30 DE ABRIL DE 2017 MARIA");
        movimiento.setTotalMovimiento(new BigDecimal("3503.34"));
        movimiento.setDivisa("EUR");
        try {
			movimiento.setFechaInicio(simpleDateFormat.parse("2017-04-30"));
			movimiento.setFechaFin(simpleDateFormat.parse("2017-04-30"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        movimiento.setTipoMovimiento("SI");
        movimiento.setSignoMovimiento(SignoMovimiento.POSITIVO.getValue());

        return movimiento;

    }

}
