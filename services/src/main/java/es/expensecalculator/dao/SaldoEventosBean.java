package es.expensecalculator.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SaldoEventosBean {

	private BigDecimal saldo = BigDecimal.ZERO;
	
	private List<String> eventos = new ArrayList<String>();

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public List<String> getEventos() {
		return eventos;
	}

	public void setEventos(List<String> eventos) {
		this.eventos = eventos;
	}
	
	
			
	
}
