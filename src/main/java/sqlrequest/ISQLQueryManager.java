package sqlrequest;

import java.sql.Connection;

/**
 * 
 * @author MDian
 *
 */
public interface ISQLQueryManager {

	/**
	 * Ex�cute une requ�te avec des arguments
	 * 
	 * @param conn
	 * @param request
	 * @param args
	 * @return
	 */
	Object query(Connection conn, String request, String[] args);
}
