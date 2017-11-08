package es.expensecalculator.web.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.component.wizard.Wizard;
import org.primefaces.context.RequestContext;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.FlowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import es.expensecalculator.dao.EstructuraSaldos;
import es.expensecalculator.dao.SaldoEventosBean;
import es.expensecalculator.model.Movimiento;
import es.expensecalculator.model.MovimientoBean;
import es.expensecalculator.model.MovimientoImagen;
import es.expensecalculator.model.MovimientoPeriodicoPlan;
import es.expensecalculator.model.MovimientoPeriodicoPlanBean;
import es.expensecalculator.model.MovimientoPeriodicoPlanImagen;
import es.expensecalculator.model.MovimientoPlan;
import es.expensecalculator.model.MovimientoPlanBean;
import es.expensecalculator.model.MovimientoPlanImagen;
import es.expensecalculator.model.Plan;
import es.expensecalculator.model.TipoEntorno;
import es.expensecalculator.model.Usuario;
import es.expensecalculator.model.enums.SignoMovimiento;
import es.expensecalculator.model.enums.TipoMovimiento;
import es.expensecalculator.service.ICalculadoraPlanService;
import es.expensecalculator.service.IMovimientoImagenService;
import es.expensecalculator.service.IMovimientoPeriodicoPlanImagenService;
import es.expensecalculator.service.IMovimientoPeriodicoPlanService;
import es.expensecalculator.service.IMovimientoPlanImagenService;
import es.expensecalculator.service.IMovimientoPlanService;
import es.expensecalculator.service.IMovimientoService;
import es.expensecalculator.service.IPlanService;
import es.expensecalculator.web.param.MovimientoPeriodicoPlanParam;
import es.expensecalculator.web.param.MovimientoPlanParam;
import es.expensecalculator.web.security.UserSessionBean;

/**
 * Manejador de la consulta de movimientoPlanes
 */
@ManagedBean(name = "calculadoraFuturoGestionManagedBean")
@ViewScoped
public class CalculadoraFuturoGestionManagedBean extends ComunListados implements Serializable {

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(CalculadoraFuturoGestionManagedBean.class);

	/** Conenedor de los datos del usuario conectado */
	@ManagedProperty(value = "#{userSessionBean}")
	UserSessionBean userSessionBean;

	/** Servicios de persistencia de las movimientoPlanes */
	@ManagedProperty(value = "#{CalculadoraPlanService}")
	ICalculadoraPlanService calculadoraPlanService;

	@ManagedProperty(value = "#{PlanService}")
	IPlanService planService;

	@ManagedProperty(value = "#{MovimientoService}")
	IMovimientoService movimientoService;

	@ManagedProperty(value = "#{MovimientoPlanService}")
	IMovimientoPlanService movimientoPlanService;

	@ManagedProperty(value = "#{MovimientoPeriodicoPlanService}")
	IMovimientoPeriodicoPlanService movimientoPeriodicoPlanService;

	@ManagedProperty(value = "#{MovimientoImagenService}")
	IMovimientoImagenService movimientoImagenService;

	@ManagedProperty(value = "#{MovimientoPlanImagenService}")
	IMovimientoPlanImagenService movimientoPlanImagenService;

	@ManagedProperty(value = "#{MovimientoPeriodicoPlanImagenService}")
	IMovimientoPeriodicoPlanImagenService movimientoPeriodicoPlanImagenService;
	private boolean skip;
	private String planCombo = "N";
	/** Número de serie por defecto */
	private static final long serialVersionUID = 1L;
	private TipoEntorno tipoentorno = TipoEntorno.MOVIMIENTO;
	private MovimientoBean movimientoInicial;
	private List<MovimientoPlanBean> movimientoPlansList = new ArrayList<MovimientoPlanBean>();
	private List<MovimientoPeriodicoPlanBean> movimientoPeriodicoPlansList = new ArrayList<MovimientoPeriodicoPlanBean>();
	private List<MovimientoPlanBean> movimientoPlansListDropped = new ArrayList<MovimientoPlanBean>();
	private List<MovimientoPeriodicoPlanBean> movimientoPeriodicoPlansListDropped = new ArrayList<MovimientoPeriodicoPlanBean>();
	/** Declaración seleccionada */
	private MovimientoPlanBean selectedMovimientoPlan;
	private MovimientoPeriodicoPlanBean selectedMovimientoPeriodicoPlan;
	private MovimientoPlanBean movimientoNuevo;
	private MovimientoPeriodicoPlanBean movimientoPeriodicoNuevo;
	private Plan plan;
	private List<Plan> planesList = new ArrayList<Plan>();
	private Plan planSeleccionadoCombo;
	private List<TablaResultados> tablaResultados = new ArrayList<TablaResultados>();

