package es.expensecalculator.web.exception;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.security.auth.login.LoginException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controlador de excepciones
 */
public class ExceptionHandler extends ExceptionHandlerWrapper {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandler.class);

	/** The wrapped. */
	private final javax.faces.context.ExceptionHandler wrapped;

	/**
	 * Instantiates a new exception handler.
	 * 
	 * @param wrapped
	 *            the wrapped
	 */
	public ExceptionHandler(final javax.faces.context.ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.context.ExceptionHandlerWrapper#getWrapped()
	 */
	@Override
	public javax.faces.context.ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.context.ExceptionHandlerWrapper#handle()
	 */
	@Override
	public void handle() throws FacesException {
		for (final Iterator<ExceptionQueuedEvent> it = getUnhandledExceptionQueuedEvents().iterator(); it.hasNext();) {
			Throwable t = it.next().getContext().getException();
			while ((t instanceof FacesException || t instanceof IllegalStateException) && t.getCause() != null) {
				t = t.getCause();
			}
			if (t instanceof FileNotFoundException || t instanceof ViewExpiredException || t instanceof LoginException
					|| t instanceof ClassCastException || t instanceof Exception) {
				final FacesContext facesContext = FacesContext.getCurrentInstance();
				final ExternalContext externalContext = facesContext.getExternalContext();
				final Map<String, Object> requestMap = externalContext.getRequestMap();
				try {
					LOG.info("{}: {}", t.getClass().getSimpleName(), t.getMessage());
					String message;
					if (t instanceof ViewExpiredException || t instanceof LoginException) {
						// final String viewId = ((ViewExpiredException)
						// t).getViewId();
						message = "View is expired.";
						requestMap.put("errorMsg", message);
						try {
							externalContext.dispatch("/expired.jsp");
						} catch (final IOException e) {
							LOG.error("Error view '/expired.xhtml' unknown!", e);
						}
					} else if (t instanceof NullPointerException) {
						message = t.getMessage(); // beware, don't leak internal
													// info!
						requestMap.put("errorMsg", message);
						try {
							externalContext.dispatch("/errorNull.jsp");
						} catch (final IOException e) {
							LOG.error("Error view '/errorNull.jsp' unknown!", e);
						}
					} else {
						message = t.getMessage(); // beware, don't leak internal
													// info!
						requestMap.put("errorMsg", message);
						try {
							externalContext.dispatch("/error.jsp");
						} catch (final IOException e) {
							LOG.error("Error view '/error.jsp' unknown!", e);
						}
					}

					facesContext.responseComplete();
				} finally {
					it.remove();
				}
			}
		}
		getWrapped().handle();
	}
}
