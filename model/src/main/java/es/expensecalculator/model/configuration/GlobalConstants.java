/*
 * $Id: $ Created on 21/03/2011
 */
package es.expensecalculator.model.configuration;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * This is an utility class which it main purpose is to avoid Firebug or PMD rules such as duplicated value. Therefore
 * here we will define constants with the most common duplicated values along the project.
 * 
 * @author Jesus Calvo
 */
public final class GlobalConstants {
    /** This is to avoid duplicated values in code. */
    public static final String UNCHECKED = "unchecked";
    /** Batch size used for entities with few elements: {@value #BATCH_SIZE_SMALL}. */
    public static final int BATCH_SIZE_SMALL = 100;
    /** Batch size used for entities with a lot of elements: {@value #BATCH_SIZE_BIG}. */
    public static final int BATCH_SIZE_BIG = 400; //antes eran 400.
    /** Batch size used for entities with more than 200 elements and less than 500 : {@value #BATCH_SIZE_MEDIUM}. */
    public static final int BATCH_SIZE_MEDIUM = 250;
    /** Default locale used in non localized conversions. */
    public static final Locale DEFAULT_LOCALE = new Locale("es", "ES");

    /** Esta constante determina el tamaño minimo que permitimos para la consulta de codigos ISIN. */
    public static final int MINIMUN_ISIN_CODE_SIZE_FOR_QUERY = 4;

    /** Esta constante determina el tamaño minimo que permitimos para la consulta de codigos BIC. */
    public static final int MINIMUN_BIC_CODE_SIZE_FOR_QUERY = 2;

    /** Esta constante determina el tamaño minimo que permitimos para la consulta por descripcion. */
    public static final int MINIMUN_DESCRIPTION_SIZE_FOR_QUERY = 5;

    /** Esta constante determina el máximo de resultados que se obtiene en las consultas. */
    public static final Long MAX_SEARCH_RESULT_LIMIT = Long.MAX_VALUE;

    public static final SimpleDateFormat FORMAT_DATE_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

    public static final SimpleDateFormat FORMAT_DATE_YYYYMMDD = new SimpleDateFormat("yyyyMMdd");

    /**
     * Instantiates a new global constants.
     */
    private GlobalConstants() {
        super();
    }
}
