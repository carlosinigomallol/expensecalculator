package es.expensecalculator.web.action;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Generador de los informes fiscales
 */
public class GeneradorInformes {

	/** Constructor */
	public GeneradorInformes() {
		super();

	}

	public void generarInforme(List<?> beans, OutputStream out, InputStream plantilla) throws JRException {

		// Aseguramos el título correcto para el tipo fiscal
		makeReport(beans, out, plantilla);

	}
	
	public void generarInforme(List<?> beans, OutputStream out, String plantilla) throws JRException {

		// Aseguramos el título correcto para el tipo fiscal
		makeReport(beans, out, plantilla);

	}

	private void makeReport(List<?> beans, OutputStream out, InputStream plantilla) throws JRException {

		JasperPrint jasperPrint = null;
		Map<String, Object> param = new HashMap<String, Object>();
		JRBeanCollectionDataSource dataConnection = new JRBeanCollectionDataSource(beans);

		param.put("REPORT_LOCALE", new Locale("es"));
		jasperPrint = JasperFillManager.fillReport(plantilla, param, dataConnection);

		JasperExportManager.exportReportToPdfStream(jasperPrint, out);

	}
	
	
	private void makeReport(List<?> beans, OutputStream out, String plantilla) throws JRException {

		JasperPrint jasperPrint = null;
		Map<String, Object> param = new HashMap<String, Object>();
		JRBeanCollectionDataSource dataConnection = new JRBeanCollectionDataSource(beans);

		param.put("REPORT_LOCALE", new Locale("es"));
		jasperPrint = JasperFillManager.fillReport(plantilla, param, dataConnection);

		JasperExportManager.exportReportToPdfStream(jasperPrint, out);

	}

}
