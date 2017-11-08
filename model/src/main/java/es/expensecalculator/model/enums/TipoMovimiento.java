package es.expensecalculator.model.enums;

public enum TipoMovimiento {

	NOMINA("NO"), // Esto nunca sale de movimientoPlan con valor 1654.15 y se tiene que tener en
					// cuenta todos los meses para el calculo
	//
	PARO("PA"), // Esto nunca sale de movimientoPlan con valor 1400 por el paro y se tiene que tener en
	// cuenta todos los meses para el calculo
	 MOVIMIENTO_A_FECHA("MO") // MOVIMIENTO CARGADO EN BASE DE
	, MOVIMIENTO_PERIODICO("MP"), // MOVIMIENTO PERIODICO CARGADO EN BASE DE
	//SALDO INICIAL
	SALDO_INICIAL("SI");

	private String value;

	TipoMovimiento(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
