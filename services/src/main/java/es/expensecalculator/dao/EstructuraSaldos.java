package es.expensecalculator.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class EstructuraSaldos {

	
	private TreeMap<Date, SaldoEventosBean> porDia = new TreeMap<Date, SaldoEventosBean>();
	
	public TreeMap<Date, SaldoEventosBean> getPorDia() {
		return porDia;
	}
	public void setPorDia(TreeMap<Date, SaldoEventosBean> porDia) {
		this.porDia = porDia;
	}
	
	
 	
}
