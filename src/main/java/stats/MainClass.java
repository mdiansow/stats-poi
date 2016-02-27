/**
 * 
 */
package stats;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import utils.DBUtils;

/**
 * @author mamsow
 *
 */
public class MainClass {
	
	public static Connection dbConnexion = null;

	private static final Logger mainLogger = org.apache.log4j.Logger
			.getLogger(MainClass.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// gestion des logs
		mainLogger.setLevel(Level.INFO);
		PatternLayout layout = new PatternLayout("%d %-5p %F:%L - %m%n");

		try {
			mainLogger
					.addAppender(new FileAppender(layout, "stats_logger.log"));
		} catch (IOException e) {
			mainLogger.error(
					"[main] Erreur lors de l'ouverture du fichier de log", e);
			e.printStackTrace();
		}
		mainLogger.info("Start extract...");
		
		try {
			dbConnexion = DBUtils.getConnection(mainLogger);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			mainLogger.fatal(e1.getMessage(), e1);
			e1.printStackTrace();
		}

		int a = 25;
		int b = 0;

		try {
			mainLogger.info("Opération: " + a / b);
		} catch (Exception e) {
			mainLogger.error("Impossible:  " + e.getMessage(), e);
		}
		mainLogger.info("End extract....");
	}

}
