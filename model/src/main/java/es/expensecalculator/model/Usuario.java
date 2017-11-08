package es.expensecalculator.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "usuario")
public class Usuario implements Serializable {

	/** Clave primaria */
	private Long id;
	private String name;
	private String login;
	private String password;
	private List<Movimiento> movimientos = new ArrayList<Movimiento>();
	private List<MovimientoPlan> movimientoPlans = new ArrayList<MovimientoPlan>();
	private List<MovimientoPeriodicoPlan> movimientoPeriodicoPlans = new ArrayList<MovimientoPeriodicoPlan>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
	public List<MovimientoPlan> getMovimientoPlans() {
		return movimientoPlans;
	}

	public void setMovimientoPlans(List<MovimientoPlan> movimientoPlans) {
		this.movimientoPlans = movimientoPlans;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
	public List<MovimientoPeriodicoPlan> getMovimientoPeriodicoPlans() {
		return movimientoPeriodicoPlans;
	}

	public void setMovimientoPeriodicoPlans(List<MovimientoPeriodicoPlan> movimientoPeriodicoPlans) {
		this.movimientoPeriodicoPlans = movimientoPeriodicoPlans;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	/**
	 * Constructor por defecto
	 */
	public Usuario() {
		super();
	}

	/**
	 * @return Clave primaria
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MODEL_GEN")
	@TableGenerator(name = "MODEL_GEN", table = "husuario", pkColumnName = "sequence_name", valueColumnName = "sequence_next_hi_value", pkColumnValue = "usuario", allocationSize = 1)
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
		return "Usuario [id=" + id + ", name=" + name + ", login=" + login + ", password=" + password + "]";
	}

	@Column(name = "name", length = 100, nullable = false)
	@Length(max = 100)
	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "login", length = 50, nullable = false)
	@Length(max = 50)
	@NotNull
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "password", length = 50, nullable = false)
	@Length(max = 50)
	@NotNull
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
