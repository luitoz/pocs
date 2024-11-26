package org.acme.config;

import java.util.Locale;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.hibernate.validator.spi.messageinterpolation.LocaleResolver;
import org.hibernate.validator.spi.messageinterpolation.LocaleResolverContext;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CustomLocaleResolver implements LocaleResolver {
	
	@ConfigProperty(name = "quarkus.default-locale") 
	String defaultLocale;

	@Override
	public Locale resolve(LocaleResolverContext context) {
		return new Locale(defaultLocale);
	}
}
