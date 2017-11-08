package es.expensecalculator.web.converters;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

// TODO: Auto-generated Javadoc
/**
 * The Class _BigDecimalConverter.
 *
 * @author carlos.mallol
 * @since 20080708
 */
public abstract class _BigDecimalConverter implements Converter {

    /** The decimal positions. */
    protected Integer decimalPositions = null;

    //20-05-2008 : Forzamos que el locale sea ES, para que las máscaras de los campos
    //numéricos funcionen correctamente.
    /** The format. */
    protected NumberFormat format = NumberFormat.getNumberInstance(new Locale("es", "ES"));

    /**
     * Instantiates a new _ big decimal converter.
     */
    public _BigDecimalConverter() {

        //format = format;

        this.setMaxNumDecimales();

        format.setMaximumFractionDigits(decimalPositions);
    }

    /**
     * Sets the max num decimales.
     */
    protected abstract void setMaxNumDecimales();

    /* (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
     */
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) throws ConverterException {

        if (facesContext == null) {
            throw new NullPointerException("facesContext");
        }
        if (uiComponent == null) {
            throw new NullPointerException("uiComponent");
        }

        if (value != null) {
            value = value.trim();
            if (value.length() > 0) {
                try {

                    Number aux = format.parse(value);
                    BigDecimal valor = new BigDecimal(aux.doubleValue());

                    return valor.setScale(decimalPositions, BigDecimal.ROUND_HALF_EVEN);
                } catch (ParseException e) {
                    throw new ConverterException("Error de conversion " + e.getMessage(), e);
                } catch (ClassCastException e1) {
                    throw new ConverterException("Error de conversion " + e1.getMessage(), e1);
                }
            }

        }
        return null;
    }

    /* (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
     */
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {

        if (facesContext == null) {
            throw new NullPointerException("facesContext");
        }
        if (uiComponent == null) {
            throw new NullPointerException("uiComponent");
        }

        if (value == null) {
            return "";
        }

        if (value instanceof String) {
            return (String) value;
        }

        try {
            BigDecimal aux = (BigDecimal) value;
            aux = aux.setScale(decimalPositions, BigDecimal.ROUND_HALF_EVEN);
            String ret = format.format(aux);
            return ret;
        } catch (Exception e) {
            throw new ConverterException(e);
        }
    }

    /**
     * Gets the as string.
     *
     * @param aux the aux
     * @return the as string
     */
    public String getAsString(BigDecimal aux) {

        if (aux == null) {
            return "";
        }

        try {
            aux = aux.setScale(decimalPositions, BigDecimal.ROUND_HALF_EVEN);
            String ret = format.format(aux);
            return ret;
        } catch (Exception e) {
            throw new ConverterException(e);
        }
    }

}
