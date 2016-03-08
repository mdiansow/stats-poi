package sqlrequest;

import java.sql.Connection;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * 
 * @author MDian
 *
 */
public interface ISQLQueryManager {

	/**
	 * Exécute toutes les requêtes d'un fichier de propriété
	 * 
	 * @param propsFileName
	 * @param args
	 *            TODO
	 * @param queryResult TODO
	 * @param sheetName TODO
	 * @return
	 */
	Map<String, Object> processAllQuery(String propsFileName, Map<String, String> args, Map<String, Object> queryResult, Sheet sheetName);

	/**
	 * Exécute une requête avec des arguments
	 * 
	 * @param conn
	 * @param request
	 * @param args
	 * @return
	 */
	Object query(Connection conn, String request, String[] args);
}
