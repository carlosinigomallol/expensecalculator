package es.expensecalculator.web.converters;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import es.expensecalculator.model.Plan;
import es.expensecalculator.service.IPlanService;

// TODO: Auto-generated Javadoc
/**
 * The Class PlanConverter.
 */
@ManagedBean(name = "planConverter")
@RequestScoped
@FacesConverter(value = "planConverter")
public class PlanConverter implements Converter {

    /** The plan service. */
    @ManagedProperty(value = "#{PlanService}")
    private IPlanService planService;

    /**
     * Convierte un string (id de plan) en un objeto del modelo.
     * 
     * @param facesContext the faces context
     * @param comp the comp
     * @param value the value
     * @return the as object
     */
    public Object getAsObject(FacesContext facesContext, UIComponent comp, String value) {
        if (value != null && !"".equals(value)) {
            try {
                Long planid = Long.parseLong(value);
                Plan plan = planService.getPlanById(planid);
                return plan;
            } catch (NumberFormatException nfe) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "No es un plan valido "
                        + nfe.getMessage()));
            } catch (Exception nfe) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "No es un plan valido "
                        + nfe.getMessage()));
            }
        }
        return null;
    }

    /**
     * Convierte la plan del modelo en un string (su id) apto para usar en la vista.
     * 
     * @param facesContext the faces context
     * @param comp the comp
     * @param value the value
     * @return the as string
     */
    public String getAsString(FacesContext facesContext, UIComponent comp, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Plan) {
            Long key = ((Plan) value).getId();
            return key != null ? key.toString() : null;
        } else {
            return null;
        }
    }

    /**
     * Gets the plan service.
     * 
     * @return the plan service
     */
    public IPlanService getPlanService() {
        return planService;
    }

    /**
     * Sets the plan service.
     * 
     * @param planService the new plan service
     */
    public void setPlanService(IPlanService planService) {
        this.planService = planService;
    }

}
