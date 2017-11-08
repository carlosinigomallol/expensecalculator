package es.expensecalculator.model.enums;

public enum Periodicidad {

	
	DIARIA("AA"),
	MENSUAL("BB"),
	TRIMESTRAL("CC"),
	ANUAL("DD");
	
	private String value;
	
	Periodicidad(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	public static Periodicidad fromValue(String value) {
        for (Periodicidad s : Periodicidad.values()) {
            if (s.value.equals(value)) {
                return s;
            }
        }
        return null;
    }
	
}
