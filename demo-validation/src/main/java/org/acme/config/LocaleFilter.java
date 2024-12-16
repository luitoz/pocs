package org.acme.config;

import java.util.Locale;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class LocaleFilter implements ContainerRequestFilter {

    public static final ThreadLocal<Locale> THREAD_LOCAL_LOCALE = new ThreadLocal<>();

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String acceptLanguage = requestContext.getHeaderString("Accept-Language");
        Locale locale = new Locale(acceptLanguage);
        THREAD_LOCAL_LOCALE.set(locale);
    }
}
