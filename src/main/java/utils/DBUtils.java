/**
 * 
 */
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * @author mamsow
 *
 */
public class DBUtils {

	private static final Logger dbUtilsLOGGER = org.apache.log4j.Logger
			.getLogger(DBUtils.class);

	private final static String DB_URL = "jdbc:postgresql://localhost:5432/";

	private final static String DB_NAME = "dvdrental";

	private final static String DB_USER = "postgres";

	private final static String DB_PASSWORD = "2182";

	private final static String DB_DRIVER = "org.postgresql.Driver";

	private static Connection conn = null;

	private static Properties properties = null;

	public static Connection getConnection(Logger logger) throws SQLException {

		dbUtilsLOGGER.setLevel(Level.INFO);
		PatternLayout layout = new PatternLayout("%d %-5p %F:%L - %m%n");

		try {
			dbUtilsLOGGER.addAppender(new FileAppender(layout,
					"stats_logger.log"));
		} catch (IOException e) {
			dbUtilsLOGGER.error(
					"[main] Erreur lors de l'ouverture du fichier de log", e);
			e.printStackTrace();
		}

		// Get DB properties
		String dbName = properties.getProperty("DB_NAME");
		String dbUser = properties.getProperty("DB_USER");
		String dbUrl = properties.getProperty("DB_URL");
		String dbPassword = properties.getProperty("DB_PASSWORD");
		// enregistrer le pilote
		try {
			Class.forName(DB_DRIVER);
			// String url = DB_URL;
			dbUtilsLOGGER.info("Connexion à la base de données "
					+ DB_USER.toUpperCase());
			conn = DriverManager.getConnection(dbUrl + dbName, dbUser,
					dbPassword);
			dbUtilsLOGGER.info("Connexion réussi...");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			dbUtilsLOGGER.fatal(e.getMessage(), e);
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
			dbUtilsLOGGER.fatal(e.getMessage(), e);
			System.exit(2);
		}
		return conn;
	}

	public static void loadDBProperties(String path) {
		try {
			path = new File(".").getCanonicalPath();

			String dir = new File(path).getParent();

			String prop = dir + "\\conf/main.properties";

			FileInputStream file = new FileInputStream(prop);

			// to load application's properties,
			properties = new Properties();

			// load all the properties from this file
			properties.load(file);

			Set set = properties.keySet();

			Iterator<Set> it = set.iterator();
			while (it.hasNext()) {
				String key = "" + it.next();
				System.out.println(key + " " + properties.getProperty(key));
			}

			// close the file
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void readAllParam(String paths) {
		try {
			String path = new File(".").getCanonicalPath();
			String dir = new File(path).getParent();

			String prop = dir + "\\conf/main.properties";

			FileInputStream file = new FileInputStream(prop);

			// to load application's properties,
			Properties properties = new Properties();

			// load all the properties from this file
			properties.load(file);

			// close the file
			file.close();

			System.err.println("Properties "
					+ properties.getProperty("app.version"));
			System.out.println();

			System.out.println("Directory  " + dir);
			dbUtilsLOGGER.info("DIR path " + path);
			Files.walk(Paths.get("/e/capgemini/conf").getParent()).forEach(
					filePath -> {
						if (Files.isRegularFile(filePath)) {
							System.out.println(filePath);
						}
					});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			dbUtilsLOGGER.fatal(e.getStackTrace(), e);
			e.printStackTrace();
		}
	}

	/*
	 * public void listFilesForFolder(final File folder) { for (final File
	 * fileEntry : folder.listFiles()) { if (fileEntry.isDirectory()) {
	 * listFilesForFolder(fileEntry); } else {
	 * System.out.println(fileEntry.getName()); } } }
	 */

	public static void closeConnection(Logger logger) throws SQLException {
		try {
			conn.close();
			dbUtilsLOGGER.info("Déconnexion réussi...");
		} catch (Exception e) {
			dbUtilsLOGGER.error(e.getMessage());
		}
	}
}
