package es.expensecalculator.web.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.expensecalculator.model.Movimiento;
import es.expensecalculator.service.IMovimientoService;
import es.expensecalculator.web.param.MovimientoParam;
import es.expensecalculator.web.security.UserSessionBean;

/**
 * Manejador de la consulta de movimientoes
 */
@ManagedBean(name = "movimientoListManagedBean")
@ViewScoped
public class MovimientoListManagedBean extends ComunListados implements Serializable {

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(MovimientoListManagedBean.class);

	/** Número de serie por defecto */
	private static final long serialVersionUID = 1L;

	/** Conenedor de los datos del usuario conectado */
	@ManagedProperty(value = "#{userSessionBean}")
	UserSessionBean userSessionBean;

	/** Servicios de persistencia de las movimientoes */
	@ManagedProperty(value = "#{MovimientoService}")
	IMovimientoService movimientoService;

	/** Lista de movimientoes consultadas */
	private List<Movimiento> movimientosList;

	/** Contenedor de los criterios de búsqueda */
	protected MovimientoParam filter;

	/** Declaración seleccionada */
	private Movimiento selected;

	/** Declaración seleccionada */
	private Movimiento movimientoSelected;

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
			setFilter((MovimientoParam) getSearchFilterManagerBean().getLastFilter());
			setMovimientosList(
					getMovimientoService().getMovimientoListWithRestrictions(getFilter()));
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
		setMovimientoSelected(null);
	}

	/**
	 * Inicializa el contenedor de criterios de búsqueda de movimientoes
	 * 
	 * @throws ModelosAEATCriticalException
	 */
	private void inicializaFiltro() {
		setFilter(new MovimientoParam());
	}

	/**
	 * Find. Al pulsar el boton de busqueda se ejecuta el listado y se guardan
	 * los parametro como ultimo filtro de busqueda por si se accede y se vuelve
	 * a la pagina anterior
	 */
	public void find() {
		setSelected(null);
		setMovimientosList(getMovimientoService().getMovimientoListWithRestrictions(getFilter()));
		fillFilter(getFilter());
	}

	/**
	 * @return Lista de movimientoes consultadas
	 */
	public List<Movimiento> getMovimientosList() {
		return movimientosList;
	}

	/**
	 * @param movimientosList
	 *            Lista de movimientoes consultadas
	 */
	public void setMovimientosList(List<Movimiento> movimientosList) {
		this.movimientosList = movimientosList;
	}

	/**
	 * @return Servicios de persistencia de las movimientoes
	 */
	public IMovimientoService getMovimientoService() {
		return movimientoService;
	}

	/**
	 * @param movimientoService
	 *            Servicios de persistencia de las movimientoes
	 */
	public void setMovimientoService(IMovimientoService movimientoService) {
		this.movimientoService = movimientoService;
	}

	/**
	 * @return Declaración seleccionada
	 */
	public Movimiento getSelected() {
		return selected;
	}

	/**
	 * @param selected
	 *            Declaración seleccionada
	 */
	public void setSelected(Movimiento selected) {
		this.selected = selected;
	}

	/**
	 * @return Declaración seleccionada
	 */
	public Movimiento getMovimientoSelected() {
		return movimientoSelected;
	}

	/**
	 * @param movimientoSelected
	 *            Declaración seleccionada
	 */
	public void setMovimientoSelected(Movimiento movimientoSelected) {
		this.movimientoSelected = movimientoSelected;
	}

	/**
	 * @return Contenedor de los criterios de búsqueda
	 */
	public MovimientoParam getFilter() {
		return filter;
	}

	/**
	 * @param filter
	 *            Contenedor de los criterios de búsqueda
	 */
	public void setFilter(MovimientoParam filter) {
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
