package es.expensecalculator.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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

/**
 * ORM para la tabla entidad Representa a la entidad declarante del modelo 290
 */
@Entity
@Table(name = "movimiento")
public class Movimiento implements Serializable {

	/** Clave primaria */
	private Long id;
	private String descripcion;
	private BigDecimal totalMovimiento;
	private String divisa;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaInicio", length = 23, nullable = false)
	@NotNull
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaFin", length = 23, nullable = false)
	@NotNull
	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = "totalMovimiento", precision = 18, scale = 2, nullable = false)
	@NotNull
	public BigDecimal getTotalMovimiento() {
		return totalMovimiento;
	}

	public void setTotalMovimiento(BigDecimal totalMovimiento) {
		this.totalMovimiento = totalMovimiento;
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

	/**
	 * Constructor por defecto
	 */
	public Movimiento() {
		super();
	}

	/**
	 * @return Clave primaria
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MODEL_GEN")
	@TableGenerator(name = "MODEL_GEN", table = "hmovimiento", pkColumnName = "sequence_name", valueColumnName = "sequence_next_hi_value", pkColumnValue = "movimiento", allocationSize = 1)
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            Clave primaria
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", descripcion=" + descripcion + ", totalMovimiento=" + totalMovimiento
				+ ", divisa=" + divisa + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", tipoMovimiento="
				+ tipoMovimiento + ", signoMovimiento=" + signoMovimiento + "]";
	}

}
