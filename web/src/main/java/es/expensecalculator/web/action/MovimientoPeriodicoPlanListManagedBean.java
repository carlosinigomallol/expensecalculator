package es.expensecalculator.web.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.expensecalculator.model.MovimientoPeriodicoPlan;
import es.expensecalculator.service.IMovimientoPeriodicoPlanService;
import es.expensecalculator.web.param.MovimientoPeriodicoPlanParam;
import es.expensecalculator.web.security.UserSessionBean;

/**
 * Manejador de la consulta de movimientoPeriodicoPlanes
 */
@ManagedBean(name = "movimientoPeriodicoPlanListManagedBean")
@ViewScoped
public class MovimientoPeriodicoPlanListManagedBean extends ComunListados implements Serializable {

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(MovimientoPeriodicoPlanListManagedBean.class);

	/** Número de serie por defecto */
	private static final long serialVersionUID = 1L;

	/** Conenedor de los datos del usuario conectado */
	@ManagedProperty(value = "#{userSessionBean}")
	UserSessionBean userSessionBean;

	/** Servicios de persistencia de las movimientoPeriodicoPlanes */
	@ManagedProperty(value = "#{MovimientoPeriodicoPlanService}")
	IMovimientoPeriodicoPlanService movimientoPeriodicoPlanService;

	/** Lista de movimientoPeriodicoPlanes consultadas */
	private List<MovimientoPeriodicoPlan> movimientoPeriodicoPlansList;

	/** Contenedor de los criterios de búsqueda */
	protected MovimientoPeriodicoPlanParam filter;

	/** Declaración seleccionada */
	private MovimientoPeriodicoPlan selected;

	/** Declaración seleccionada */
	private MovimientoPeriodicoPlan movimientoPeriodicoPlanSelected;

	/**
	 * Inicializa el filtro de busqueda para la pagina. Busca en caso de que
	 * hubiera filtro anterior en el objeto de session que guarda siempre el
	 * ultimo filtro de busqueda ejecutado. Si existe se asigna y se ejecuta la
	 * busqueda aplicando el filtro
	 * 
	 * @throws ModelosAEATCriticalException
	 */
	@PostConstruct
	public void init() throws Exception {
		createFilter();
		if (checkIsExistsLastSearch()) {
			setFilter((MovimientoPeriodicoPlanParam) getSearchFilterManagerBean().getLastFilter());
			setMovimientoPeriodicoPlansList(
					getMovimientoPeriodicoPlanService().getMovimientoPeriodicoPlanListWithRestrictions(getFilter()));
		}
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
		setSelected(null);
		setMovimientoPeriodicoPlanSelected(null);
	}

	/**
	 * Inicializa el contenedor de criterios de búsqueda de
	 * movimientoPeriodicoPlanes
	 * 
	 * @throws ModelosAEATCriticalException
	 */
	private void inicializaFiltro() {
		setFilter(new MovimientoPeriodicoPlanParam());
	}

	/**
	 * Find. Al pulsar el boton de busqueda se ejecuta el listado y se guardan
	 * los parametro como ultimo filtro de busqueda por si se accede y se vuelve
	 * a la pagina anterior
	 */
	public void find() {
		setSelected(null);
		setMovimientoPeriodicoPlansList(
				getMovimientoPeriodicoPlanService().getMovimientoPeriodicoPlanListWithRestrictions(getFilter()));
		fillFilter(getFilter());
	}

	/**
	 * @return Lista de movimientoPeriodicoPlanes consultadas
	 */
	public List<MovimientoPeriodicoPlan> getMovimientoPeriodicoPlansList() {
		return movimientoPeriodicoPlansList;
	}

	/**
	 * @param movimientoPeriodicoPlansList
	 *            Lista de movimientoPeriodicoPlanes consultadas
	 */
	public void setMovimientoPeriodicoPlansList(List<MovimientoPeriodicoPlan> movimientoPeriodicoPlansList) {
		this.movimientoPeriodicoPlansList = movimientoPeriodicoPlansList;
	}

	/**
	 * @return Servicios de persistencia de las movimientoPeriodicoPlanes
	 */
	public IMovimientoPeriodicoPlanService getMovimientoPeriodicoPlanService() {
		return movimientoPeriodicoPlanService;
	}

	/**
	 * @param movimientoPeriodicoPlanService
	 *            Servicios de persistencia de las movimientoPeriodicoPlanes
	 */
	public void setMovimientoPeriodicoPlanService(IMovimientoPeriodicoPlanService movimientoPeriodicoPlanService) {
		this.movimientoPeriodicoPlanService = movimientoPeriodicoPlanService;
	}

	/**
	 * @return Declaración seleccionada
	 */
	public MovimientoPeriodicoPlan getSelected() {
		return selected;
	}

	/**
	 * @param selected
	 *            Declaración seleccionada
	 */
	public void setSelected(MovimientoPeriodicoPlan selected) {
		this.selected = selected;
	}

	/**
	 * @return Declaración seleccionada
	 */
	public MovimientoPeriodicoPlan getMovimientoPeriodicoPlanSelected() {
		return movimientoPeriodicoPlanSelected;
	}

	/**
	 * @param movimientoPeriodicoPlanSelected
	 *            Declaración seleccionada
	 */
	public void setMovimientoPeriodicoPlanSelected(MovimientoPeriodicoPlan movimientoPeriodicoPlanSelected) {
		this.movimientoPeriodicoPlanSelected = movimientoPeriodicoPlanSelected;
	}

	/**
	 * @return Contenedor de los criterios de búsqueda
	 */
	public MovimientoPeriodicoPlanParam getFilter() {
		return filter;
	}

	/**
	 * @param filter
	 *            Contenedor de los criterios de búsqueda
	 */
	public void setFilter(MovimientoPeriodicoPlanParam filter) {
		this.filter = filter;
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

}
