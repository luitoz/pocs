package org.acme.opentelemetry.config;

import java.util.regex.Pattern;

import org.jboss.logmanager.ExtLogRecord;
import org.jboss.logmanager.formatters.PatternFormatter;

public class ObfuscatingPatternFormatter extends PatternFormatter {

    private final Pattern passwordPattern = Pattern.compile("(?i)(password)=([^\\s]+)");
    private final Pattern ssnPattern = Pattern.compile("(?i)(ssn)=([^\\s]+)");
    private final Pattern creditCardPattern = Pattern.compile("(?i)(creditcard)=([^\\s]+)");

    public ObfuscatingPatternFormatter(String pattern) {
        super(pattern);
    }

    @Override
    public String format(ExtLogRecord record) {
//    	System.out.println("formattttttttttttttt");
        String message = record.getFormattedMessage();
        message = passwordPattern.matcher(message).replaceAll("$1=****");
        message = ssnPattern.matcher(message).replaceAll("$1=****");
        message = creditCardPattern.matcher(message).replaceAll("$1=****");
        record.setMessage(message);
        return super.format(record);
    }
}
