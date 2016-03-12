/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.database;

import java.net.URL;
import javafx.application.Application;

/**
 *
 * @author Phelipe
 */
public interface Environment {

	/**
	 * Returns the environment name
	 *
	 */
	String getName();

	/**
	 * Checks if a key is present
	 *
	 */
	boolean has(String key);

	/**
	 * Checks if a key is equals to true if it's not present will return false
	 */
	boolean supports(String feature);

	/**
	 * Returns a key
	 */
	String get(String string);

	/**
	 * Returns a key or a default value if the value isn't set
	 */
	String get(String string, String defaultValue);

	/**
	 * Sets a key in memory. This will *not* affect any configuration file.
	 *
	 * @param key
	 * @param value
	 */
	void set(String key, String value);

	Iterable<String> getKeys();

	/**
	 * Locates a resource according to your current environment.
	 */
	URL getResource(String name);

}
