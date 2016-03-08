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
	 * Ex�cute toutes les requ�tes d'un fichier de propri�t�
	 * 
	 * @param propsFileName
	 * @param args
	 *            TODO
	 * @param queryResult TODO
	 * @return
	 */
	Map<String, Object> processAllQuery(String propsFileName, Map<String, String> args, Map<String, Object> queryResult);

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
