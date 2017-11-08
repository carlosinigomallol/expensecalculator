package es.expensecalculator.web.param;

import java.io.Serializable;

/**
 * Interfaz para los contenedores de datos de los formularios
 * 
 * @param <T> Instancia concreta del contenedor
 */
public interface EntityParam<T> extends Serializable {

    /**
     * @return Instancia del contenedor
     */
    T getEntityParam();

    /**
     * Limpia el objeto de ejemplo (crea uno nuevo).
     */
    void limpiarEntityParam();

    /**
     * Comprueba si el filtro esta relleno.
     * 
     * @return true, si está relleno
     */
    boolean isFilledEntityParam();

    /**
     * @return true, si se ha pulsado un botón en el formulario
     */
    boolean isButtonPressed();

    /**
     * @param buttonPressed Activa o desactiva el indicador de botón pulsado
     */
    void setButtonPressed(boolean buttonPressed);

}
