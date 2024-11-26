package org.acme.config;

import java.util.Locale;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.hibernate.validator.BaseHibernateValidatorConfiguration;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;

import io.quarkus.hibernate.validator.ValidatorFactoryCustomizer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ExternalResourceValidatorFactoryCustomizer implements ValidatorFactoryCustomizer{
	@ConfigProperty(name = "messageBundle.externalPath") 
	String externalPath;
	
	@ConfigProperty(name = "messageBundle.baseName") 
	String baseName;
	
    @Inject
    CustomLocaleResolver customLocaleResolver;
	
    @Override
    public void customize(BaseHibernateValidatorConfiguration<?> configuration) {
    	configuration
    	 .messageInterpolator(
    			 new ResourceBundleMessageInterpolator(
    					 new ExternalResourceBundleLocator(externalPath, baseName)) {
    				    @Override
    				    public String interpolate(String message, Context context) {
    				        Locale currentLocale = customLocaleResolver.resolve(null);
    				        return super.interpolate(message, context, currentLocale);
    				    }
    			 });
    }
}
