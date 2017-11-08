package es.expensecalculator.web.exception;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating ExceptionHandler objects.
 */
public class ExceptionHandlerFactory extends javax.faces.context.ExceptionHandlerFactory {

    /** The parent. */
    private final javax.faces.context.ExceptionHandlerFactory parent;

    /**
     * Instantiates a new exception handler factory.
     *
     * @param parent the parent
     */
    public ExceptionHandlerFactory(final javax.faces.context.ExceptionHandlerFactory parent) {
        this.parent = parent;
    }

    /* (non-Javadoc)
     * @see javax.faces.context.ExceptionHandlerFactory#getExceptionHandler()
     */
    @Override
    public ExceptionHandler getExceptionHandler() {
        return new ExceptionHandler(this.parent.getExceptionHandler());
    }

}
