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

import es.expensecalculator.model.MovimientoPeriodicoPlan;
import es.expensecalculator.service.IMovimientoPeriodicoPlanService;

// TODO: Auto-generated Javadoc
/**
 * The Class MovimientoPeriodicoPlanConverter.
 */
@ManagedBean(name = "movimientoPeriodicoPlanConverter")
@RequestScoped
@FacesConverter(value = "movimientoPeriodicoPlanConverter")
public class MovimientoPeriodicoPlanConverter implements Converter {

    /** The movimientoPeriodicoPlan service. */
    @ManagedProperty(value = "#{MovimientoPeriodicoPlanService}")
    private IMovimientoPeriodicoPlanService movimientoPeriodicoPlanService;

    /**
     * Convierte un string (id de movimientoPeriodicoPlan) en un objeto del modelo.
     * 
     * @param facesContext the faces context
     * @param comp the comp
     * @param value the value
     * @return the as object
     */
    public Object getAsObject(FacesContext facesContext, UIComponent comp, String value) {
        if (value != null && !"".equals(value)) {
            try {
                Long movimientoPeriodicoPlanid = Long.parseLong(value);
                MovimientoPeriodicoPlan movimientoPeriodicoPlan = movimientoPeriodicoPlanService.getMovimientoPeriodicoPlanById(movimientoPeriodicoPlanid);
                return movimientoPeriodicoPlan;
            } catch (NumberFormatException nfe) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "No es un movimientoPeriodicoPlan valido "
                        + nfe.getMessage()));
            } catch (Exception nfe) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "No es un movimientoPeriodicoPlan valido "
                        + nfe.getMessage()));
            }
        }
        return null;
    }

    /**
     * Convierte la movimientoPeriodicoPlan del modelo en un string (su id) apto para usar en la vista.
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
        if (value instanceof MovimientoPeriodicoPlan) {
            Long key = ((MovimientoPeriodicoPlan) value).getId();
            return key != null ? key.toString() : null;
        } else {
            return null;
        }
    }

    /**
     * Gets the movimientoPeriodicoPlan service.
     * 
     * @return the movimientoPeriodicoPlan service
     */
    public IMovimientoPeriodicoPlanService getMovimientoPeriodicoPlanService() {
        return movimientoPeriodicoPlanService;
    }

    /**
     * Sets the movimientoPeriodicoPlan service.
     * 
     * @param movimientoPeriodicoPlanService the new movimientoPeriodicoPlan service
     */
    public void setMovimientoPeriodicoPlanService(IMovimientoPeriodicoPlanService movimientoPeriodicoPlanService) {
        this.movimientoPeriodicoPlanService = movimientoPeriodicoPlanService;
    }

}
