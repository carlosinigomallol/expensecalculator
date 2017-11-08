package es.expensecalculator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * ORM para la tabla entidad Representa a la entidad declarante del modelo 290
 */
@Entity
@Table(name = "plan")
public class Plan implements Serializable {
	/** Clave primaria */
	private Long id;
	private String descripcionPlan;
	private Usuario usuario;

	private List<MovimientoImagen> movimientoImagens = new ArrayList<MovimientoImagen>();
	private List<MovimientoPeriodicoPlanImagen> movimientoPeriodicoPlanImagens = new ArrayList<MovimientoPeriodicoPlanImagen>();
	private List<MovimientoPlanImagen> movimientoPlanImagens = new ArrayList<MovimientoPlanImagen>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuarioid")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "plan")
	public List<MovimientoImagen> getMovimientoImagens() {
		return movimientoImagens;
	}

	public void setMovimientoImagens(List<MovimientoImagen> movimientoImagens) {
		this.movimientoImagens = movimientoImagens;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "plan")
	public List<MovimientoPeriodicoPlanImagen> getMovimientoPeriodicoPlanImagens() {
		return movimientoPeriodicoPlanImagens;
	}

	public void setMovimientoPeriodicoPlanImagens(List<MovimientoPeriodicoPlanImagen> movimientoPeriodicoPlanImagens) {
		this.movimientoPeriodicoPlanImagens = movimientoPeriodicoPlanImagens;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "plan")
	public List<MovimientoPlanImagen> getMovimientoPlanImagens() {
		return movimientoPlanImagens;
	}

	public void setMovimientoPlanImagens(List<MovimientoPlanImagen> movimientoPlanImagens) {
		this.movimientoPlanImagens = movimientoPlanImagens;
	}

	/**
	 * Constructor por defecto
	 */
	public Plan() {
		super();
	}

	/**
	 * @return Clave primaria
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MODEL_GEN")
	@TableGenerator(name = "MODEL_GEN", table = "hplan", pkColumnName = "sequence_name", valueColumnName = "sequence_next_hi_value", pkColumnValue = "plan", allocationSize = 1)
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

	@Column(name = "descripcionPlan", length = 255, nullable = false)
	@Length(max = 255)
	@NotNull
	public String getDescripcionPlan() {
		return descripcionPlan;
	}

	public void setDescripcionPlan(String descripcionPlan) {
		this.descripcionPlan = descripcionPlan;
	}

	@Override
	public String toString() {
		return "Plan [id=" + id + ", descripcionPlan=" + descripcionPlan + "]";
	}

}
