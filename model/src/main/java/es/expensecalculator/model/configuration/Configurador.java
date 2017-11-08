package es.expensecalculator.model.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configurador. Lee y carga la información del fichero de propiedades
 */
public final class Configurador {

    /** Logger. */
    private final static Logger LOGGER = LoggerFactory.getLogger(Configurador.class);

    /** Contenedor de propiedades. */
    private final static Seguridad KEYS_CONTAINER = new Seguridad();

    /**
     * Constructor por defecto
     */
    private Configurador() {
        super();
    }

    /**
     * Inicializa el contenedor de propiedades
     */
    static {

        Properties config = new Properties();
        InputStream io = null;
        try {
            io = Configurador.class.getResourceAsStream("/config-expensecalculator.properties");
            config.load(io);
        } catch (Exception e) {
            LOGGER.error("Error al obtener la configuración por defecto", e);
        } finally {
            if (io != null) {
                try {
                    io.close();
                } catch (IOException i) {
                    LOGGER.error(i.getMessage(), i);
                }
            } else {
                LOGGER.info("Accediendo al fichero por la ruta directa");
                try {
                    io = new FileInputStream("/produccion/expensecalculator/etc/config-expensecalculator.properties");
                    config.load(io);
                } catch (IOException i) {
                    LOGGER.error("Error al obtener la configuración en la ruta indicada", i);
                } finally {
                    if (io != null) {
                        try {
                            io.close();
                        } catch (IOException i) {
                            LOGGER.error(i.getMessage(), i);
                        }
                    }
                }
            }
        }

        KEYS_CONTAINER.setProperty(AppKeys.expensecalculator_DIRECTORIO_CONFIGURACION, config.getProperty(AppKeys.expensecalculator_DIRECTORIO_CONFIGURACION));
    }

    /**
     * Devuelve el valor de una clave
     * 
     * @param key Clave
     * @return Valor de la configuración
     */
    public static String getProperty(String key) {
        return KEYS_CONTAINER.getProperty(key);
    }
}
