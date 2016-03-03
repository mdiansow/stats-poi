/**
 * 
 */
package engineImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
public class ExcelManager implements IExcelManager {

	private static Logger logger = MainClass.MAIN_LOGGER;
	private Workbook workbook;

	/*
	 * (non-Javadoc)
	 * 
	 * @see engineImpl.IExcelManager#processExcelFile(java.lang.String)
	 */
	@Override
	public void processExcelFile(String path) {
		try {
			FileInputStream xlsFile = Utils.readFile(path);

			workbook = WorkbookFactory.create(xlsFile);

			Path parent = Paths.get(System.getProperty("user.dir")).getParent();

			File file = new File(parent + "/conf");
			List<String> propertiesFiles = Utils.listFilesForFolder(file);
			int index = -1;
			while (++index < propertiesFiles.size()) {
				String propertiesFileName = propertiesFiles.get(index);
				String[] splitResult = propertiesFileName.split("\\.");
				if (splitResult.length > 0) {
					String sheetName = splitResult[0];
					System.err.println("Sheet name\t" + sheetName);

					Sheet sheet = workbook.getSheet(sheetName);
					if (sheet != null) {
						logger.info("Sheet name:  " + sheet.getSheetName()
								+ "  Physical row number  "
								+ sheet.getPhysicalNumberOfRows());
						this.processSheet(sheet, null);
					}
				}
			}

			try (FileOutputStream outputStream = new FileOutputStream(
					"JavaBooks.xls")) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * engineImpl.IExcelManager#processShett(org.apache.poi.ss.usermodel.Sheet,
	 * java.util.Properties)
	 */
	@Override
	public void processSheet(Sheet sheetName, Properties properties) {
		writeCell(sheetName, 2, "Hello world");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * engineImpl.IExcelManager#writeCell(org.apache.poi.ss.usermodel.Sheet,
	 * int, java.lang.String)
	 */
	@Override
	public void writeCell(Sheet sheet, int ligne, String request) {
		// Properties properties = Utils.loadProperties("");
		// logger.info("Properties \t" + properties.size());
		String col = "B";
		logger.info("Start write request result to cell " + col + ligne);
		Row row = sheet.getRow(ligne);
		if (row == null) {
			row = sheet.createRow(ligne);
		}
		final Cell cell = row.createCell(3);
		cell.setCellValue(request);
	}
}
