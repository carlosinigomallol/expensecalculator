package es.expensecalculator.web.param;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import es.expensecalculator.model.Usuario;

/**
 * Contenedor de los datos para los parámetros del formulario
 */
public class MovimientoPeriodicoPlanImagenParam extends ParamCommun
		implements EntityParam<MovimientoPeriodicoPlanImagenParam>, Serializable {

	/**
	 * Generador por el IDE
	 */
	private static final long serialVersionUID = 5107792745323596286L;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * Constructor por defecto
	 */
	public MovimientoPeriodicoPlanImagenParam() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDivisa() {
		return divisa;
	}

	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	public BigDecimal getDiaEjecucion() {
		return diaEjecucion;
	}

	public void setDiaEjecucion(BigDecimal diaEjecucion) {
		this.diaEjecucion = diaEjecucion;
	}

	public String getPeriodidadEjecucion() {
		return periodidadEjecucion;
	}

	public void setPeriodidadEjecucion(String periodidadEjecucion) {
		this.periodidadEjecucion = periodidadEjecucion;
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

	@Override
	public MovimientoPeriodicoPlanImagenParam getEntityParam() {
		return this;
	}

}
