package es.expensecalculator.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TreeMap;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.expensecalculator.model.Movimiento;
import es.expensecalculator.model.MovimientoBean;
import es.expensecalculator.model.MovimientoPeriodicoPlan;
import es.expensecalculator.model.MovimientoPeriodicoPlanBean;
import es.expensecalculator.model.MovimientoPlan;
import es.expensecalculator.model.MovimientoPlanBean;
import es.expensecalculator.model.Usuario;
import es.expensecalculator.model.enums.Periodicidad;
import es.expensecalculator.model.enums.SignoMovimiento;
import es.expensecalculator.model.enums.TipoMovimiento;

/**
 * Implementación del DAO para la tabla Movimiento
 */
public class CalculadoraPlanDAO implements ICalculadoraPlanDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(CalculadoraPlanDAO.class);

	/** Session de Hibernate */
	private SessionFactory sessionFactory;

	/**
	 * @return SessionFactory - Hibernate Session Factory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the new session factory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public BigDecimal calcularSaldoActual(Date fechaPeticionCalculoPlan, Usuario usuario) {
		// Saldo de movimientos bancarios
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fechaPeticion = simpleDateFormat.format(fechaPeticionCalculoPlan);
		BigDecimal saldoActual = BigDecimal.ZERO;
		String sql = "SELECT SUM(CASE m.signoMovimiento WHEN 'AA' THEN m.totalMovimiento WHEN 'BB' THEN m.totalMovimiento * (-1) END) From movimiento as m WHERE m.usuarioid = "
				+ usuario.getId() + " AND m.fechaInicio<='" + fechaPeticion + "'";
		org.hibernate.Query query = getSessionFactory().getCurrentSession().createSQLQuery(sql);

		List<Object> lista = query.list();
		BigDecimal saldoMovimientos = BigDecimal.ZERO;
		if (lista != null && !lista.isEmpty()) {
			if (lista.get(0) != null) {
				saldoMovimientos = (BigDecimal) lista.get(0);
			}
		}

		saldoActual = saldoMovimientos;

		return saldoActual;
	}

	public BigDecimal calcularSaldoActual(Date fechaPeticionCalculoPlan, Usuario usuario, MovimientoBean movimiento) {
		// Saldo de movimientos bancarios
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		BigDecimal saldoActual = BigDecimal.ZERO;
		BigDecimal saldoMovimientos = BigDecimal.ZERO;
		if (movimiento != null && (movimiento.getFechaInicio().before(fechaPeticionCalculoPlan)
				|| movimiento.getFechaInicio().equals(fechaPeticionCalculoPlan))) {
			saldoMovimientos = movimiento.getTotalMovimiento();
		}
		saldoActual = saldoMovimientos;

		return saldoActual;
	}

	public EstructuraSaldos calculadoraTotalGastosPlanes(List<MovimientoPlanBean> movimientoPlans,
			Date fechaPeticionCalculoPlan, TipoMovimiento tipoMovimiento,
			List<MovimientoPeriodicoPlanBean> movimientoPeriodicoPlans, Usuario usuario,
			MovimientoBean movimientoBean) {
		EstructuraSaldos estructuraSaldos = new EstructuraSaldos();
		GregorianCalendar gregorianCalendarIni = new GregorianCalendar();
		gregorianCalendarIni.setTime(fechaPeticionCalculoPlan);

		GregorianCalendar gregorianCalendarFin = new GregorianCalendar();
		gregorianCalendarFin.setTime(fechaPeticionCalculoPlan);
		gregorianCalendarFin.add(GregorianCalendar.YEAR, 3); // Se suman
		TreeMap<Date, SaldoEventosBean> porDia = new TreeMap<Date, SaldoEventosBean>();
		// BigDecimal saldoActual =
		// calcularSaldoActual(gregorianCalendarIni.getTime(), usuario);

		BigDecimal saldoActual = calcularSaldoActual(gregorianCalendarIni.getTime(), usuario, movimientoBean);

		while (gregorianCalendarIni.getTime().before(gregorianCalendarFin.getTime())) {

			System.out.println("PROCESANDO FECHA: " + gregorianCalendarIni.getTime());
			SaldoEventosBean saldoEventosBean = new SaldoEventosBean();
			BigDecimal totalfinal = BigDecimal.ZERO;
			BigDecimal totalMovimientosPlan = BigDecimal.ZERO;

			List<String> eventos = new ArrayList<String>();

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			LOGGER.info("SALDO ACTUAL A FECHA " + simpleDateFormat.format(fechaPeticionCalculoPlan)
					+ " SIN MOVIMIENTOS PLAN: " + saldoActual);

			Date fechaProceso = gregorianCalendarIni.getTime();

			if (movimientoPeriodicoPlans != null && !movimientoPeriodicoPlans.isEmpty()) {
				for (MovimientoPeriodicoPlanBean m : movimientoPeriodicoPlans) {
					if (!fechaProceso.before(m.getFechaInicio()) && !fechaProceso.after(m.getFechaFin())) {
						if (fechaProceso.getDate() == m.getDiaEjecucion().intValue()) {

							Periodicidad periodicidad = Periodicidad.fromValue(m.getPeriodidadEjecucion());

							switch (periodicidad) {
							case MENSUAL:
								System.out.println(
										"TENIENDO EN CUENTA MOVIMINETO PERIODICO PARA CALCULO DE SALDO ACTUAL: "
												+ m.getCantidad() + " EN FECHA " + fechaProceso);
								if (m.getSignoMovimiento().equals(SignoMovimiento.POSITIVO.getValue())) {
									eventos.add(
											"Movimiento periodico " + m.getDescripcion() + " SUMA " + m.getCantidad());
									totalMovimientosPlan = totalMovimientosPlan.add(m.getCantidad());
								} else {
									eventos.add(
											"Movimiento periodico " + m.getDescripcion() + " RESTA " + m.getCantidad());
									totalMovimientosPlan = totalMovimientosPlan.subtract(m.getCantidad());
								}
								break;
							case ANUAL:
								GregorianCalendar gregorianCalendarFechaInicio = new GregorianCalendar();
								gregorianCalendarFechaInicio.setTime(m.getFechaInicio());
								int monthFechaInicio = gregorianCalendarFechaInicio.get(GregorianCalendar.MONTH);

								GregorianCalendar gregorianCalendarFechaPeticion = new GregorianCalendar();
								gregorianCalendarFechaPeticion.setTime(fechaProceso);
								int monthFechaPeticion = gregorianCalendarFechaPeticion.get(GregorianCalendar.MONTH);

								if (monthFechaInicio == monthFechaPeticion) {
									if (m.getSignoMovimiento().equals(SignoMovimiento.POSITIVO.getValue())) {
										eventos.add("Movimiento periodico " + m.getDescripcion() + " SUMA "
												+ m.getCantidad());
										totalMovimientosPlan = totalMovimientosPlan.add(m.getCantidad());
									} else {
										eventos.add("Movimiento periodico " + m.getDescripcion() + " RESTA "
												+ m.getCantidad());
										totalMovimientosPlan = totalMovimientosPlan.subtract(m.getCantidad());
									}
								}

							}
							LOGGER.info("TRATANDO MOVIMIENTO PERIODICO " + m.toString());

						}
					}
				}
			}

			if (movimientoPlans != null && !movimientoPlans.isEmpty()) {
				for (MovimientoPlanBean m : movimientoPlans) {
					if (fechaProceso.equals(m.getFechaInicio()) && fechaProceso.equals(m.getFechaFin())) {
						LOGGER.info("TRATANDO MOVIMIENTO PLAN " + m);
						if (m.getSignoMovimiento().equals(SignoMovimiento.POSITIVO.getValue())) {
							totalMovimientosPlan = totalMovimientosPlan.add(m.getTotalMovimiento());
							eventos.add("Movimiento a fecha " + m.getDescripcion() + " SUMA " + m.getTotalMovimiento());
						} else {
							totalMovimientosPlan = totalMovimientosPlan.subtract(m.getTotalMovimiento());
							eventos.add(
									"Movimiento a fecha " + m.getDescripcion() + " RESTA " + m.getTotalMovimiento());
						}
					}
				}
			}

			// List<MovimientoAPlazosPlan> movimientoAPlazosPlans,
			// List<MovimientoHipotecaPlan> movimientoHipotecaPlans,
			// List<MovimientoRentingPlan> movimientoRentingPlans,
			//

			totalfinal = totalMovimientosPlan.add(saldoActual);
			LOGGER.info("SALDO ACTUAL A FECHA " + simpleDateFormat.format(fechaPeticionCalculoPlan)
					+ " CON MOVIMIENTOS PLAN: " + totalfinal);

			saldoEventosBean.setEventos(eventos);
			saldoEventosBean.setSaldo(totalfinal);

			porDia.put(gregorianCalendarIni.getTime(), saldoEventosBean);
			saldoActual = totalfinal;
			gregorianCalendarIni.add(GregorianCalendar.DATE, 1);
		}
		// Voy a hacer un calculo de dias

		estructuraSaldos.setPorDia(porDia);

		return estructuraSaldos;

	}

	private Date getFechaUltimoMovimientoExistente(Usuario usuario) {
		String sql = "SELECT MAX(m.fechaFin) From movimiento as m WHERE m.usuarioid = " + usuario.getId() + "";
		org.hibernate.Query query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
		List<Object> lista = query.list();
		Date fecha = null;
		if (lista != null && !lista.isEmpty()) {
			if (lista.get(0) != null) {
				fecha = (Date) lista.get(0);
			}
		}

		sql = "SELECT MAX(m.fechaFin) From movimientoConIva as m WHERE m.usuarioid = " + usuario.getId() + "";
		query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
		lista = query.list();
		if (lista != null && !lista.isEmpty()) {
			if (lista.get(0) != null) {
				Date fechaConIva = (Date) lista.get(0);
				if (fechaConIva.after(fecha)) {
					fecha = fechaConIva;
				}
			}
		}
		return fecha;
	}

	/**
	 * Actualiza una movimiento
	 * 
	 * @param movimiento
	 *            Movimiento
	 */
	public void updateMovimiento(Movimiento movimiento) {
		getSessionFactory().getCurrentSession().update(movimiento);
	}

	/**
	 * Borra una movimiento
	 * 
	 * @param movimiento
	 *            Movimiento
	 */
	public void deleteMovimiento(Movimiento movimiento) {
		getSessionFactory().getCurrentSession().delete(movimiento);
	}

}