	public void borrarPlan() {
		try {
			planService.borrarPlan(planSeleccionadoCombo);
			planSeleccionadoCombo =null;
			inicializarDatosPorCambioPlanCombo();
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Borrado correctamente el plan seleccionado");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error al borrar el plan");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public void inicializarDatosPorCambioPlanCombo() {
		if (planCombo != null && planCombo.equals("N")) {
			Wizard wizard = (Wizard) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent("carForm:wizardMovimientos");
			wizard.setStep("personal");
			RequestContext.getCurrentInstance().update("carForm");
			planSeleccionadoCombo = null;
			planesList = new ArrayList<Plan>();
			tablaResultados = new ArrayList<TablaResultados>();
			tipoentorno = TipoEntorno.MOVIMIENTO;
			init();
		} else if (planCombo != null && planCombo.equals("S")) {
			Wizard wizard = (Wizard) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent("carForm:wizardMovimientos");
			wizard.setStep("personal");
			RequestContext.getCurrentInstance().update("carForm");
			movimientoPlansList = new ArrayList<MovimientoPlanBean>();
			movimientoPeriodicoPlansList = new ArrayList<MovimientoPeriodicoPlanBean>();
			tipoentorno = TipoEntorno.PLAN;
			tablaResultados = new ArrayList<TablaResultados>();
			planesList = recuperarPlanesPorUsuario(userSessionBean.getUsuario());

		}
	}

	public void rellenarFormularioConPlanSeleccionado() {

		MovimientoImagen movimientoImagenBD = movimientoImagenService.getMovimientoImagenPorPlan(planSeleccionadoCombo);
		List<MovimientoPeriodicoPlanImagen> movimientoPeriodicoPlanImagenListBD = movimientoPeriodicoPlanImagenService
				.getMovimientosDesdePlan(planSeleccionadoCombo);
		List<MovimientoPlanImagen> movimientoPlanImagenListBD = movimientoPlanImagenService
				.getMovimientosDesdePlan(planSeleccionadoCombo);

		if (movimientoImagenBD == null) {
			movimientoInicial = new MovimientoBean();
			movimientoInicial.setDescripcion("Saldo inicial");
			movimientoInicial.setSignoMovimiento(SignoMovimiento.POSITIVO.getValue());
			movimientoInicial.setTipoMovimiento(TipoMovimiento.SALDO_INICIAL.getValue());
			movimientoInicial.setTipoentorno(TipoEntorno.PLAN);
			movimientoInicial.setDivisa("EUR");
		} else {
			movimientoInicial = new MovimientoBean();
			movimientoInicial.setId(movimientoImagenBD.getId());
			movimientoInicial.setDescripcion(movimientoImagenBD.getDescripcion());
			movimientoInicial.setSignoMovimiento(movimientoImagenBD.getSignoMovimiento());
			movimientoInicial.setTipoMovimiento(movimientoImagenBD.getTipoMovimiento());
			movimientoInicial.setTotalMovimiento(movimientoImagenBD.getTotalMovimiento());
			movimientoInicial.setFechaInicio(movimientoImagenBD.getFechaInicio());
			movimientoInicial.setFechaFin(movimientoImagenBD.getFechaFin());
			movimientoInicial.setTipoentorno(TipoEntorno.PLAN);
			movimientoInicial.setDivisa("EUR");
		}
		movimientoPlansListDropped = new ArrayList<MovimientoPlanBean>();
		movimientoPeriodicoPlansListDropped = new ArrayList<MovimientoPeriodicoPlanBean>();

		for (MovimientoPlanImagen mp : movimientoPlanImagenListBD) {
			MovimientoPlanBean movimientoPlanBean = new MovimientoPlanBean();
			movimientoPlanBean.setId(mp.getId());
			movimientoPlanBean.setDescripcion(mp.getDescripcion());
			movimientoPlanBean.setDivisa(mp.getDivisa());
			movimientoPlanBean.setFechaFin(mp.getFechaFin());
			movimientoPlanBean.setFechaInicio(mp.getFechaInicio());
			movimientoPlanBean.setSignoMovimiento(mp.getSignoMovimiento());
			movimientoPlanBean.setTipoMovimiento(mp.getTipoMovimiento());
			movimientoPlanBean.setTotalMovimiento(mp.getTotalMovimiento());
			movimientoPlanBean.setTipoentorno(TipoEntorno.PLAN);
			movimientoPlansListDropped.add(movimientoPlanBean);
		}
		for (MovimientoPeriodicoPlanImagen mpp : movimientoPeriodicoPlanImagenListBD) {
			MovimientoPeriodicoPlanBean movimientoPeridodicoPlanBean = new MovimientoPeriodicoPlanBean();
			movimientoPeridodicoPlanBean.setId(mpp.getId());
			movimientoPeridodicoPlanBean.setCantidad(mpp.getCantidad());
			movimientoPeridodicoPlanBean.setDescripcion(mpp.getDescripcion());
			movimientoPeridodicoPlanBean.setDiaEjecucion(mpp.getDiaEjecucion());
			movimientoPeridodicoPlanBean.setDivisa(mpp.getDivisa());
			movimientoPeridodicoPlanBean.setFechaFin(mpp.getFechaFin());
			movimientoPeridodicoPlanBean.setFechaInicio(mpp.getFechaInicio());
			movimientoPeridodicoPlanBean.setPeriodidadEjecucion(mpp.getPeriodidadEjecucion());
			movimientoPeridodicoPlanBean.setSignoMovimiento(mpp.getSignoMovimiento());
			movimientoPeridodicoPlanBean.setTipoMovimiento(mpp.getTipoMovimiento());
			movimientoPeridodicoPlanBean.setTipoentorno(TipoEntorno.PLAN);
			movimientoPeriodicoPlansListDropped.add(movimientoPeridodicoPlanBean);
		}

		Wizard wizard = (Wizard) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent("carForm:wizardMovimientos");
		wizard.setStep("personal");
		RequestContext.getCurrentInstance().update("carForm");

	}

	public List<Plan> completarPlan(String query) {
		return planService.getPlanDesdeDescripcion(query, userSessionBean.getUsuario());
	}

	private List<Plan> recuperarPlanesPorUsuario(Usuario usuario) {
		return planService.getPlanesPorUsuario(usuario);
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	@PostConstruct
	public void init() {
		try {
			if (userSessionBean != null && userSessionBean.getUsuario() != null) {
				MovimientoPlanParam movimientoPlanParam = new MovimientoPlanParam();
				movimientoPlanParam.setUsuario(userSessionBean.getUsuario());
				MovimientoPeriodicoPlanParam movimientoPeriodicoPlanParam = new MovimientoPeriodicoPlanParam();
				movimientoPeriodicoPlanParam.setUsuario(userSessionBean.getUsuario());
				List<MovimientoPlan> movimientoPlansListBD = movimientoPlanService
						.getMovimientoPlanListWithRestrictions(movimientoPlanParam);
				List<MovimientoPeriodicoPlan> movimientoPeriodicoPlansListDB = movimientoPeriodicoPlanService
						.getMovimientoPeriodicoPlanListWithRestrictions(movimientoPeriodicoPlanParam);

				for (MovimientoPlan mp : movimientoPlansListBD) {
					MovimientoPlanBean movimientoPlanBean = new MovimientoPlanBean();
					movimientoPlanBean.setId(mp.getId());
					movimientoPlanBean.setDescripcion(mp.getDescripcion());
					movimientoPlanBean.setDivisa(mp.getDivisa());
					movimientoPlanBean.setFechaFin(mp.getFechaFin());
					movimientoPlanBean.setFechaInicio(mp.getFechaInicio());
					movimientoPlanBean.setSignoMovimiento(mp.getSignoMovimiento());
					movimientoPlanBean.setTipoMovimiento(mp.getTipoMovimiento());
					movimientoPlanBean.setTotalMovimiento(mp.getTotalMovimiento());
					movimientoPlanBean.setTipoentorno(TipoEntorno.MOVIMIENTO);
					movimientoPlansList.add(movimientoPlanBean);
				}
				for (MovimientoPeriodicoPlan mpp : movimientoPeriodicoPlansListDB) {
					MovimientoPeriodicoPlanBean movimientoPeridodicoPlanBean = new MovimientoPeriodicoPlanBean();
					movimientoPeridodicoPlanBean.setId(mpp.getId());
					movimientoPeridodicoPlanBean.setCantidad(mpp.getCantidad());
					movimientoPeridodicoPlanBean.setDescripcion(mpp.getDescripcion());
					movimientoPeridodicoPlanBean.setDiaEjecucion(mpp.getDiaEjecucion());
					movimientoPeridodicoPlanBean.setDivisa(mpp.getDivisa());
					movimientoPeridodicoPlanBean.setFechaFin(mpp.getFechaFin());
					movimientoPeridodicoPlanBean.setFechaInicio(mpp.getFechaInicio());
					movimientoPeridodicoPlanBean.setPeriodidadEjecucion(mpp.getPeriodidadEjecucion());
					movimientoPeridodicoPlanBean.setSignoMovimiento(mpp.getSignoMovimiento());
					movimientoPeridodicoPlanBean.setTipoMovimiento(mpp.getTipoMovimiento());
					movimientoPeridodicoPlanBean.setTipoentorno(TipoEntorno.MOVIMIENTO);
					movimientoPeriodicoPlansList.add(movimientoPeridodicoPlanBean);
				}

				movimientoPlansListDropped = new ArrayList<MovimientoPlanBean>();
				movimientoPeriodicoPlansListDropped = new ArrayList<MovimientoPeriodicoPlanBean>();
				tablaResultados = new ArrayList<TablaResultados>();

				Movimiento movimientoInicialBD = movimientoService
						.getMovimientoPorUsuario(userSessionBean.getUsuario());
				if (movimientoInicialBD == null) {
					movimientoInicial = new MovimientoBean();
					movimientoInicial.setDescripcion("Saldo inicial");
					movimientoInicial.setSignoMovimiento(SignoMovimiento.POSITIVO.getValue());
					movimientoInicial.setTipoMovimiento(TipoMovimiento.SALDO_INICIAL.getValue());
					movimientoInicial.setTipoentorno(TipoEntorno.MOVIMIENTO);
					movimientoInicial.setDivisa("EUR");
				} else {
					movimientoInicial = new MovimientoBean();
					movimientoInicial.setDescripcion(movimientoInicialBD.getDescripcion());
					movimientoInicial.setDivisa(movimientoInicialBD.getDivisa());
					movimientoInicial.setFechaFin(movimientoInicialBD.getFechaFin());
					movimientoInicial.setFechaInicio(movimientoInicialBD.getFechaInicio());
					movimientoInicial.setId(movimientoInicialBD.getId());
					movimientoInicial.setSignoMovimiento(movimientoInicialBD.getSignoMovimiento());
					movimientoInicial.setTipoentorno(TipoEntorno.MOVIMIENTO);
					movimientoInicial.setTipoMovimiento(movimientoInicialBD.getTipoMovimiento());
					movimientoInicial.setTotalMovimiento(movimientoInicialBD.getTotalMovimiento());
				}
				createFilter();

			} else {
				System.out.println("ERROR!!!!!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void inicializarPlan() {
		plan = new Plan();
	}

	public void perisitirSaldoInicial() {
		if (movimientoInicial.getId() != null) {
			movimientoInicial.setFechaFin(movimientoInicial.getFechaInicio());

			if (movimientoInicial.getTipoentorno().equals(TipoEntorno.MOVIMIENTO)) {
				Movimiento mov = movimientoService.getMovimientoById(movimientoInicial.getId());
				mov.setDescripcion(movimientoInicial.getDescripcion());
				mov.setDivisa(movimientoInicial.getDivisa());
				mov.setFechaFin(movimientoInicial.getFechaFin());
				mov.setSignoMovimiento(movimientoInicial.getSignoMovimiento());
				mov.setTipoMovimiento(movimientoInicial.getTipoMovimiento());
				mov.setTotalMovimiento(movimientoInicial.getTotalMovimiento());
				// mov.setUsuario(userSessionBean.getUsuario()); no hace falta
				movimientoService.actualizarMovimiento(mov);
			} else {
				MovimientoImagen mov = movimientoImagenService.getMovimientoImagenById(movimientoInicial.getId());
				mov.setDescripcion(movimientoInicial.getDescripcion());
				mov.setDivisa(movimientoInicial.getDivisa());
				mov.setFechaFin(movimientoInicial.getFechaFin());
				mov.setSignoMovimiento(movimientoInicial.getSignoMovimiento());
				mov.setTipoMovimiento(movimientoInicial.getTipoMovimiento());
				mov.setTotalMovimiento(movimientoInicial.getTotalMovimiento());
				mov.setPlan(planSeleccionadoCombo);
				movimientoImagenService.actualizarMovimientoImagen(mov);
			}
		} else {
			movimientoInicial.setFechaFin(movimientoInicial.getFechaInicio());
			if (movimientoInicial.getTipoentorno().equals(TipoEntorno.MOVIMIENTO)) {
				Movimiento mov = movimientoService.getMovimientoById(movimientoInicial.getId());
				mov.setDescripcion(movimientoInicial.getDescripcion());
				mov.setDivisa(movimientoInicial.getDivisa());
				mov.setFechaFin(movimientoInicial.getFechaFin());
				mov.setSignoMovimiento(movimientoInicial.getSignoMovimiento());
				mov.setTipoMovimiento(movimientoInicial.getTipoMovimiento());
				mov.setTotalMovimiento(movimientoInicial.getTotalMovimiento());
				mov.setUsuario(userSessionBean.getUsuario());
				movimientoService.addMovimiento(mov);
			} else {
				MovimientoImagen mov = movimientoImagenService.getMovimientoImagenById(movimientoInicial.getId());
				mov.setDescripcion(movimientoInicial.getDescripcion());
				mov.setDivisa(movimientoInicial.getDivisa());
				mov.setFechaFin(movimientoInicial.getFechaFin());
				mov.setSignoMovimiento(movimientoInicial.getSignoMovimiento());
				mov.setTipoMovimiento(movimientoInicial.getTipoMovimiento());
				mov.setTotalMovimiento(movimientoInicial.getTotalMovimiento());
				mov.setPlan(planSeleccionadoCombo);
				movimientoImagenService.addMovimientoImagen(mov);
			}
		}
		FacesMessage message = null;
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Grabado", "Grabado saldo inicial");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public Date dameSaldoInicialFecha() {
		return getTablaResultados().get(0).getFecha();
	}

	public Date dameSaldoFinalFecha() {
		return getTablaResultados().get(getTablaResultados().size() - 1).getFecha();
	}

	public BigDecimal dameSaldoInicialSaldo() {
		return getTablaResultados().get(0).getSaldo();
	}

	public BigDecimal dameSaldoFinalSaldo() {
		return getTablaResultados().get(getTablaResultados().size() - 1).getSaldo();

	}

	public void informePlan() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.setContentType("application/pdf");
		String naam = "salida";
		response.setHeader("Content-Disposition", "attachment; filename=\"" + naam + ".pdf" + "\"");

		tablaResultados = new ArrayList<TablaResultados>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		EstructuraSaldos estructuraSaldos = null;
		try {
			estructuraSaldos = calculadoraPlanService.calculadoraTotalGastosPlanes(movimientoPlansListDropped,
					simpleDateFormat.parse(simpleDateFormat.format(new Date(System.currentTimeMillis()))),
					TipoMovimiento.NOMINA, movimientoPeriodicoPlansListDropped, userSessionBean.getUsuario(),
					movimientoInicial);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		if (estructuraSaldos != null) {
			TreeMap<Date, SaldoEventosBean> porDia = estructuraSaldos.getPorDia();
			for (Date dia : porDia.keySet()) {
				System.out.println("Fecha: " + simpleDateFormat.format(dia) + " " + porDia.get(dia).getSaldo());
				TablaResultados tab = new TablaResultados();
				tab.setFecha(dia);
				tab.setSaldo(porDia.get(dia).getSaldo());
				tab.setEventos(porDia.get(dia).getEventos());
				tablaResultados.add(tab);
			}
		}

		try {

			OutputStream out = response.getOutputStream();

			GeneradorInformes generadorInformes = new GeneradorInformes();
			InputStream ios = this.getClass().getResourceAsStream("/InformeSaldos.jasper");
			generadorInformes.generarInforme(tablaResultados, out, ios);
			if (out != null) {
				System.out.println(out.toString());
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		context.responseComplete();
	}

	public void calcularPlan() {
		tablaResultados = new ArrayList<TablaResultados>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		EstructuraSaldos estructuraSaldos = null;
		try {
			estructuraSaldos = calculadoraPlanService.calculadoraTotalGastosPlanes(movimientoPlansListDropped,
					simpleDateFormat.parse(simpleDateFormat.format(new Date(System.currentTimeMillis()))),
					TipoMovimiento.NOMINA, movimientoPeriodicoPlansListDropped, userSessionBean.getUsuario(),
					movimientoInicial);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RequestContext context = RequestContext.getCurrentInstance();
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No Calculado",
					"Saldo no calculado: " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			context.addCallbackParam("calculado", "NO");
			return;
		}
		if (estructuraSaldos != null) {
			TreeMap<Date, SaldoEventosBean> porDia = estructuraSaldos.getPorDia();
			for (Date dia : porDia.keySet()) {
				System.out.println("Fecha: " + simpleDateFormat.format(dia) + " " + porDia.get(dia).getSaldo());
				TablaResultados tab = new TablaResultados();
				tab.setFecha(dia);
				tab.setSaldo(porDia.get(dia).getSaldo());
				tab.setEventos(porDia.get(dia).getEventos());
				tablaResultados.add(tab);
			}
		}

		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Calculado", "Saldo calculado");
		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("calculado", "SI");

	}

	public void moverTodos() {
		for (MovimientoPlanBean m : movimientoPlansList) {
			movimientoPlansListDropped.add(m);
		}

		movimientoPlansList.clear();
	}

	public void moverTodosMP() {
		for (MovimientoPeriodicoPlanBean m : movimientoPeriodicoPlansList) {
			movimientoPeriodicoPlansListDropped.add(m);
		}
		movimientoPeriodicoPlansList.clear();
	}

	public void createPdf(String filename) throws IOException, DocumentException {
		// step 1
		Document document = new Document();
		// step 2
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		// step 3
		document.open();
		// step 4
		document.add(createFirstTable());
		// step 5
		document.close();
	}

	/**
	 * Creates our first table
	 * 
	 * @return our first table
	 */
	public static PdfPTable createFirstTable() {
		// a table with three columns
		PdfPTable table = new PdfPTable(3);
		// the cell object
		PdfPCell cell;
		// we add a cell with colspan 3
		cell = new PdfPCell(new Phrase("Cell with colspan 3"));
		cell.setColspan(3);
		table.addCell(cell);
		// now we add a cell with rowspan 2
		cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
		cell.setRowspan(2);
		table.addCell(cell);
		// we add the four remaining cells with addCell()
		table.addCell("row 1; cell 1");
		table.addCell("row 1; cell 2");
		table.addCell("row 2; cell 1");
		table.addCell("row 2; cell 2");
		return table;
	}

	public void actualizarDatosMovimiento(MovimientoPlanBean selectedMovimientoPlan) {
		if (movimientoPlansList.get(movimientoPlansList.indexOf(selectedMovimientoPlan)) != null) {
			MovimientoPlanBean mov = (movimientoPlansList.get(movimientoPlansList.indexOf(selectedMovimientoPlan)));
			mov.desdeOtroMovimientoPlan(selectedMovimientoPlan);
		}
	}

	public void actualizarBDDatosMovimiento(MovimientoPlanBean selectedMovimientoPlan) {
		if (movimientoPlansList.get(movimientoPlansList.indexOf(selectedMovimientoPlan)) != null) {
			MovimientoPlanBean mov = (movimientoPlansList.get(movimientoPlansList.indexOf(selectedMovimientoPlan)));
			if (mov.getTipoentorno().equals(TipoEntorno.MOVIMIENTO)) {
				mov.desdeOtroMovimientoPlan(selectedMovimientoPlan);
				MovimientoPlan mv = movimientoPlanService.getMovimientoPlanById(mov.getId());
				mv.setDescripcion(mov.getDescripcion());
				mv.setDivisa(mov.getDivisa());
				mv.setFechaFin(mov.getFechaFin());
				mv.setFechaInicio(mov.getFechaInicio());
				mv.setSignoMovimiento(mov.getSignoMovimiento());
				mv.setTipoMovimiento(mov.getTipoMovimiento());
				mv.setTotalMovimiento(mov.getTotalMovimiento());
				movimientoPlanService.actualizarMovimientoPlan(mv);
			} else {
				mov.desdeOtroMovimientoPlan(selectedMovimientoPlan);
				MovimientoPlanImagen mv = movimientoPlanImagenService.getMovimientoPlanImagenById(mov.getId());
				mv.setDescripcion(mov.getDescripcion());
				mv.setDivisa(mov.getDivisa());
				mv.setFechaFin(mov.getFechaFin());
				mv.setFechaInicio(mov.getFechaInicio());
				mv.setSignoMovimiento(mov.getSignoMovimiento());
				mv.setTipoMovimiento(mov.getTipoMovimiento());
				mv.setTotalMovimiento(mov.getTotalMovimiento());
				movimientoPlanImagenService.actualizarMovimientoPlanImagen(mv);
			}
		}
	}

	public void actualizarDatosMovimientoPeriodico(MovimientoPeriodicoPlanBean selectedMovimientoPeriodicoPlan) {
		if (movimientoPlansList.get(movimientoPlansList.indexOf(selectedMovimientoPlan)) != null) {
			MovimientoPlanBean mov = (movimientoPlansList.get(movimientoPlansList.indexOf(selectedMovimientoPlan)));
			mov.desdeOtroMovimientoPlan(selectedMovimientoPlan);
		}
	}

	public void actualizarBDDatosMovimientoPeriodico(MovimientoPeriodicoPlanBean selectedMovimientoPeriodicoPlan) {
		if (movimientoPeriodicoPlansList
				.get(movimientoPeriodicoPlansList.indexOf(selectedMovimientoPeriodicoPlan)) != null) {
			MovimientoPeriodicoPlanBean mov = (movimientoPeriodicoPlansList
					.get(movimientoPeriodicoPlansList.indexOf(selectedMovimientoPeriodicoPlan)));
			if (mov.getTipoentorno().equals(TipoEntorno.MOVIMIENTO)) {
				mov.desdeOtroMovimientoPeriodicoPlan(selectedMovimientoPeriodicoPlan);
				MovimientoPeriodicoPlan mv = movimientoPeriodicoPlanService.getMovimientoPeriodicoPlanById(mov.getId());
				movimientoPeriodicoPlanService.actualizarMovimientoPeriodicoPlan(mv);
			} else {
				mov.desdeOtroMovimientoPeriodicoPlan(selectedMovimientoPeriodicoPlan);
				MovimientoPeriodicoPlanImagen mv = movimientoPeriodicoPlanImagenService
						.getMovimientoPeriodicoPlanImagenById(mov.getId());
				mv.setCantidad(mov.getCantidad());
				mv.setDescripcion(mov.getDescripcion());
				mv.setDiaEjecucion(mov.getDiaEjecucion());
				mv.setDivisa(mov.getDivisa());
				mv.setFechaFin(mov.getFechaFin());
				mv.setFechaInicio(mov.getFechaInicio());
				mv.setPeriodidadEjecucion(mov.getPeriodidadEjecucion());
				mv.setSignoMovimiento(mov.getSignoMovimiento());
				mv.setTipoMovimiento(mov.getTipoMovimiento());
				movimientoPeriodicoPlanImagenService.actualizarMovimientoPeriodicoPlanImagen(mv);
			}

		}
	}

	public List<TablaResultados> getTablaResultados() {
		return tablaResultados;
	}

	public void setTablaResultados(List<TablaResultados> tablaResultados) {
		this.tablaResultados = tablaResultados;
	}

	public void borrar(MovimientoPlanBean mov) {
		movimientoPlansListDropped.remove(mov);
		movimientoPlansList.add(mov);
	}

	public void borrar(MovimientoPeriodicoPlanBean mov) {
		movimientoPeriodicoPlansListDropped.remove(mov);
		movimientoPeriodicoPlansList.add(mov);
	}

	public void onMovimientoPeriodicoPlanDrop(DragDropEvent ddEvent) {
		MovimientoPeriodicoPlanBean movimientoPeriodicoPlan = ((MovimientoPeriodicoPlanBean) ddEvent.getData());
		movimientoPeriodicoPlansListDropped.add(movimientoPeriodicoPlan);
		movimientoPeriodicoPlansList.remove(movimientoPeriodicoPlan);
	}

	public void onMovimientoPlanDrop(DragDropEvent ddEvent) {
		MovimientoPlanBean movimientoPlan = ((MovimientoPlanBean) ddEvent.getData());
		movimientoPlansListDropped.add(movimientoPlan);
		movimientoPlansList.remove(movimientoPlan);
	}

	/**
	 * Inicializa el filtro de busqueda
	 * 
	 * @throws ModelosAEATCriticalException
	 */
	private void createFilter() throws Exception {
		inicializaFiltro();
	}

	/**
	 * Clear form. Limpia el filtro de busqueda
	 * 
	 * @throws ModelosAEATCriticalException
	 */
	public void clearForm() throws Exception {
		inicializaFiltro();
		this.selectedMovimientoPlan = null;
		this.selectedMovimientoPeriodicoPlan = null;
	}

	/**
	 * Inicializa el contenedor de criterios de búsqueda de movimientoPlanes
	 * 
	 * @throws ModelosAEATCriticalException
	 */
	private void inicializaFiltro() {
		// setFilter(new MovimientoPlanParam());
	}

	/**
	 * Find. Al pulsar el boton de busqueda se ejecuta el listado y se guardan
	 * los parametro como ultimo filtro de busqueda por si se accede y se vuelve
	 * a la pagina anterior
	 */
	// public void find() {
	// // setSelected(null);
	//
	// MovimientoPlanParam movimientoPlanParam = new MovimientoPlanParam();
	// MovimientoPeriodicoPlanParam movimientoPeriodicoPlanParam = new
	// MovimientoPeriodicoPlanParam();
	// List<MovimientoPlan> movimientoPlansListBD =
	// movimientoPlanService.getMovimientoPlanListWithRestrictions(movimientoPlanParam);
	// List<MovimientoPeriodicoPlan> movimientoPeriodicoPlansListDB =
	// movimientoPeriodicoPlanService
	// .getMovimientoPeriodicoPlanListWithRestrictions(movimientoPeriodicoPlanParam);
	//
	//
	// for (MovimientoPlan mp : movimientoPlansListBD) {
	// MovimientoPlanBean movimientoPlanBean = new MovimientoPlanBean();
	// movimientoPlanBean.setDescripcion(mp.getDescripcion());
	// movimientoPlanBean.setDivisa(mp.getDivisa());
	// movimientoPlanBean.setFechaFin(mp.getFechaFin());
	// movimientoPlanBean.setFechaInicio(mp.getFechaInicio());
	// movimientoPlanBean.setSignoMovimiento(mp.getSignoMovimiento());
	// movimientoPlanBean.setTipoMovimiento(mp.getTipoMovimiento());
	// movimientoPlanBean.setTotalMovimiento(mp.getTotalMovimiento());
	// movimientoPlansList.add(movimientoPlanBean);
	// }
	// for (MovimientoPeriodicoPlan mpp : movimientoPeriodicoPlansListDB) {
	// MovimientoPeriodicoPlanBean movimientoPeridodicoPlanBean = new
	// MovimientoPeriodicoPlanBean();
	// movimientoPeridodicoPlanBean.setCantidad(mpp.getCantidad());
	// movimientoPeridodicoPlanBean.setDescripcion(mpp.getDescripcion());
	// movimientoPeridodicoPlanBean.setDiaEjecucion(mpp.getDiaEjecucion());
	// movimientoPeridodicoPlanBean.setDivisa(mpp.getDivisa());
	// movimientoPeridodicoPlanBean.setFechaFin(mpp.getFechaFin());
	// movimientoPeridodicoPlanBean.setFechaInicio(mpp.getFechaInicio());
	// movimientoPeridodicoPlanBean.setPeriodidadEjecucion(mpp.getPeriodidadEjecucion());
	// movimientoPeridodicoPlanBean.setSignoMovimiento(mpp.getSignoMovimiento());
	// movimientoPeridodicoPlanBean.setTipoMovimiento(mpp.getTipoMovimiento());
	// movimientoPeriodicoPlansList.add(movimientoPeridodicoPlanBean);
	// }
	// }

	

	public void crearRegistroMovientoPlan() {
		movimientoNuevo = new MovimientoPlanBean();
	}

	public void crearRegistroMovientoPeriodicoPlan() {
		movimientoPeriodicoNuevo = new MovimientoPeriodicoPlanBean();
	}

	public void grabarMovimientoPlan() {
		try {
			if (tipoentorno.equals(TipoEntorno.MOVIMIENTO)) {
				movimientoNuevo.setTipoMovimiento(TipoMovimiento.MOVIMIENTO_A_FECHA.getValue());
				movimientoNuevo.setFechaFin(movimientoNuevo.getFechaInicio());
				MovimientoPlan movimientoPlan = new MovimientoPlan();
				movimientoPlan.setDescripcion(movimientoNuevo.getDescripcion());
				movimientoPlan.setDivisa(movimientoNuevo.getDivisa());
				movimientoPlan.setFechaFin(movimientoNuevo.getFechaFin());
				movimientoPlan.setFechaInicio(movimientoNuevo.getFechaInicio());
				movimientoPlan.setSignoMovimiento(movimientoNuevo.getSignoMovimiento());
				movimientoPlan.setTipoMovimiento(movimientoNuevo.getTipoMovimiento());
				movimientoPlan.setTotalMovimiento(movimientoNuevo.getTotalMovimiento());
				movimientoPlan.setUsuario(userSessionBean.getUsuario());
				movimientoPlanService.addMovimientoPlan(movimientoPlan);
				movimientoPlansList.add(movimientoNuevo);
			} else {
				movimientoNuevo.setTipoMovimiento(TipoMovimiento.MOVIMIENTO_A_FECHA.getValue());
				movimientoNuevo.setFechaFin(movimientoNuevo.getFechaInicio());
				MovimientoPlanImagen movimientoPlan = new MovimientoPlanImagen();
				movimientoPlan.setDescripcion(movimientoNuevo.getDescripcion());
				movimientoPlan.setDivisa(movimientoNuevo.getDivisa());
				movimientoPlan.setFechaFin(movimientoNuevo.getFechaFin());
				movimientoPlan.setFechaInicio(movimientoNuevo.getFechaInicio());
				movimientoPlan.setSignoMovimiento(movimientoNuevo.getSignoMovimiento());
				movimientoPlan.setTipoMovimiento(movimientoNuevo.getTipoMovimiento());
				movimientoPlan.setTotalMovimiento(movimientoNuevo.getTotalMovimiento());
				movimientoPlan.setPlan(planSeleccionadoCombo);
				movimientoPlanImagenService.addMovimientoPlanImagen(movimientoPlan);
				movimientoPlansList.add(movimientoNuevo);
			}
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Grabado", "Grabado movimiento");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error grabado movimiento");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void grabarMovimientoPeriodicoPlan() {
		try {
			if (tipoentorno.equals(TipoEntorno.MOVIMIENTO)) {
				MovimientoPeriodicoPlan movimientoPPlan = new MovimientoPeriodicoPlan();
				movimientoPPlan.setCantidad(movimientoPeriodicoNuevo.getCantidad());
				movimientoPPlan.setDescripcion(movimientoPeriodicoNuevo.getDescripcion());
				movimientoPPlan.setDiaEjecucion(movimientoPeriodicoNuevo.getDiaEjecucion());
				movimientoPPlan.setDivisa(movimientoPeriodicoNuevo.getDivisa());
				movimientoPPlan.setFechaFin(movimientoPeriodicoNuevo.getFechaFin());
				movimientoPPlan.setFechaInicio(movimientoPeriodicoNuevo.getFechaInicio());
				movimientoPPlan.setPeriodidadEjecucion(movimientoPeriodicoNuevo.getPeriodidadEjecucion());
				movimientoPPlan.setSignoMovimiento(movimientoPeriodicoNuevo.getSignoMovimiento());
				movimientoPPlan.setTipoMovimiento(movimientoPeriodicoNuevo.getTipoMovimiento());
				movimientoPPlan.setUsuario(userSessionBean.getUsuario());
				movimientoPeriodicoPlanService.addMovimientoPeriodicoPlan(movimientoPPlan);
				movimientoPeriodicoPlansList.add(movimientoPeriodicoNuevo);
			} else {
				MovimientoPeriodicoPlanImagen movimientoPPlan = new MovimientoPeriodicoPlanImagen();
				movimientoPPlan.setCantidad(movimientoPeriodicoNuevo.getCantidad());
				movimientoPPlan.setDescripcion(movimientoPeriodicoNuevo.getDescripcion());
				movimientoPPlan.setDiaEjecucion(movimientoPeriodicoNuevo.getDiaEjecucion());
				movimientoPPlan.setDivisa(movimientoPeriodicoNuevo.getDivisa());
				movimientoPPlan.setFechaFin(movimientoPeriodicoNuevo.getFechaFin());
				movimientoPPlan.setFechaInicio(movimientoPeriodicoNuevo.getFechaInicio());
				movimientoPPlan.setPeriodidadEjecucion(movimientoPeriodicoNuevo.getPeriodidadEjecucion());
				movimientoPPlan.setSignoMovimiento(movimientoPeriodicoNuevo.getSignoMovimiento());
				movimientoPPlan.setTipoMovimiento(movimientoPeriodicoNuevo.getTipoMovimiento());
				movimientoPPlan.setPlan(planSeleccionadoCombo);
				movimientoPeriodicoPlanImagenService.addMovimientoPeriodicoPlanImagen(movimientoPPlan);
				movimientoPeriodicoPlansList.add(movimientoPeriodicoNuevo);
			}
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Grabado", "Grabado movimiento periodico");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error grabado movimiento periodico");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void borrarMov(MovimientoPlanBean selectedMovimientoPlan) {
		if (selectedMovimientoPlan.getTipoentorno().equals(TipoEntorno.MOVIMIENTO)) {
			MovimientoPlan movimientoPlan = movimientoPlanService.getMovimientoPlanById(selectedMovimientoPlan.getId());
			movimientoPlanService.borrarMovimientoPlan(movimientoPlan);
			movimientoPlansList.remove(selectedMovimientoPlan);
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Borrado", "Borrado movimiento");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			MovimientoPlanImagen movimientoPlan = movimientoPlanImagenService
					.getMovimientoPlanImagenById(selectedMovimientoPlan.getId());
			movimientoPlanImagenService.borrarMovimientoPlanImagen(movimientoPlan);
			movimientoPlansList.remove(selectedMovimientoPlan);
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Borrado", "Borrado movimiento plan");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void borrarMovPeriodico(MovimientoPeriodicoPlanBean selectedMovimientoPeriodicoPlan) {
		if (selectedMovimientoPeriodicoPlan.getTipoentorno().equals(TipoEntorno.MOVIMIENTO)) {
			MovimientoPeriodicoPlan movimientoPeriodicoPlan = movimientoPeriodicoPlanService
					.getMovimientoPeriodicoPlanById(selectedMovimientoPeriodicoPlan.getId());
			movimientoPeriodicoPlanService.borrarMovimientoPeriodicoPlan(movimientoPeriodicoPlan);
			movimientoPeriodicoPlansList.remove(selectedMovimientoPeriodicoPlan);
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Borrado", "Borrado movimiento periodico");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			MovimientoPeriodicoPlanImagen movimientoPeriodicoPlanImagen = movimientoPeriodicoPlanImagenService
					.getMovimientoPeriodicoPlanImagenById(selectedMovimientoPeriodicoPlan.getId());
			movimientoPeriodicoPlanImagenService.borrarMovimientoPeriodicoPlanImagen(movimientoPeriodicoPlanImagen);
			movimientoPeriodicoPlansList.remove(selectedMovimientoPeriodicoPlan);
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Borrado", "Borrado movimiento periodico");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void grabarPlanFuturo() {
		List<Plan> planes = planService.getPlanDesdeDescripcionExacta(plan.getDescripcionPlan(),
				userSessionBean.getUsuario());
		if (planes != null && !planes.isEmpty()) {
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
					"Error grabado plan. Ya existe uno con la misma descripción");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return;
		}
		// Plan con descripcion asignada
		plan.setUsuario(userSessionBean.getUsuario());

		planService.addPlan(plan);

		for (MovimientoPlanBean movimientoPlan : movimientoPlansListDropped) {
			MovimientoPlanImagen movimientoPlanImagen = new MovimientoPlanImagen();
			movimientoPlanImagen.setDescripcion(movimientoPlan.getDescripcion());
			movimientoPlanImagen.setDivisa(movimientoPlan.getDivisa());
			movimientoPlanImagen.setFechaFin(movimientoPlan.getFechaFin());
			movimientoPlanImagen.setFechaInicio(movimientoPlan.getFechaInicio());
			movimientoPlanImagen.setPlan(plan);
			movimientoPlanImagen.setSignoMovimiento(movimientoPlan.getSignoMovimiento());
			movimientoPlanImagen.setTipoMovimiento(movimientoPlan.getTipoMovimiento());
			movimientoPlanImagen.setTotalMovimiento(movimientoPlan.getTotalMovimiento());
			movimientoPlanImagenService.addMovimientoPlanImagen(movimientoPlanImagen);
		}
		for (MovimientoPeriodicoPlanBean movimientoPeriodicoPlan : movimientoPeriodicoPlansListDropped) {

			MovimientoPeriodicoPlanImagen movimientoPeriodicoPlanImagen = new MovimientoPeriodicoPlanImagen();
			movimientoPeriodicoPlanImagen.setCantidad(movimientoPeriodicoPlan.getCantidad());
			movimientoPeriodicoPlanImagen.setDescripcion(movimientoPeriodicoPlan.getDescripcion());
			movimientoPeriodicoPlanImagen.setDiaEjecucion(movimientoPeriodicoPlan.getDiaEjecucion());
			movimientoPeriodicoPlanImagen.setDivisa(movimientoPeriodicoPlan.getDivisa());
			movimientoPeriodicoPlanImagen.setFechaFin(movimientoPeriodicoPlan.getFechaFin());
			movimientoPeriodicoPlanImagen.setFechaInicio(movimientoPeriodicoPlan.getFechaInicio());
			movimientoPeriodicoPlanImagen.setPeriodidadEjecucion(movimientoPeriodicoPlan.getPeriodidadEjecucion());
			movimientoPeriodicoPlanImagen.setSignoMovimiento(movimientoPeriodicoPlan.getSignoMovimiento());
			movimientoPeriodicoPlanImagen.setTipoMovimiento(movimientoPeriodicoPlan.getTipoMovimiento());
			movimientoPeriodicoPlanImagen.setPlan(plan);
			movimientoPeriodicoPlanImagenService.addMovimientoPeriodicoPlanImagen(movimientoPeriodicoPlanImagen);
		}

		if (movimientoInicial != null && movimientoInicial.getTotalMovimiento() != null) {
			MovimientoImagen movimientoImagen = new MovimientoImagen();
			movimientoImagen.setDescripcion(movimientoInicial.getDescripcion());
			movimientoImagen.setDivisa(movimientoInicial.getDivisa());
			movimientoImagen.setFechaFin(movimientoInicial.getFechaFin());
			movimientoImagen.setFechaInicio(movimientoInicial.getFechaInicio());
			movimientoImagen.setPlan(plan);
			movimientoImagen.setSignoMovimiento(movimientoInicial.getSignoMovimiento());
			movimientoImagen.setTipoMovimiento(movimientoInicial.getTipoMovimiento());
			movimientoImagen.setTotalMovimiento(movimientoInicial.getTotalMovimiento());
			movimientoImagenService.addMovimientoImagen(movimientoImagen);

		}
	}

	public String getPlanCombo() {
		return planCombo;
	}

	public void setPlanCombo(String planCombo) {
		this.planCombo = planCombo;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public IPlanService getPlanService() {
		return planService;
	}

	public void setPlanService(IPlanService planService) {
		this.planService = planService;
	}

	public IMovimientoService getMovimientoService() {
		return movimientoService;
	}

	public void setMovimientoService(IMovimientoService movimientoService) {
		this.movimientoService = movimientoService;
	}

	public MovimientoBean getMovimientoInicial() {
		return movimientoInicial;
	}

	public void setMovimientoInicial(MovimientoBean movimientoInicial) {
		this.movimientoInicial = movimientoInicial;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public TipoEntorno getTipoentorno() {
		return tipoentorno;
	}

	public void setTipoentorno(TipoEntorno tipoentorno) {
		this.tipoentorno = tipoentorno;
	}

	public IMovimientoImagenService getMovimientoImagenService() {
		return movimientoImagenService;
	}

	public void setMovimientoImagenService(IMovimientoImagenService movimientoImagenService) {
		this.movimientoImagenService = movimientoImagenService;
	}

	public IMovimientoPlanImagenService getMovimientoPlanImagenService() {
		return movimientoPlanImagenService;
	}

	public void setMovimientoPlanImagenService(IMovimientoPlanImagenService movimientoPlanImagenService) {
		this.movimientoPlanImagenService = movimientoPlanImagenService;
	}

	public IMovimientoPeriodicoPlanImagenService getMovimientoPeriodicoPlanImagenService() {
		return movimientoPeriodicoPlanImagenService;
	}

	public void setMovimientoPeriodicoPlanImagenService(
			IMovimientoPeriodicoPlanImagenService movimientoPeriodicoPlanImagenService) {
		this.movimientoPeriodicoPlanImagenService = movimientoPeriodicoPlanImagenService;
	}

	public Plan getPlanSeleccionadoCombo() {
		return planSeleccionadoCombo;
	}

	public void setPlanSeleccionadoCombo(Plan planSeleccionadoCombo) {
		this.planSeleccionadoCombo = planSeleccionadoCombo;
	}

	public List<Plan> getPlanesList() {
		return planesList;
	}

	public void setPlanesList(List<Plan> planesList) {
		this.planesList = planesList;
	}

	public MovimientoPlanBean getMovimientoNuevo() {
		return movimientoNuevo;
	}

	public void setMovimientoNuevo(MovimientoPlanBean movimientoNuevo) {
		this.movimientoNuevo = movimientoNuevo;
	}

	public MovimientoPeriodicoPlanBean getMovimientoPeriodicoNuevo() {
		return movimientoPeriodicoNuevo;
	}

	public void setMovimientoPeriodicoNuevo(MovimientoPeriodicoPlanBean movimientoPeriodicoNuevo) {
		this.movimientoPeriodicoNuevo = movimientoPeriodicoNuevo;
	}

	public MovimientoPlanBean getSelectedMovimientoPlan() {
		return selectedMovimientoPlan;
	}

	public void setSelectedMovimientoPlan(MovimientoPlanBean selectedMovimientoPlan) {
		this.selectedMovimientoPlan = selectedMovimientoPlan;
	}

	public MovimientoPeriodicoPlanBean getSelectedMovimientoPeriodicoPlan() {
		return selectedMovimientoPeriodicoPlan;
	}

	public void setSelectedMovimientoPeriodicoPlan(MovimientoPeriodicoPlanBean selectedMovimientoPeriodicoPlan) {
		this.selectedMovimientoPeriodicoPlan = selectedMovimientoPeriodicoPlan;
	}

	/**
	 * @return Contenedor de datos del usuario conectado
	 */
	public UserSessionBean getUserSessionBean() {
		return userSessionBean;
	}

	/**
	 * @param userSessionBean
	 *            Contenedor de datos del usuario conectado
	 */
	public void setUserSessionBean(UserSessionBean userSessionBean) {
		this.userSessionBean = userSessionBean;
	}

	public ICalculadoraPlanService getCalculadoraPlanService() {
		return calculadoraPlanService;
	}

	public void setCalculadoraPlanService(ICalculadoraPlanService calculadoraPlanService) {
		this.calculadoraPlanService = calculadoraPlanService;
	}

	public IMovimientoPlanService getMovimientoPlanService() {
		return movimientoPlanService;
	}

	public void setMovimientoPlanService(IMovimientoPlanService movimientoPlanService) {
		this.movimientoPlanService = movimientoPlanService;
	}

	public IMovimientoPeriodicoPlanService getMovimientoPeriodicoPlanService() {
		return movimientoPeriodicoPlanService;
	}

	public void setMovimientoPeriodicoPlanService(IMovimientoPeriodicoPlanService movimientoPeriodicoPlanService) {
		this.movimientoPeriodicoPlanService = movimientoPeriodicoPlanService;
	}

	public List<MovimientoPlanBean> getMovimientoPlansList() {
		return movimientoPlansList;
	}

	public void setMovimientoPlansList(List<MovimientoPlanBean> movimientoPlansList) {
		this.movimientoPlansList = movimientoPlansList;
	}

	public List<MovimientoPeriodicoPlanBean> getMovimientoPeriodicoPlansList() {
		return movimientoPeriodicoPlansList;
	}

	public void setMovimientoPeriodicoPlansList(List<MovimientoPeriodicoPlanBean> movimientoPeriodicoPlansList) {
		this.movimientoPeriodicoPlansList = movimientoPeriodicoPlansList;
	}

	public List<MovimientoPlanBean> getMovimientoPlansListDropped() {
		return movimientoPlansListDropped;
	}

	public void setMovimientoPlansListDropped(List<MovimientoPlanBean> movimientoPlansListDropped) {
		this.movimientoPlansListDropped = movimientoPlansListDropped;
	}

	public List<MovimientoPeriodicoPlanBean> getMovimientoPeriodicoPlansListDropped() {
		return movimientoPeriodicoPlansListDropped;
	}

	public void setMovimientoPeriodicoPlansListDropped(
			List<MovimientoPeriodicoPlanBean> movimientoPeriodicoPlansListDropped) {
		this.movimientoPeriodicoPlansListDropped = movimientoPeriodicoPlansListDropped;
	}

}
