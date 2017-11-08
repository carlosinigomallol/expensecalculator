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

import es.expensecalculator.model.Movimiento;
import es.expensecalculator.model.MovimientoPlan;
import es.expensecalculator.service.IMovimientoPlanService;
import es.expensecalculator.service.IMovimientoService;

// TODO: Auto-generated Javadoc
/**
 * The Class MovimientoConverter.
 */
@ManagedBean(name = "movimientoPlanConverter")
@RequestScoped
@FacesConverter(value = "movimientoPlanConverter")
public class MovimientoPlanConverter implements Converter {

    /** The movimiento service. */
    @ManagedProperty(value = "#{MovimientoPlanService}")
    private IMovimientoPlanService movimientoPlanService;

    /**
     * Convierte un string (id de movimiento) en un objeto del modelo.
     * 
     * @param facesContext the faces context
     * @param comp the comp
     * @param value the value
     * @return the as object
     */
    public Object getAsObject(FacesContext facesContext, UIComponent comp, String value) {
        if (value != null && !"".equals(value)) {
            try {
                Long movimientoPlanid = Long.parseLong(value);
                MovimientoPlan movimientoPlan = movimientoPlanService.getMovimientoPlanById(movimientoPlanid);
                return movimientoPlan;
            } catch (NumberFormatException nfe) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "No es un movimiento valido "
                        + nfe.getMessage()));
            } catch (Exception nfe) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "No es un movimiento valido "
                        + nfe.getMessage()));
            }
        }
        return null;
    }

    /**
     * Convierte la movimiento del modelo en un string (su id) apto para usar en la vista.
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
        if (value instanceof MovimientoPlan) {
            Long key = ((MovimientoPlan) value).getId();
            return key != null ? key.toString() : null;
        } else {
            return null;
        }
    }

    /**
     * Gets the movimiento service.
     * 
     * @return the movimiento service
     */
    public IMovimientoPlanService getMovimientoPlanService() {
        return movimientoPlanService;
    }

    /**
     * Sets the movimiento service.
     * 
     * @param movimientoService the new movimiento service
     */
    public void setMovimientoPlanService(IMovimientoPlanService movimientoPlanService) {
        this.movimientoPlanService = movimientoPlanService;
    }

}
