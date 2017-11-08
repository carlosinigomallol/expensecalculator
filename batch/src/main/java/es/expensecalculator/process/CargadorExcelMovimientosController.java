package es.expensecalculator.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.expensecalculator.model.Movimiento;
import es.expensecalculator.model.enums.SignoMovimiento;
import es.expensecalculator.service.IMovimientoService;

public class CargadorExcelMovimientosController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CargadorExcelMovimientosController.class);
	private IMovimientoService movimientoService;
	
	public IMovimientoService getMovimientoService() {
		return movimientoService;
	}

	public void setMovimientoService(IMovimientoService movimientoService) {
		this.movimientoService = movimientoService;
	}
	
	public void cargarExcelING(String pathFicheroMovimientos) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(pathFicheroMovimientos);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			while ((linea = br.readLine()) != null)
				if (Character.isDigit(linea.charAt(0))) {
					Movimiento movimiento = getMovimientoING(linea);
					if(movimiento!=null){
						LOGGER.info("Cargando movimiento ..." + movimiento.toString());
						cargarMovimiento(movimiento);
						LOGGER.info("Movimiento cargado");
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
		
	}

	

	public void cargarExcelEvo(String pathFicheroMovimientos) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(pathFicheroMovimientos);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			while ((linea = br.readLine()) != null)
				if (!linea.contains("Fecha ctble")) {
					Movimiento movimiento = getMovimiento(linea);
					if(movimiento!=null){
						LOGGER.info("Cargando movimiento ..." + movimiento.toString());
						cargarMovimiento(movimiento);
						LOGGER.info("Movimiento cargado");
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private void cargarMovimiento(Movimiento movimiento) {
		movimientoService.addMovimiento(movimiento);
	}

	
	private Movimiento getMovimientoING(String linea) throws ParseException {
		String campos[] = linea.split("#");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String fechaValor = campos[0];
		String concepto = campos[1];
		String cantidad = campos[4];
		String divisa = "EUR";

		Movimiento movimiento = new Movimiento();
		movimiento.setDescripcion(concepto);
		movimiento.setDivisa(divisa);
		movimiento.setFechaFin(simpleDateFormat.parse(fechaValor));
		movimiento.setFechaInicio(simpleDateFormat.parse(fechaValor));
		cantidad=cantidad.replaceAll("\\.", "");
		BigDecimal totalMovimiento = new BigDecimal(cantidad.replaceAll(",", "\\."));
		if (totalMovimiento.compareTo(BigDecimal.ZERO) >= 0) {
			movimiento.setSignoMovimiento(SignoMovimiento.POSITIVO.getValue());
		} else {
			movimiento.setSignoMovimiento(SignoMovimiento.NEGATIVO.getValue());
		}
		movimiento.setTipoMovimiento("MO");
		movimiento.setTotalMovimiento(totalMovimiento.abs());

		return movimiento;
	}
	
	private Movimiento getMovimiento(String linea) throws ParseException {
		String campos[] = linea.split(";");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String fechaValor = campos[1];
		String concepto = campos[2];
		String cantidad = campos[3];
		String divisa = campos[4];

		Movimiento movimiento = new Movimiento();
		movimiento.setDescripcion(concepto);
		movimiento.setDivisa(divisa);
		movimiento.setFechaFin(simpleDateFormat.parse(fechaValor));
		movimiento.setFechaInicio(simpleDateFormat.parse(fechaValor));
		BigDecimal totalMovimiento = new BigDecimal(cantidad.replaceAll(",", "\\."));
		if (totalMovimiento.compareTo(BigDecimal.ZERO) >= 0) {
			movimiento.setSignoMovimiento(SignoMovimiento.POSITIVO.getValue());
		} else {
			movimiento.setSignoMovimiento(SignoMovimiento.NEGATIVO.getValue());
		}
		movimiento.setTipoMovimiento("MO");
		movimiento.setTotalMovimiento(totalMovimiento.abs());

		return movimiento;
	}

	

}
