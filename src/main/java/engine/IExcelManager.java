package engine;

import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;

public interface IExcelManager {

	/**
	 * Read Excel file.
	 * 
	 * @param path
	 *            Excel file path name
	 */
	void processExcelFile(String path);

	void processSheet(Sheet sheetName, Properties properties);

	/**
	 * 
	 * @param ligne
	 * @param request
	 */
	void writeCell(Sheet sheet, int ligne, String request);

}