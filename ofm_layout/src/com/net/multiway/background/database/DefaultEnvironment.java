/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.database;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;

/**
 *
 * @author Phelipe
 */
public class DefaultEnvironment implements Environment {

	private final Properties properties = new Properties();
	private String environment;
	private static Environment instance = null;
	private Application app;

	protected DefaultEnvironment(String environment) throws IOException {
		if (environment == null || environment.equals("")) {
			environment = "development";
		}
		this.environment = environment;

		loadAndPut("environment");
		loadAndPut(environment);
	}

	public static Environment getInstance() {

		if (instance == null) {
			try {
				instance = new DefaultEnvironment(null);
			} catch (IOException ex) {
				Logger.getLogger(DefaultEnvironment.class.getName()).log(Level.SEVERE, null, ex);
				ex.printStackTrace();
				return null;
			}
		}

		return instance;
	}

	public void setApplication(Application app) {
		this.app = app;
	}

	private void loadAndPut(String environment) throws IOException {
		String name = "/META-INF/" + environment + ".properties";
		InputStream stream = DefaultEnvironment.class.getResourceAsStream(name);
		Properties properties = new Properties();

		if (stream != null) {
			properties.load(stream);
			this.properties.putAll(properties);
		} else {
			System.out.println("File " + environment + ".properties is missing!");
		}
	}

	@Override
	public boolean supports(String feature) {
		return Boolean.parseBoolean(get(feature));
	}

	@Override
	public boolean has(String key) {
		return properties.containsKey(key);
	}

	@Override
	public String get(String key) {
		if (!has(key)) {
			System.out.println("Key " + key + " not found in environment " + environment);
		}
		return properties.getProperty(key);
	}

	@Override
	public String get(String key, String defaultValue) {
		if (has(key)) {
			return get(key);
		}
		return defaultValue;
	}

	@Override
	public void set(String key, String value) {
		properties.setProperty(key, value);
	}

	@Override
	public Iterable<String> getKeys() {
		return properties.stringPropertyNames();
	}

	@Override
	public URL getResource(String name) {
		URL resource = DefaultEnvironment.class.getResource("/META-INF/" + environment
				+ name);
		if (resource != null) {
			return resource;
		}
		return DefaultEnvironment.class.getResource(name);
	}

	@Override
	public String getName() {
		return environment;
	}

	public String getBaseDir() {
		try {
			return new File(".").getCanonicalPath();
		} catch (IOException ex) {
			Logger.getLogger(DefaultEnvironment.class.getName()).log(Level.SEVERE, null, ex);
			return "";
		}
	}
}
