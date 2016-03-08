/**
 * 
 */
package stats;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import sqlrequestImpl.SQLQueryManagerImpl;
import utils.Constant;
import utils.DBUtils;
import utils.Utils;
import engine.IExcelManager;
import engineImpl.ExcelManagerImpl;

/**
 * @author mamsow
 *
 */
public class MainClass {

	public static Connection dbConnexion = null;

	public static final Logger MAIN_LOGGER = org.apache.log4j.Logger
			.getLogger(MainClass.class);

	// private static String excelFilePath = "\\conf/Stats-2016.xlsx";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initLogger();
		MAIN_LOGGER.info("Start extract...");

		Map<String, String> dateArgs = new HashMap<String, String>();
		dateArgs.put(Constant.END_DATE_NAME, "01/04/2007");
		dateArgs.put(Constant.START_DATE_NAME, "25/02/2007");

		Map<String, Object> queryResult = new HashMap<String, Object>();

		new SQLQueryManagerImpl().processAllQuery("/ARCCPT.properties",
				dateArgs, queryResult);
		new SQLQueryManagerImpl().processAllQuery("/ARCCSI.properties",
				dateArgs, queryResult);

		System.err.println("nb of result " + queryResult.size());

		// Calendar cal = Calendar.getInstance();
		// System.out.println(new SimpleDateFormat("MM/YYYY").format(cal
		// .getTime()));
		//
		//
		//
		// URL propertiesFile =
		// MainClass.class.getResource("/ARCCPT.properties");
		// System.err.println("Path\t" + propertiesFile.getFile());
		//
		// Properties props = Utils.loadProperties(propertiesFile.getPath());
		//
		// List<String> sqlProps = Utils.sqlRequestsKeys(props);
		// System.out.println("SQL request key:  " + sqlProps.size());
		// for (int i = 0; i < sqlProps.size(); i++) {
		// System.err.println("Request (" + i + "), nb args: "
		// + DBUtils.nbArgs(props.getProperty(sqlProps.get(i))));
		// }
		// DB properties
		// Utils.loadDBProperties("", null);

		// List<File> files = new ArrayList<File>();
		// new Utils().listf("/resources", files);
		//
		// for (File f : files) {
		// System.err.println("File main\t" + f.getName());
		// }
		// String[] rArgs = { "01/04/2007", "01/05/2007" };

		// try {
		// dbConnexion = DBUtils.getConnection();
		// new SQLQueryManagerImpl()
		// .query(dbConnexion,
		// "SELECT count(*) nb, sum(amount) as somme FROM public.payment WHERE payment_date > ? and payment_date < ?",
		// rArgs);
		// dbConnexion.close();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// try {
		// dbConnexion = DBUtils.getConnection();
		//
		// // Test request
		// String req = "select * from actor;";
		// PreparedStatement ps = dbConnexion.prepareStatement(req);
		//
		// // Process the result
		// try {
		// ResultSet rs = ps.executeQuery();
		// while (rs.next()) {
		// System.out.println("First name: " + rs.getString(2));
		// }
		// } catch (SQLException e) {
		// MAIN_LOGGER.error(e.getStackTrace());
		// }
		//
		// } catch (SQLException e1) {
		// // TODO Auto-generated catch block
		// MAIN_LOGGER.fatal(e1.getMessage(), e1);
		// e1.printStackTrace();
		// }

		// IExcelManager extractor = new ExcelManagerImpl();
		// extractor.processExcelFile("\\conf/Stats-2016.xls");

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
