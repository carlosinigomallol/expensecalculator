package es.expensecalculator.web.param;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Contenedor de los datos para los parámetros del formulario
 */
public class MovimientoImagenParam extends ParamCommun implements EntityParam<MovimientoImagenParam>, Serializable {

	/**
	 * Generador por el IDE
	 */
	private static final long serialVersionUID = 5107792745323596286L;

	private String descripcion;
	private BigDecimal totalMovimiento;
	private String divisa;
	private Date fechaInicio;
	private Date fechaFin;
	private String tipoMovimiento;
	private String signoMovimiento;

	/**
	 * Constructor por defecto
	 */
	public MovimientoImagenParam() {
		super();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getTotalMovimiento() {
		return totalMovimiento;
	}

	public void setTotalMovimiento(BigDecimal totalMovimiento) {
		this.totalMovimiento = totalMovimiento;
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
	public MovimientoImagenParam getEntityParam() {
		return this;
	}

}
