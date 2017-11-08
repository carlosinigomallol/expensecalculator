package es.expensecalculator.web.action;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.expensecalculator.web.param.EntityParam;

// TODO: Auto-generated Javadoc
/**
 * The Class SearchFilterManagerBean.
 */
@ManagedBean(name = "searchFilterManagerBean")
@SessionScoped
public class SearchFilterManagerBean implements Serializable {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchFilterManagerBean.class);

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The entity param filter. */
    private EntityParam<?> entityParamFilter;

    /**
     * Gets the last filter.
     * 
     * @return the last filter
     */
    public EntityParam<?> getLastFilter() {
        return entityParamFilter;
    }

    /**
     * Sets the last filter.
     * 
     * @param entityParamFilter the new last filter
     */
    public void setLastFilter(EntityParam<?> entityParamFilter) {
        this.entityParamFilter = entityParamFilter;
    }

}
