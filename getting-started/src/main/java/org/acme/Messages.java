package org.acme;

import io.quarkus.qute.i18n.MessageBundle;
import io.quarkus.qute.i18n.Localized;

@MessageBundle
public interface Messages {

    String resourceNotFound(String resourceName);

    String invalidInput();

    String accessDenied();

}
