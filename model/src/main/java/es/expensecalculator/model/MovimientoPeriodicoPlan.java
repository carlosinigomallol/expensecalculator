package es.expensecalculator.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "movimientoPeriodicoPlan")
public class MovimientoPeriodicoPlan {

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
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuarioid", nullable = false)
	@NotNull
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Column(name = "divisa", length = 3, nullable = false)
	@Length(max = 3)
	@NotNull
	public String getDivisa() {
		return divisa;
	}

	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	@Column(name = "tipoMovimiento", length = 2, nullable = false)
	@Length(max = 2)
	@NotNull
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	@Column(name = "signoMovimiento", length = 2, nullable = false)
	@Length(max = 2)
	@NotNull
	public String getSignoMovimiento() {
		return signoMovimiento;
	}

	public void setSignoMovimiento(String signoMovimiento) {
		this.signoMovimiento = signoMovimiento;
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MODEL_GEN")
	@TableGenerator(name = "MODEL_GEN", table = "hmovimientoPeriodicoPlan", pkColumnName = "sequence_name", valueColumnName = "sequence_next_hi_value", pkColumnValue = "movimientoPeriodicoPlan", allocationSize = 1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "descripcion", length = 255, nullable = false)
	@Length(max = 255)
	@NotNull
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "periodidadEjecucion", length = 2, nullable = false)
	@Length(max = 2)
	@NotNull
	public String getPeriodidadEjecucion() {
		return periodidadEjecucion;
	}

	public void setPeriodidadEjecucion(String periodidadEjecucion) {
		this.periodidadEjecucion = periodidadEjecucion;
	}

	@Column(name = "diaEjecucion", precision = 18, scale = 2, nullable = false)
	@NotNull
	public BigDecimal getDiaEjecucion() {
		return diaEjecucion;
	}

	public void setDiaEjecucion(BigDecimal diaEjecucion) {
		this.diaEjecucion = diaEjecucion;
	}

	@Column(name = "cantidad", precision = 18, scale = 2, nullable = false)
	@NotNull
	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaInicio", length = 23)
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaFin", length = 23)
	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Override
	public String toString() {
		return "MovimientoPeriodicoPlan [id=" + id + ", descripcion=" + descripcion + ", divisa=" + divisa
				+ ", diaEjecucion=" + diaEjecucion + ", periodidadEjecucion=" + periodidadEjecucion + ", cantidad="
				+ cantidad + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", tipoMovimiento="
				+ tipoMovimiento + ", signoMovimiento=" + signoMovimiento + "]";
	}

	public void desdeOtroMovimientoPeriodicoPlan(MovimientoPeriodicoPlan mov) {
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
