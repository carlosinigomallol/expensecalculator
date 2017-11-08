package es.process;

import org.junit.Test;

import es.expensecalculator.process.CargadorExcelMovimientosProcess;

public class TestCargadorExcelMovimientosProcess {

	@Test
	public void test() throws Exception{

		//String [] args = {"target/test-classes/movimientosEvo.csv", "E"};
		String [] args = {"target/test-classes/movimientosIng.csv", "I"};
		CargadorExcelMovimientosProcess.main(args);
	}
	
}
