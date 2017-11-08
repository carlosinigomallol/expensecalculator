package es.expensecalculator.model;

import java.math.BigDecimal;
import java.util.Date;

public class MovimientoPeriodicoPlanBean {

	private Long id;
	private String descripcion;
	private String divisa;
	private BigDecimal diaEjecucion;
	private String periodidadEjecucion;
	private BigDecimal cantidad;
	private Date fechaInicio;
	private Date fechaFin;
	private String tipoMovimiento;
	private String signoMovimiento;
	private TipoEntorno tipoentorno;

	public TipoEntorno getTipoentorno() {
		return tipoentorno;
	}

	public void setTipoentorno(TipoEntorno tipoentorno) {
		this.tipoentorno = tipoentorno;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDivisa() {
		return divisa;
	}

	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public String getSignoMovimiento() {
		return signoMovimiento;
	}

	public void setSignoMovimiento(String signoMovimiento) {
		this.signoMovimiento = signoMovimiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPeriodidadEjecucion() {
		return periodidadEjecucion;
	}

	public void setPeriodidadEjecucion(String periodidadEjecucion) {
		this.periodidadEjecucion = periodidadEjecucion;
	}

	public BigDecimal getDiaEjecucion() {
		return diaEjecucion;
	}

	public void setDiaEjecucion(BigDecimal diaEjecucion) {
		this.diaEjecucion = diaEjecucion;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidad == null) ? 0 : cantidad.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((diaEjecucion == null) ? 0 : diaEjecucion.hashCode());
		result = prime * result + ((divisa == null) ? 0 : divisa.hashCode());
		result = prime * result + ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = prime * result + ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((periodidadEjecucion == null) ? 0 : periodidadEjecucion.hashCode());
		result = prime * result + ((signoMovimiento == null) ? 0 : signoMovimiento.hashCode());
		result = prime * result + ((tipoMovimiento == null) ? 0 : tipoMovimiento.hashCode());
		result = prime * result + ((tipoentorno == null) ? 0 : tipoentorno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovimientoPeriodicoPlanBean other = (MovimientoPeriodicoPlanBean) obj;
		if (cantidad == null) {
			if (other.cantidad != null)
				return false;
		} else if (!cantidad.equals(other.cantidad))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (diaEjecucion == null) {
			if (other.diaEjecucion != null)
				return false;
		} else if (!diaEjecucion.equals(other.diaEjecucion))
			return false;
		if (divisa == null) {
			if (other.divisa != null)
				return false;
		} else if (!divisa.equals(other.divisa))
			return false;
		if (fechaFin == null) {
			if (other.fechaFin != null)
				return false;
		} else if (!fechaFin.equals(other.fechaFin))
			return false;
		if (fechaInicio == null) {
			if (other.fechaInicio != null)
				return false;
		} else if (!fechaInicio.equals(other.fechaInicio))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (periodidadEjecucion == null) {
			if (other.periodidadEjecucion != null)
				return false;
		} else if (!periodidadEjecucion.equals(other.periodidadEjecucion))
			return false;
		if (signoMovimiento == null) {
			if (other.signoMovimiento != null)
				return false;
		} else if (!signoMovimiento.equals(other.signoMovimiento))
			return false;
		if (tipoMovimiento == null) {
			if (other.tipoMovimiento != null)
				return false;
		} else if (!tipoMovimiento.equals(other.tipoMovimiento))
			return false;
		if (tipoentorno != other.tipoentorno)
			return false;
		return true;
	}

	public void desdeOtroMovimientoPeriodicoPlan(MovimientoPeriodicoPlanBean mov) {
		this.descripcion = mov.getDescripcion();
		this.cantidad = mov.getCantidad();
		this.divisa = mov.getDivisa();
		this.fechaInicio = mov.getFechaInicio();
		this.fechaFin = mov.getFechaFin();
		this.tipoMovimiento = mov.getTipoMovimiento();
		this.signoMovimiento = mov.getSignoMovimiento();
		this.diaEjecucion = mov.getDiaEjecucion();
		this.periodidadEjecucion = mov.getPeriodidadEjecucion();
	}

}
