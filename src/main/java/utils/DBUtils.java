/**
 * 
 */
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.poi.util.StringUtil;

import stats.MainClass;

/**
 * @author mamsow
 *
 */
public class DBUtils {

	private static final Logger dbUtilsLOGGER = MainClass.MAIN_LOGGER;

	private final static String DB_URL = "jdbc:postgresql://localhost:5432/";

	private final static String DB_NAME = "dvdrental";

	private final static String DB_USER = "postgres";

	private final static String DB_PASSWORD = "2182";

	private final static String DB_DRIVER = "org.postgresql.Driver";

	private static Connection conn = null;

	private static Properties properties = null;

	public static Connection getConnection(Properties props)
			throws SQLException {

		properties = props;// Utils.loadProperties(propertiesFile.getPath());
		if (properties != null) {
			// Get DB properties
			String dbName = properties.getProperty("DB_NAME");
			String dbUser = properties.getProperty("DB_USER");
			String dbUrl = properties.getProperty("DB_URL");
			String dbPassword = properties.getProperty("DB_PASSWORD");
			String db_driver = properties.getProperty("DB_DRIVER");
			// enregistrer le pilote
			try {
				Class.forName(db_driver);
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
		}
		return conn;
	}

	public static void readAllParam(String propertiesFile) {
		try {
			// String path = new File(".").getCanonicalPath();
			// String dir = new File(path).getParent();
			//
			// String prop = dir + "\\conf/main.properties";

			FileInputStream file = new FileInputStream(propertiesFile);

			// to load application's properties,
			properties = new Properties();

			// load all the properties from this file
			properties.load(file);

			// close the file
			file.close();

			System.err.println("Properties size\t" + properties.size());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			dbUtilsLOGGER.fatal(e.getStackTrace(), e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public static int nbArgs(String request) {
		int counter = 0;
		for (int i = 0; i < request.length(); i++) {
			if (request.charAt(i) == '?') {
				counter++;
			}
		}
		return counter;
	}

	public static void closeConnection(Logger logger) throws SQLException {
		try {
			conn.close();
			dbUtilsLOGGER.info("Déconnexion réussi...");
		} catch (Exception e) {
			dbUtilsLOGGER.error(e.getMessage());
		}
	}
}
