package es.expensecalculator.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CargadorExcelMovimientosProcess {
	private static final Logger LOGGER = LoggerFactory.getLogger(CargadorExcelMovimientosProcess.class);

	public static void main(String args[]) throws Exception {
		if (args.length != 2) {
			throw new Exception(
					"Se necesita como argumento el path completo del archivo csv de movimientos, tipo fichero I=ING, E=EVO");
		}
		LOGGER.info("Cargando contexto");
		String[] contextos = { "expensecalculator-dbservices-context.xml", "cargador-excel-movimientos.xml" };
		LOGGER.info("Contexto cargado");
		ApplicationContext ctx = new ClassPathXmlApplicationContext(contextos);
		CargadorExcelMovimientosController cargadorExcelMovimientosController = ctx
				.getBean(CargadorExcelMovimientosController.class);
		String pathFicheroMovimientos = args[0];
		LOGGER.info("Cargando fichero " + pathFicheroMovimientos);
		if (args[1].equals("E")) {
			cargadorExcelMovimientosController.cargarExcelEvo(pathFicheroMovimientos);
			LOGGER.info("Fichero cargado " + pathFicheroMovimientos);
		} else if (args[1].equals("I")) {
			cargadorExcelMovimientosController.cargarExcelING(pathFicheroMovimientos);
			LOGGER.info("Fichero cargado " + pathFicheroMovimientos);
		}
	}

}
