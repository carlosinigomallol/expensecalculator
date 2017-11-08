package es.expensecalculator.model.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class CalculadoraGastosException.
 */
public class CalculadoraGastosException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new modelosaeat exception.
     * 
     * @param s the s
     */
    public CalculadoraGastosException(String s) {
        super(s);
    }

    /**
     * Instantiates a new modelosaeat exception.
     * 
     * @param s the s
     * @param e the e
     */
    public CalculadoraGastosException(String s, Exception e) {
        super(s, e);
    }

    public CalculadoraGastosException(Exception e) {
        super(e);
    }

}
