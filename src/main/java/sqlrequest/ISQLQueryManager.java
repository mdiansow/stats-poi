package sqlrequest;

import java.sql.Connection;

/**
 * 
 * @author MDian
 *
 */
public interface ISQLQueryManager {

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
