/**
 * 
 */
package engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import stats.MainClass;
import utils.Utils;

/**
 * 
 * @author mamsow
 *
 */
public class ExcelManager {

	private static Logger logger = MainClass.MAIN_LOGGER;

	/**
	 * Read Excel file.
	 * 
	 * @param path
	 *            Excel file path name
	 */
	public void readExcelFile(String path) {
		try {
			FileInputStream xlsFile = Utils.readFile(path);

			final Workbook workbook = WorkbookFactory.create(xlsFile);

			Sheet sheet = workbook.getSheet("ARCCPT");
			logger.info("Sheet name:  " + sheet.getSheetName() + "  Physical row number  "+sheet.getPhysicalNumberOfRows());

			
			Row row = sheet.getRow(0);
			final Cell cell = row.createCell(3);
			cell.setCellValue("Coucou");
			try (FileOutputStream outputStream = new FileOutputStream(
					"JavaBooks.xlsx")) {
				workbook.write(outputStream);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param ligne
	 * @param request
	 */
	public void writeCell(Sheet sheet, int ligne, String request) {
		Properties properties = Utils.loadProperties("");
		logger.info("Properties \t" + properties.size());
		String col = "B";
		logger.info("Start write request result to cell " + col + ligne);
		Row row = sheet.getRow(ligne);
		final Cell cell = row.createCell(3);
		cell.setCellValue(request);
	}
}
