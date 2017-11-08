package es.expensecalculator.model.enums;

public enum SignoMovimiento {

	
	POSITIVO("AA"),
	NEGATIVO("BB");
	
	private String value;
	
	SignoMovimiento(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
