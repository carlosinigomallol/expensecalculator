package es.expensecalculator.model.configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Contenedor multihilo de los valores de la configuración del sistema
 */
public class Seguridad {

    /** Mapa multihilo */
    private Map<String, Object> threads = new HashMap<String, Object>();

    /**
     * Constructor por defecto
     */
    public Seguridad() {
        super();
    }

    /**
     * Devuelve el valor de una clave
     * 
     * @param key Clave
     * @return Valor de la configuración
     */
    public String getProperty(String key) {
        return threads.get(key).toString();
    }

    /**
     * Asigna al mapa el valor para una clave
     * 
     * @param key Clave
     * @param value Valor de la configuración
     */
    public void setProperty(String key, Object value) {
        threads.put(key, value);
    }
}
