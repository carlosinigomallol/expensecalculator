package es.expensecalculator.web.action;

import javax.faces.bean.ManagedProperty;

import es.expensecalculator.web.param.EntityParam;


public class ComunListados {

    /** The search filter manager bean. */
    @ManagedProperty(value = "#{searchFilterManagerBean}")
    protected SearchFilterManagerBean searchFilterManagerBean;

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

    /**
     * Check is exists last search.
     * Busca si existe ultimo filtro de busqueda
     * 
     * @return true, if successful
     */
    protected boolean checkIsExistsLastSearch() {
        //Si existe ultimo entonces lo establezco como filtro
        if (searchFilterManagerBean.getLastFilter() != null
                && (searchFilterManagerBean.getLastFilter().isFilledEntityParam() || searchFilterManagerBean.getLastFilter().isButtonPressed())) {
            return true;
        }
        return false;
    }

    protected void fillFilter(EntityParam<?> entity) {
        entity.setButtonPressed(true);
        searchFilterManagerBean.setLastFilter(entity);
    }
}
