package es.expensecalculator.web.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.convert.FacesConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class ComisionConverter.
 * 
 * @author carlos.mallol
 * @since 20080708
 */
@ManagedBean(name = "dosDecimalConverter")
@RequestScoped
@FacesConverter(value = "dosDecimalConverter")
public class DosDecimalConverter extends _BigDecimalConverter {

    /*
     * (non-Javadoc)
     * @see es.rbcdexia.estadoscnmv.action.converters._BigDecimalConverter#setMaxNumDecimales()
     */
    @Override
    protected void setMaxNumDecimales() {

        this.decimalPositions = 2;

    }

}
