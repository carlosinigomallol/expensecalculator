/**
 * $Id: Messages.java 118982 2012-11-16 17:01:45Z jose.galo $
 */
package es.expensecalculator.web.messages;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

// TODO: Auto-generated Javadoc
/**
 * The Class Messages.
 *
 * @author jose
 */
public class Messages extends ResourceBundle {

    /** The Constant BUNDLE_NAME. */
    protected static final String BUNDLE_NAME = "es.rbc.modelosaeat.web.messages";
    
    /** The Constant BUNDLE_EXTENSION. */
    protected static final String BUNDLE_EXTENSION = "properties";
    
    /** The Constant UTF8_CONTROL. */
    protected static final Control UTF8_CONTROL = new UTF8Control();

    /**
     * Instantiates a new messages.
     */
    public Messages() {
        setParent(ResourceBundle.getBundle(BUNDLE_NAME, FacesContext.getCurrentInstance().getViewRoot().getLocale(),
                UTF8_CONTROL));
    }

    /* (non-Javadoc)
     * @see java.util.ResourceBundle#handleGetObject(java.lang.String)
     */
    @Override
    protected Object handleGetObject(String key) {
        return parent.getObject(key);
    }

    /* (non-Javadoc)
     * @see java.util.ResourceBundle#getKeys()
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Enumeration getKeys() {
        return parent.getKeys();
    }

    /**
     * The Class UTF8Control.
     */
    protected static class UTF8Control extends Control {
        
        /* (non-Javadoc)
         * @see java.util.ResourceBundle.Control#newBundle(java.lang.String, java.util.Locale, java.lang.String, java.lang.ClassLoader, boolean)
         */
        @Override
        public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader,
                boolean reload) throws IllegalAccessException, InstantiationException, IOException {
            // The below code is copied from default Control#newBundle()
            // implementation.
            // Only the PropertyResourceBundle line is changed to read the file
            // as UTF-8.
            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, BUNDLE_EXTENSION);
            ResourceBundle bundle = null;
            InputStream stream = null;
            if (reload) {
                URL url = loader.getResource(resourceName);
                if (url != null) {
                    URLConnection connection = url.openConnection();
                    if (connection != null) {
                        connection.setUseCaches(false);
                        stream = connection.getInputStream();
                    }
                }
            } else {
                stream = loader.getResourceAsStream(resourceName);
            }
            if (stream != null) {
                try {
                    bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
                } finally {
                    stream.close();
                }
            }
            return bundle;
        }
    }

}
