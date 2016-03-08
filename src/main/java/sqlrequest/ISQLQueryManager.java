package sqlrequest;

import java.sql.Connection;
import java.util.Map;

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
	 * @return
	 */
	Map<String, Object> processAllQuery(String propsFileName, Map<String, String> args, Map<String, Object> queryResult);

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
