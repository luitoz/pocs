package org.acme.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.eclipse.microprofile.config.spi.ConfigSource;

public class ExternalConfigSource implements ConfigSource {

    private final Map<String, String> properties = new HashMap<>();

    public ExternalConfigSource() {
        try (FileInputStream fis = new FileInputStream("/dev/config/msg_en.properties")) {
            Properties props = new Properties();
            //TODO properties vs map
            props.load(fis);
            for (String name : props.stringPropertyNames()) {
                properties.put(name, props.getProperty(name));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, String> getProperties() {
        return properties;
    }

    @Override
    public String getValue(String propertyName) {
        return properties.get(propertyName);
    }

    @Override
    public String getName() {
        return "ExternalConfigSource";
    }

	@Override
	public Set<String> getPropertyNames() {
		return properties.keySet();
	}
}
