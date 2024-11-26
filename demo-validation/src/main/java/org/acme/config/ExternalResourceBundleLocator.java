package org.acme.config;

import java.io.FileInputStream;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;

public class ExternalResourceBundleLocator implements ResourceBundleLocator {

    private final String externalPath;
    
    private final String baseName;
    
    

    public ExternalResourceBundleLocator(String externalPath, String baseName) {
        this.externalPath = externalPath;
        this.baseName = baseName;
    }

    @Override
    public ResourceBundle getResourceBundle(Locale locale) {
    	//TODO
//    	Locale.setDefault(new Locale("es"));
        try {
        	System.out.println("locale: "+locale.getLanguage());
            // Construct the path to the external file
            String bundlePath = externalPath + "/" + baseName + "_" + locale.getLanguage() + ".properties";
            
            // Load the bundle from the file system
            return new PropertyResourceBundle(new FileInputStream(bundlePath));
        } catch (Exception e) {
            // Fallback to default if not found
            return ResourceBundle.getBundle(baseName, locale);
        }
    }
}
