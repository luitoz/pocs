package org.acme;

import java.util.Locale;

import org.eclipse.microprofile.config.Config;

import io.quarkus.qute.i18n.Localized;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

	@Localized("en")
	Messages errorMessagesEn;

    @Inject
    Config config;

	@Context
	HttpHeaders headers;

	@Override
	public Response toResponse(Exception exception) {
		System.out.println( headers.getAcceptableLanguages());
		Locale locale = headers.getAcceptableLanguages().isEmpty() ? Locale.ENGLISH
				: headers.getAcceptableLanguages().get(0);

		String errorMessage;

		if (exception instanceof ResourceNotFoundException) {
			String resourceName = ((ResourceNotFoundException) exception).getResourceName();
			errorMessage = locale.getLanguage().equals("fr") ? 
					config.getValue("resourceNotFound", String.class)
					: config.getValue("resourceNotFound", String.class);
		} else if (exception instanceof InvalidInputException) {
			errorMessage = locale.getLanguage().equals("fr") ? 
					config.getValue("invalidInput", String.class)
					: config.getValue("invalidInput", String.class);
		} 
			else if (exception instanceof AccessDeniedException) {
				errorMessage = locale.getLanguage().equals("fr") ? 
						config.getValue("accessDenied", String.class)
						: config.getValue("accessDenied", String.class);
		} 
			else {
			errorMessage = "An unexpected error occurred";
		}

		return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).type(MediaType.TEXT_PLAIN).build();
	}
}
