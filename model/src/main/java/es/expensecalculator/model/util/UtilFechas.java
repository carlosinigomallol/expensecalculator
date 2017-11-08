package es.expensecalculator.model.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class UtilFechas {

	private UtilFechas() {
		super();
	}

	public BigDecimal diferenciaFechas(Date date1, Date date2, int valor) {
		BigDecimal retorno = BigDecimal.ZERO;
		Calendar cal1 = null;
		cal1 = Calendar.getInstance();

		Calendar cal2 = null;
		cal2 = Calendar.getInstance();

		// different date might have different offset
		cal1.setTime(date1);
		long ldate1 = date1.getTime() + cal1.get(Calendar.ZONE_OFFSET) + cal1.get(Calendar.DST_OFFSET);

		cal2.setTime(date2);
		long ldate2 = date2.getTime() + cal2.get(Calendar.ZONE_OFFSET) + cal2.get(Calendar.DST_OFFSET);

		// Use integer calculation, truncate the decimals
		int hr1 = (int) (ldate1 / 3600000); // 60*60*1000
		int hr2 = (int) (ldate2 / 3600000);

		int days1 = (int) hr1 / 24;
		int days2 = (int) hr2 / 24;

		int dateDiff = days2 - days1;
		int yearDiff = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
		int monthDiff = yearDiff * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);

		if (valor == 1) {
			if (dateDiff < 0)
				dateDiff = dateDiff * (-1);
			retorno = new BigDecimal(dateDiff + "");
		} else if (valor == 2) {
			if (monthDiff < 0)
				monthDiff = monthDiff * (-1);
			retorno = new BigDecimal(monthDiff + "");
		} else if (valor == 3) {
			if (yearDiff < 0)
				yearDiff = yearDiff * (-1);
			retorno = new BigDecimal(yearDiff + "");
		}
		return retorno;
	}

	public static BigDecimal getMonths(GregorianCalendar g1, GregorianCalendar g2) {
		BigDecimal elapsed = new BigDecimal("-1"); // Por defecto estaba en 0 y
													// siempre asi no haya
		// pasado un mes contaba 1)
		GregorianCalendar gc1, gc2;
		Date d1, d2;

		if (g2.after(g1)) {
			gc2 = (GregorianCalendar) g2.clone();
			gc1 = (GregorianCalendar) g1.clone();
		} else {
			gc2 = (GregorianCalendar) g1.clone();
			gc1 = (GregorianCalendar) g2.clone();
		}

		while (gc1.before(gc2)) {
			gc1.add(Calendar.MONTH, 1);
			elapsed = elapsed.add(BigDecimal.ONE);
		}

		if (gc1.get(Calendar.DATE) == (gc2.get(Calendar.DATE)))
			elapsed = elapsed.add(BigDecimal.ONE);
		return elapsed;
	}

	public static Date getFechaMasUno(Date fecha) {
		GregorianCalendar greg = new GregorianCalendar();
		greg.setTime(fecha);
		greg.add(GregorianCalendar.DATE, +1);
		return greg.getTime();
	}

	public static Date getFechaMenosUno(Date fecha) {
		GregorianCalendar greg = new GregorianCalendar();
		greg.setTime(fecha);
		greg.add(GregorianCalendar.DATE, -1);
		return greg.getTime();
	}

	public static Date getDechaActualFormateada() {
		Date actual = new Date(System.currentTimeMillis());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			actual = simpleDateFormat.parse(simpleDateFormat.format(actual));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return actual;
	}

	public static List<Date> getDiffFechas(Date fechaDesde, Date fechaHasta) {
		GregorianCalendar gcDesde = new GregorianCalendar();
		gcDesde.setTime(fechaDesde);
		GregorianCalendar gcHasta = new GregorianCalendar();
		gcHasta.setTime(fechaHasta);
		return dameDias(gcDesde, gcHasta);
	}

	private static List<Date> dameDias(GregorianCalendar cal, GregorianCalendar calHasta) {
		List<Date> fechas = new ArrayList<Date>();
		fechas.add(new java.sql.Date(cal.getTimeInMillis()));
		while (!cal.getTime().equals(calHasta.getTime())) {
			cal.add(GregorianCalendar.DATE, 1);
			fechas.add(new java.sql.Date(cal.getTimeInMillis()));
		}
		return fechas;
	}

	public static Date getAnioMenosUno(Date fecha) {
		GregorianCalendar greg = new GregorianCalendar();
		greg.setTime(fecha);
		greg.add(GregorianCalendar.YEAR, -1);
		return greg.getTime();
	}

	public static String getAnioFecha(Date fecha) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		return simpleDateFormat.format(fecha);
	}

	public static String getStringMilisegundos(Date fecha) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddHHmmssSSS");
		return simpleDateFormat.format(fecha);
	}

	public static String getStringMilisegundosSinFecha(Date fecha) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mmssSSS");
		return simpleDateFormat.format(fecha);
	}

}
