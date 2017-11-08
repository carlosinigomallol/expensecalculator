package es.expensecalculator.web.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TablaResultados {

	private Date fecha;
	private BigDecimal saldo;
	private List<String> eventos = new ArrayList<String>();
	
	
	public List<String> getEventos() {
		return eventos;
	}
	public void setEventos(List<String> eventos) {
		this.eventos = eventos;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	
	
}
