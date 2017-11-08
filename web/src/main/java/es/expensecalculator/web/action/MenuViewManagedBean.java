package es.expensecalculator.web.action;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuViewManagedBean.
 */
@ManagedBean(name = "menuViewManagedBean")
@ViewScoped
public class MenuViewManagedBean {

    /** The search filter manager bean. */
    @ManagedProperty(value = "#{searchFilterManagerBean}")
    private SearchFilterManagerBean searchFilterManagerBean;

    /**
     * Inits the.
     */
    @PostConstruct
    public void init() {

    }

    /**
     * Redirect previous action.
     */
    private void redirectPreviousAction() {
        searchFilterManagerBean.setLastFilter(null);
    }

    
    
    public String redirectToMovimientoList() {
        redirectPreviousAction();
        return "/pages/modelosaeat/MovimientoList.xhtml?faces-redirect=true";
    }

    
    public String redirectToCalculadoraFuturoGestion() {
        redirectPreviousAction();
        return "/pages/modelosaeat/CalculadoraFuturoGestion.xhtml?faces-redirect=true";
    }
    
    public String redirectToMovimientoPeriodicoList() {
        redirectPreviousAction();
        return "/pages/modelosaeat/MovimientoPeriodicoList.xhtml?faces-redirect=true";
    }
    
    
    public String redirectToMovimientoPlanList() {
        redirectPreviousAction();
        return "/pages/modelosaeat/MovimientoPlanList.xhtml?faces-redirect=true";
    }
    
    
    public String redirectToMovimientoPeriodicoPlanList() {
        redirectPreviousAction();
        return "/pages/modelosaeat/MovimientoPeriodicoPlanList.xhtml?faces-redirect=true";
    }

    /**
     * Gets the search filter manager bean.
     * 
     * @return the search filter manager bean
     */
    public SearchFilterManagerBean getSearchFilterManagerBean() {
        return searchFilterManagerBean;
    }

    /**
     * Sets the search filter manager bean.
     * 
     * @param searchFilterManagerBean the new search filter manager bean
     */
    public void setSearchFilterManagerBean(SearchFilterManagerBean searchFilterManagerBean) {
        this.searchFilterManagerBean = searchFilterManagerBean;
    }

}
