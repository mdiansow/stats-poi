package sqlrequestImpl;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;

import sqlrequest.ISQLQueryManager;
import utils.Constant;
import utils.DBUtils;
import utils.Utils;

/**
 * 
 * @author MDian
 *
 */
public class SQLQueryManagerImpl implements ISQLQueryManager {

	/*
	 * (non-Javadoc)
	 * 
	 * @see sqlrequest.ISQLQueryManager#processAllQuery(java.lang.String)
	 */
	@Override
	public Map<String, Object> processAllQuery(String propsFileName,
			Map<String, String> args, Map<String, Object> queryResult, Sheet sheetName) {
		// TODO Auto-generated method stub
		URL propertiesFile = SQLQueryManagerImpl.class.getResource(propsFileName);

		Properties props = Utils.loadProperties(propertiesFile.getPath());
		System.err.println("Process " + propsFileName);
		Connection conn;
		try {
			conn = DBUtils.getConnection(props);
			if (props != null) {
				List<String> sqlProps = Utils.sqlRequestsKeys(props);
				System.err.println("Query result ");
				for (String key : sqlProps) {
					String request = props.getProperty(key);
					String[] rArgs = requestArgs(args, request);
					Object result = query(conn, request, rArgs);
					System.err.println("Key " + key + " Result \t" + result);
					queryResult.put(key, result);
				}
				conn.close();
				System.out.println("");
				System.err.println("END of ..." + propsFileName);
				System.out.println();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return queryResult;
	}

	private String[] requestArgs(Map<String, String> args, String request) {
		// TODO Auto-generated method stub
		String[] requestArgs = null;

		int nbArgs = DBUtils.nbArgs(request);

		String endDate = args.get(Constant.END_DATE_NAME);
		String startDate = args.get(Constant.START_DATE_NAME);

		try {
			if (nbArgs == 1) {
				requestArgs = new String[] { endDate };
			} else if (nbArgs == 2) {
				requestArgs = new String[] { startDate, endDate };
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return requestArgs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sqlrequest.ISQLQueryManager#query(utils.DBUtils, java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public Object query(Connection conn, String request, String[] args) {
		Object result = null;

		try {
			PreparedStatement ps = conn.prepareStatement(request);

			SimpleDateFormat format = new SimpleDateFormat(
					utils.Constant.DATE_FORMAT);

			try {
				int nbArgs = args.length;
				if (nbArgs > 0) {
					for (int i = 0; i < nbArgs; i++) {
						java.util.Date date = format.parse(args[i]);
						ps.setDate(i + 1, new Date(date.getTime()));
					}
				}

				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					result = rs.getObject(1);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
