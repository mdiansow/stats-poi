package sqlrequestImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import sqlrequest.ISQLQueryManager;

/**
 * 
 * @author MDian
 *
 */
public class SQLQueryManagerImpl implements ISQLQueryManager {

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
					System.err.println("Query \t" + result);
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
