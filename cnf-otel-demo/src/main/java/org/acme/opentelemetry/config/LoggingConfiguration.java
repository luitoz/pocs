package org.acme.opentelemetry.config;

import java.io.IOException;
import java.util.logging.Handler;

import org.jboss.logmanager.LogContext;
import org.jboss.logmanager.Logger;
import org.jboss.logmanager.handlers.ConsoleHandler;
import org.jboss.logmanager.handlers.SocketHandler;
import org.jboss.logmanager.handlers.SyslogHandler;

import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import static io.quarkus.bootstrap.logging.InitialConfigurator.DELAYED_HANDLER;

@ApplicationScoped
@Startup
public class LoggingConfiguration {

    @PostConstruct
    public void setup() {

        
        LogContext logContext = LogContext.getLogContext();
        Logger rootLogger = logContext.getLogger("");
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new ObfuscatingPatternFormatter
        		("%d{yyyy-MM-dd HH:mm:ss} %-5p [%c] (%t) %s%e%n"));
        rootLogger.addHandler(consoleHandler);
        System.out.println("Custom logging handler registered.");
        
        try {
            SocketHandler socketHandler = new SocketHandler("localhost",12345);
//            socketHandler.setHost("your-secure-log-server.com");
//            socketHandler.setPort(12345);
//            socketHandler.setSecure(true); // Enable secure connection
            socketHandler.
            setFormatter(new ObfuscatingPatternFormatter
            		("%d{yyyy-MM-dd HH:mm:ss} %-5p [%c] (%t) %s%e%n"));
            rootLogger.addHandler(socketHandler);
            System.out.println("Custom secure socket handler registered.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SyslogHandler syslogHandler=null;
		try {
			syslogHandler = new SyslogHandler("localhost", 514);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        syslogHandler.setProtocol(SyslogHandler.Protocol.TCP);
        syslogHandler.setFacility(SyslogHandler.Facility.USER_LEVEL);
        syslogHandler.setFormatter(new ObfuscatingPatternFormatter("%d{yyyy-MM-dd HH:mm:ss} [%t] %-5level %logger{36} - %msg%n"));
        rootLogger.addHandler(syslogHandler);        
//        try {
//        	JBoss7GelfLogHandler  gelfHandler = new JBoss7GelfLogHandler ();
//            gelfHandler.setHost("localhost");
//            gelfHandler.setPort(12201);
//            gelfHandler.setFormatter(new ObfuscatingPatternFormatter("%d{yyyy-MM-dd HH:mm:ss} [%t] %-5level %logger{36} - %msg%n"));
//            rootLogger.addHandler(gelfHandler);
//            System.out.println("Custom GELF handler registered.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
