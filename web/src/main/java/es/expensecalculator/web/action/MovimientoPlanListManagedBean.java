package es.expensecalculator.web.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.expensecalculator.model.MovimientoPlan;
import es.expensecalculator.service.IMovimientoPlanService;
import es.expensecalculator.web.param.MovimientoPlanParam;
import es.expensecalculator.web.security.UserSessionBean;

/**
 * Manejador de la consulta de movimientoPlanes
 */
@ManagedBean(name = "movimientoPlanListManagedBean")
@ViewScoped
public class MovimientoPlanListManagedBean extends ComunListados implements Serializable {

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(MovimientoPlanListManagedBean.class);

	/** Número de serie por defecto */
	private static final long serialVersionUID = 1L;

	/** Conenedor de los datos del usuario conectado */
	@ManagedProperty(value = "#{userSessionBean}")
	UserSessionBean userSessionBean;

	/** Servicios de persistencia de las movimientoPlanes */
	@ManagedProperty(value = "#{MovimientoPlanService}")
	IMovimientoPlanService movimientoPlanService;

	/** Lista de movimientoPlanes consultadas */
	private List<MovimientoPlan> movimientoPlansList;

	/** Contenedor de los criterios de búsqueda */
	protected MovimientoPlanParam filter;

	/** Declaración seleccionada */
	private MovimientoPlan selected;

	/** Declaración seleccionada */
	private MovimientoPlan movimientoPlanSelected;

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
			setFilter((MovimientoPlanParam) getSearchFilterManagerBean().getLastFilter());
			setMovimientoPlansList(getMovimientoPlanService().getMovimientoPlanListWithRestrictions(getFilter()));
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
		setMovimientoPlanSelected(null);
	}

	/**
	 * Inicializa el contenedor de criterios de búsqueda de movimientoPlanes
	 * 
	 * @throws ModelosAEATCriticalException
	 */
	private void inicializaFiltro() {
		setFilter(new MovimientoPlanParam());
	}

	/**
	 * Find. Al pulsar el boton de busqueda se ejecuta el listado y se guardan
	 * los parametro como ultimo filtro de busqueda por si se accede y se vuelve
	 * a la pagina anterior
	 */
	public void find() {
		setSelected(null);
		setMovimientoPlansList(getMovimientoPlanService().getMovimientoPlanListWithRestrictions(getFilter()));
		fillFilter(getFilter());
	}

	/**
	 * @return Lista de movimientoPlanes consultadas
	 */
	public List<MovimientoPlan> getMovimientoPlansList() {
		return movimientoPlansList;
	}

	/**
	 * @param movimientoPlansList
	 *            Lista de movimientoPlanes consultadas
	 */
	public void setMovimientoPlansList(List<MovimientoPlan> movimientoPlansList) {
		this.movimientoPlansList = movimientoPlansList;
	}

	/**
	 * @return Servicios de persistencia de las movimientoPlanes
	 */
	public IMovimientoPlanService getMovimientoPlanService() {
		return movimientoPlanService;
	}

	/**
	 * @param movimientoPlanService
	 *            Servicios de persistencia de las movimientoPlanes
	 */
	public void setMovimientoPlanService(IMovimientoPlanService movimientoPlanService) {
		this.movimientoPlanService = movimientoPlanService;
	}

	/**
	 * @return Declaración seleccionada
	 */
	public MovimientoPlan getSelected() {
		return selected;
	}

	/**
	 * @param selected
	 *            Declaración seleccionada
	 */
	public void setSelected(MovimientoPlan selected) {
		this.selected = selected;
	}

	/**
	 * @return Declaración seleccionada
	 */
	public MovimientoPlan getMovimientoPlanSelected() {
		return movimientoPlanSelected;
	}

	/**
	 * @param movimientoPlanSelected
	 *            Declaración seleccionada
	 */
	public void setMovimientoPlanSelected(MovimientoPlan movimientoPlanSelected) {
		this.movimientoPlanSelected = movimientoPlanSelected;
	}

	/**
	 * @return Contenedor de los criterios de búsqueda
	 */
	public MovimientoPlanParam getFilter() {
		return filter;
	}

	/**
	 * @param filter
	 *            Contenedor de los criterios de búsqueda
	 */
	public void setFilter(MovimientoPlanParam filter) {
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
