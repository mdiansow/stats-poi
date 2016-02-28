/**
 * 
 */
package stats;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import engine.ExcelManager;
import utils.DBUtils;
import utils.Utils;

/**
 * @author mamsow
 *
 */
public class MainClass {

	public static Connection dbConnexion = null;

	public static final Logger MAIN_LOGGER = org.apache.log4j.Logger
			.getLogger(MainClass.class);

	private static String excelFilePath = "\\conf/Stats-2016.xlsx";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initLogger();
		MAIN_LOGGER.info("Start extract...");

		// DBUtils.readAllParam("");

		// DB properties
		// Utils.loadDBProperties("", null);

		try {
			dbConnexion = DBUtils.getConnection();

			// Test request
			String req = "select * from actor;";
			PreparedStatement ps = dbConnexion.prepareStatement(req);

			// Process the result
			try {
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					System.out.println("First name: " + rs.getString(2));
				}
			} catch (SQLException e) {
				MAIN_LOGGER.error(e.getStackTrace());
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			MAIN_LOGGER.fatal(e1.getMessage(), e1);
			e1.printStackTrace();
		}

		ExcelManager extractor = new ExcelManager();
		extractor.readExcelFile("\\conf/Stats-2016.xlsx");

		try {
			Utils.readFile("");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// int a = 25;
		// int b = 0;
		//
		// try {
		// mainLogger.info("Opération: " + a / b);
		// } catch (Exception e) {
		// mainLogger.error("Impossible:  " + e.getMessage(), e);
		// }
		MAIN_LOGGER.info("End extract....");
	}

	/**
	 * 
	 */
	private static void initLogger() {
		// gestion des logs
		MAIN_LOGGER.setLevel(Level.INFO);
		PatternLayout layout = new PatternLayout("%d %-5p %F:%L - %m%n");

		try {
			MAIN_LOGGER
					.addAppender(new FileAppender(layout, "stats_logger.log"));
		} catch (IOException e) {
			MAIN_LOGGER.error(
					"[main] Erreur lors de l'ouverture du fichier de log", e);
			e.printStackTrace();
		}
	}
}
