package es.expensecalculator.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ORM para la tabla entidad Representa a la entidad declarante del modelo 290
 */
public class MovimientoPlanBean implements Serializable {

	private Long id;
	private String descripcion;
	private BigDecimal totalMovimiento;
	private String divisa;
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

	public BigDecimal getTotalMovimiento() {
		return totalMovimiento;
	}

	public void setTotalMovimiento(BigDecimal totalMovimiento) {
		this.totalMovimiento = totalMovimiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	
	public void desdeOtroMovimientoPlan(MovimientoPlanBean mov) {
		this.descripcion = mov.getDescripcion();
		this.totalMovimiento = mov.getTotalMovimiento();
		this.divisa = mov.getDivisa();
		this.fechaInicio = mov.getFechaInicio();
		this.fechaFin = mov.getFechaFin();
		this.tipoMovimiento = mov.getTipoMovimiento();
		this.signoMovimiento = mov.getSignoMovimiento();
		this.tipoentorno = mov.getTipoentorno();
	}

	/**
	 * Constructor por defecto
	 */
	public MovimientoPlanBean() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((divisa == null) ? 0 : divisa.hashCode());
		result = prime * result + ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = prime * result + ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((signoMovimiento == null) ? 0 : signoMovimiento.hashCode());
		result = prime * result + ((tipoMovimiento == null) ? 0 : tipoMovimiento.hashCode());
		result = prime * result + ((tipoentorno == null) ? 0 : tipoentorno.hashCode());
		result = prime * result + ((totalMovimiento == null) ? 0 : totalMovimiento.hashCode());
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
		MovimientoPlanBean other = (MovimientoPlanBean) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
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
		if (totalMovimiento == null) {
			if (other.totalMovimiento != null)
				return false;
		} else if (!totalMovimiento.equals(other.totalMovimiento))
			return false;
		return true;
	}
	
	
	
}
