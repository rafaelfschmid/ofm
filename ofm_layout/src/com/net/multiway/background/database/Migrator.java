/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.database;

import org.flywaydb.core.Flyway;

/**
 *
 * @author Phelipe
 */
class Migrator {

	public static void migrate() {
		Environment environment = DefaultEnvironment.getInstance();

		if (environment.get("database.usemigration").equalsIgnoreCase("false")) {
			return;
		}

		System.out.println("Initializing migration...");
		System.out.println("Scanning folder: " + environment.get("database.migration.folder"));

		System.out.println(environment.get("database.url"));

		String url = environment.get("database.url");
		String username = environment.get("database.user");
		String password = environment.get("database.password");

		Flyway flyway = new Flyway();
		flyway.setLocations(environment.get("database.migration.folder"));
		flyway.setDataSource(url, username, password);
		flyway.migrate();
	}
}
