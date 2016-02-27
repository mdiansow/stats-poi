/**
 * 
 */
package utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
		// enregistrer le pilote
		try {
			Class.forName(DB_DRIVER);
			// String url = DB_URL;
			dbUtilsLOGGER.info("Connexion à la base de données "
					+ DB_USER.toUpperCase());
			conn = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER,
					DB_PASSWORD);
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

	public static void closeConnection(Logger logger) throws SQLException {
		try {
			conn.close();
			dbUtilsLOGGER.info("Déconnexion réussi...");
		} catch (Exception e) {
			dbUtilsLOGGER.error(e.getMessage());
		}
	}
}
